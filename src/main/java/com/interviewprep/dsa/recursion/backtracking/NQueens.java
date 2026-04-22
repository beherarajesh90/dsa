package com.interviewprep.dsa.recursion.backtracking;

import java.util.*;

//https://leetcode.com/problems/n-queens/description/
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        //fill '.' into board initially
        for(char[] ch: board) Arrays.fill(ch, '.');
        backtrack(result, new HashSet<>(), new HashSet<>(), new HashSet<>(), board, n, 0);
        return result;
    }

    private void backtrack(List<List<String>> result, Set<Integer> cols, Set<Integer> diag1, Set<Integer> diag2, char[][] board, int n, int row) {
        if (row == n){
            List<String> selectedRow = new ArrayList<>();
            for (char[] r: board) selectedRow.add(new String(r));
            result.add(selectedRow);
            return;
        }

        for (int col = 0; col<n; col++){
            if (cols.contains(col) || diag2.contains(row - col) || diag1.contains(row + col)){
                continue;
            }
            cols.add(col);           // add column
            diag2.add(row - col);    // add negative diagonal
            diag1.add(row + col);    // add positive diagonal
            board[row][col] = 'Q';   // add Queen on board
            backtrack(result, cols, diag1, diag2, board, n, row+1);
            board[row][col] = '.';          // remove Queen
            diag1.remove(row + col);     // remove positive diagonal
            diag2.remove(row - col);     // remove negative diagonal
            cols.remove(col);               // remove column
        }
    }

    //using arrays for faster checking
    public List<List<String>> solveNQueensOptimized(int n) {
        List<List<String>> result = new ArrayList<>();
        boolean[] cols = new boolean[n];
        boolean[] diag = new boolean[2*n-1];
        boolean[] antiDiag = new boolean[2*n-1];
        char[][] board = new char[n][n];
        //fill '.' into board initially
        for(char[] ch: board) Arrays.fill(ch, '.');
        backtrackOptimized(result, cols, diag, antiDiag, board, n, 0);
        return result;
    }

    private void backtrackOptimized(List<List<String>> result, boolean[] cols, boolean[] diag, boolean[] antiDiag, char[][] board, int n, int row) {
        if (row == n){
            List<String> selectedRow = new ArrayList<>();
            for (char[] r: board) selectedRow.add(new String(r));
            result.add(selectedRow);
            return;
        }

        for (int col = 0; col<n; col++){
            if (cols[col] || antiDiag[(n-1) + (col - row)] || diag[row + col]){
                continue;
            }
            cols[col]=true;                          // add column
            antiDiag[(n-1) + (col - row)] = true;    // add negative diagonal
            diag[row + col] = true;                  // add positive diagonal
            board[row][col] = 'Q';                   // add Queen on board
            backtrackOptimized(result, cols, diag, antiDiag, board, n, row+1);
            board[row][col] = '.';                   // remove Queen
            diag[row + col] = false;                 // remove positive diagonal
            antiDiag[(n-1) + (col - row)] = false;   // remove negative diagonal
            cols[col] = false;                       // remove column
        }
    }
}
