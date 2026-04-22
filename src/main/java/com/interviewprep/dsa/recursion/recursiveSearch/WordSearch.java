package com.interviewprep.dsa.recursion.recursiveSearch;

//https://leetcode.com/problems/word-search/description/
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(backtrack(board, visited, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, boolean[][] visited, String word, int x, int y, int index){

        if(index == word.length()) return true;

        if(x <0 || y<0 || x>=board.length || y>=board[0].length || visited[x][y] || board[x][y]!=word.charAt(index)) return false;

        visited[x][y] = true;
        boolean found = backtrack(board, visited, word, x, y+1, index+1) || backtrack(board, visited, word, x+1, y, index+1)
                || backtrack(board, visited, word, x, y-1, index+1) || backtrack(board, visited, word, x-1, y, index+1);
        visited[x][y] = false;

        return found;
    }
}
