package com.interviewprep.dsa.trie.prefixAndSearch;

//https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
public class DesignAddAndSearchWordsDataStructure {

}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class WordDictionary {

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {

        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null) node.children[idx] = new TrieNode();
            node = node.children[idx];

        }
        node.isEnd = true;

    }

    public boolean search(String word) {

        return dfs(root, word, 0);

    }

    private boolean dfs(TrieNode node, String word, int i) {

        if (i == word.length()) return node.isEnd;

        char ch = word.charAt(i);
        if (ch == '.') {

            for (TrieNode child : node.children) {
                if (child != null && dfs(child, word, i + 1)) return true;
            }
            return false;

        } else {
            int idx = ch - 'a';
            if (node.children[idx] == null) return false;
            return dfs(node.children[idx], word, i + 1);
        }

    }

}