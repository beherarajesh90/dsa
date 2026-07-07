package com.interviewprep.dsa.trie.prefixAndSearch;

//https://leetcode.com/problems/longest-word-in-dictionary/description/
public class LongestWordInDictionary {
    private TrieNode root = new TrieNode();

    public String longestWord(String[] words) {
        for(String word: words){
            insert(word);
        }

        String[] result = {""};
        dfs(root,new StringBuilder(),  result);
        return result[0];
    }

    private void dfs(TrieNode node, StringBuilder path,String[] result){

        if(path.length() > result[0].length() || (path.length() == result[0].length() && path.toString().compareTo(result[0]) < 0)){
            result[0] = path.toString();
        }

        for(int i=0; i<26; i++){
            TrieNode child = node.children[i];
            if(child != null && child.isWord){
                path.append((char) ('a' + i));
                dfs(child, path, result);
                path.deleteCharAt(path.length()-1);
            }
        }

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

        TrieNode(){
            children = new TrieNode[26];
            isWord = false;
        }
    }
}
