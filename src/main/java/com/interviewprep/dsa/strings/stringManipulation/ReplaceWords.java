package com.interviewprep.dsa.strings.stringManipulation;

import java.util.List;

//https://leetcode.com/problems/replace-words/description/
public class ReplaceWords {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String str : dictionary){
            trie.insert(str);
        }
        StringBuilder result = new StringBuilder();
        String[] sentenceWords = sentence.split(" ");
        for(int i = 0; i<sentenceWords.length; i++){
            String replacement = trie.findReplacement(sentenceWords[i]);
            if (replacement.isEmpty()){
                replacement = sentenceWords[i];
            }
            if (i > 0) result.append(" ");
            result.append(replacement);
        }
        return result.toString();
    }
}

class TrieNode {

    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
    }

}

class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.isEndOfWord = true;
    }

    public String findReplacement(String word) {
        TrieNode node = root;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (node.children[word.charAt(i) - 'a'] == null) return "";

            result.append(word.charAt(i));
            node = node.children[word.charAt(i) - 'a'];

            if (node.isEndOfWord) return result.toString();
        }
        return "";
    }
}