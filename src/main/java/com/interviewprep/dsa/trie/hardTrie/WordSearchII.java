package com.interviewprep.dsa.trie.hardTrie;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/word-search-ii/description/
public class WordSearchII {
    //     private TrieNode root = new TrieNode();

//     public List<String> findWords(char[][] board, String[] words) {

//         for(String word: words){
//             insert(word);
//         }

//         int m = board.length;
//         int n = board[0].length;

//         List<String> result = new ArrayList<>();
//         for(int i=0; i<m; i++){
//             for(int j=0; j<n; j++){
//                 dfs(i, j, board, root, new StringBuilder(), result);
//             }
//         }
//         return result;

//     }

//     private void dfs(int i, int j, char[][] board,TrieNode node, StringBuilder cur, List<String> result){

//         if(i<0 || j<0 || i>=board.length || j>=board[0].length) return;

//         if(board[i][j] == '#') return;  //already visited

//         int index = board[i][j] - 'a';
//         if(node.children[index] == null) return;    // board character not present in trie


//         cur.append(board[i][j]);
//         board[i][j] = '#';   // visited
//         node = node.children[index];

//         if(node.isWord){
//             result.add(cur.toString());
//             node.isWord = false;    // to not considder duplicate words
//         }

//         dfs(i,j+1, board, node, cur, result);
//         dfs(i+1,j, board, node, cur, result);
//         dfs(i,j-1, board, node, cur, result);
//         dfs(i-1,j, board, node, cur, result);

//         board[i][j] = (char) ((int)'a' + index);
//         cur.deleteCharAt(cur.length() - 1);

//     }

//     private void insert(String word){
//         TrieNode node = root;
//         for(char c: word.toCharArray()){
//             int index = c - 'a';
//             if(node.children[index] == null){
//                 node.children[index] = new TrieNode();
//             }
//             node = node.children[index];
//         }
//         node.isWord = true;
//     }

//     class TrieNode{
//         TrieNode[] children;
//         boolean isWord;

//         public TrieNode(){
//             children = new TrieNode[26];
//             isWord = false;
//         }
//     }

    // optimized
    private TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {

        for(String word: words){
            insert(word);
        }

        int m = board.length;
        int n = board[0].length;

        List<String> result = new ArrayList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                dfs(i, j, board, root, result);
            }
        }
        return result;

    }

    private void dfs(int i, int j, char[][] board,TrieNode node, List<String> result){

        if(i<0 || j<0 || i>=board.length || j>=board[0].length) return;

        if(board[i][j] == '#') return;  //already visited

        int index = board[i][j] - 'a';
        if(node.children[index] == null) return;    // board character not present in trie

        board[i][j] = '#';   // visited
        node = node.children[index];

        if(node.word != null){
            result.add(node.word);
            node.word = null;    // to not consider duplicate words
        }

        dfs(i,j+1, board, node, result);
        dfs(i+1,j, board, node, result);
        dfs(i,j-1, board, node, result);
        dfs(i-1,j, board, node, result);

        board[i][j] = (char) ((int)'a' + index);

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
        node.word = word;
    }

    class TrieNode{
        TrieNode[] children;
        String word;

        public TrieNode(){
            children = new TrieNode[26];
        }
    }
}
