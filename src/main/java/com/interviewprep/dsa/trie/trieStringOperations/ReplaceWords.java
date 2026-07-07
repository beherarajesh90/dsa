package com.interviewprep.dsa.trie.trieStringOperations;

import java.util.List;

//https://leetcode.com/problems/replace-words/description/
public class ReplaceWords {
    private TrieNode root = new TrieNode();

    public String replaceWords(List<String> dictionary, String sentence) {

        // insert the dictionary words into the trie
        for(String word: dictionary){
            insert(word);
        }

        String[] sentenceWords = sentence.split(" ");
        StringBuilder res = new StringBuilder();

        for(String w: sentenceWords){
            if(res.length() > 0) res.append(" ");

            res.append(findRoot(w));
        }

        return res.toString();
    }

    private String findRoot(String word){
        TrieNode node = root;

        StringBuilder cur = new StringBuilder();
        for(char c: word.toCharArray()){
            int index = c - 'a';
            if(node.children[index] == null) return word;

            cur.append(c);

            node = node.children[index];
            if(node.isWord) return cur.toString();

        }

        return word;
    }

    private void insert(String word){
        TrieNode node = root;
        for(char c: word.toCharArray()){
            int index = c - 'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWord = true;
    }

    class TrieNode{
        TrieNode[] children;
        boolean isWord;

        public TrieNode(){
            children = new TrieNode[26];
            isWord = false;
        }
    }
}
