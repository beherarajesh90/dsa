package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.*;

//https://leetcode.com/problems/word-ladder/
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // return ladderLengthBFS(beginWord, endWord, wordList);

        // return ladderLengthBFSWithPatternMap(beginWord, endWord, wordList);

        return ladderLengthBiDirecBFSWithMutation(beginWord, endWord, wordList);
    }

    private int ladderLengthBiDirecBFSWithMutation(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) return 0;

        Set<String> frontSet = new HashSet<>();
        frontSet.add(beginWord);

        Set<String> backSet = new HashSet<>();
        backSet.add(endWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        int level = 1;

        while(!frontSet.isEmpty() && !backSet.isEmpty()){
            //always expand the smaller fronteir
            if(frontSet.size() > backSet.size()){
                Set<String> tempSet = frontSet;
                frontSet = backSet;
                backSet = tempSet;
            }

            Set<String> nextSet = new HashSet<>();
            for(String word: frontSet){

                char[] letters = word.toCharArray();

                for(int i=0; i<word.length(); i++){

                    char original = letters[i];
                    for(char c='a'; c<='z'; c++){

                        if(c == original) continue;
                        letters[i] = c;
                        String mutation = new String(letters);

                        if(backSet.contains(mutation)) return level + 1;

                        if(wordSet.contains(mutation) && !visited.contains(mutation)){
                            visited.add(mutation);
                            nextSet.add(mutation);
                        }
                    }
                    letters[i] = original;
                }
            }
            frontSet = nextSet;
            level++;
        }

        return 0;
    }

    private int ladderLengthBFSWithPatternMap(String beginWord, String endWord, List<String> wordList) {

        //if the endWord is not present in wordList, its not reachable
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordList.contains(endWord)) return 0;

        //populate the pattern map with each words patterns. *ot:[hot, lot, dot]
        Map<String, List<String>> patternMap = new HashMap<>();

        //start word my not be present in the wordlist so add it
        wordSet.add(beginWord);

        //time: O(n * m^2)[n = word list length, m = word length, each substr=O(m), m times = m^2, for every word = n * m^2]
        for(String word: wordSet){
            for(int i=0; i<word.length(); i++){
                String pattern = word.substring(0,i) + "*" + word.substring(i+1);
                patternMap.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int level = 1;

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        //BFS time complexity: O(n * m^2)
        while(!q.isEmpty()){

            int size = q.size();
            for(int i=1; i<=size; i++){

                String curWord = q.poll();
                for(int j=0; j<curWord.length(); j++){

                    String pattern = curWord.substring(0,j) + "*" + curWord.substring(j+1);
                    for(String neighbourWord: patternMap.getOrDefault(pattern, new ArrayList<>())){

                        if(neighbourWord.equals(endWord)) return level + 1;

                        if(!visited.contains(neighbourWord)){
                            q.offer(neighbourWord);
                            visited.add(neighbourWord);
                        }
                    }
                }
            }

            level++;
        }

        return 0;

    }

    //Time: O(n^2 * m) (n = wordlist length[each word is dequed n * n-1 * ... 1 = n*(n-1)/2 = n^2], m = length of each word)
    private int ladderLengthBFS(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        wordSet.remove(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                // Compare against every remaining word in the set
                Iterator<String> it = wordSet.iterator();
                while (it.hasNext()) {
                    String word = it.next();
                    if (differsByOne(current, word)) {
                        if (word.equals(endWord)) return level + 1;
                        queue.offer(word);
                        it.remove();
                    }
                }
            }
            level++;
        }
        return 0;
    }

    private boolean differsByOne(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }
}
