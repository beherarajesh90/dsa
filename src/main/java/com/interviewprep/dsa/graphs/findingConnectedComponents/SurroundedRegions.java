package com.interviewprep.dsa.graphs.findingConnectedComponents;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/surrounded-regions/description/
public class SurroundedRegions {
    public void solve(char[][] board) {
        // solveUsingDFS(board);

        // solveUsingDFSOptimal(board);

        solveUsingDSU(board);
    }

    // go through all the elements and perform dfs. if the region touches borders do not mark x else mark.
    private void solveUsingDFS(char[][] board){
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                boolean[] touchesBorder = {false};
                List<int[]> region = new ArrayList<>();
                if(board[i][j] == 'O' && !visited[i][j]){
                    dfs(i, j, touchesBorder, region, board, visited);
                }

                if(!touchesBorder[0]){
                    for(int[] cell: region){
                        board[cell[0]][cell[1]] = 'X';
                    }
                }
            }
        }
    }

    private void dfs(int i, int j, boolean[] touchesBorder, List<int[]> region, char[][] board, boolean[][] visited){
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j] == 'X' || visited[i][j]){
            return;
        }
        visited[i][j] = true;
        region.add(new int[]{i, j});

        if(i == 0 || i == board.length-1 || j == 0 || j == board[0].length-1){
            touchesBorder[0] = true;
        }

        dfs(i, j+1, touchesBorder, region, board, visited);
        dfs(i+1, j, touchesBorder, region, board, visited);
        dfs(i, j-1, touchesBorder, region, board, visited);
        dfs(i-1, j, touchesBorder, region, board, visited);
    }

    //go through the borders and mark Os and connected Os as safe(S). Go through all the elements if 0s found mark X, if S found mark O.
    private void solveUsingDFSOptimal(char[][] board){

        int m = board.length;
        int n = board[0].length;

        //mark all the border 0s as safe
        for(int i=0; i<m; i++){
            if(board[i][0] == 'O') markSafe(i,0, board);
            if(board[i][n-1] == 'O') markSafe(i,n-1, board);
        }

        for(int j=0; j<n; j++){
            if(board[0][j] == 'O') markSafe(0, j, board);
            if(board[m-1][j] == 'O') markSafe(m-1, j, board);
        }

        //sweep: mark S as 0
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 'S') board[i][j]='O';
                else if(board[i][j] == 'O') board[i][j]='X';
            }
        }
    }

    private void markSafe(int i, int j, char[][] board){
        if(i<0 || i>=board.length || j<0 || j>=board[0].length) return;

        if(board[i][j] == 'X' || board[i][j] == 'S') return;

        board[i][j] = 'S';

        markSafe(i, j+1, board);
        markSafe(i+1, j, board);
        markSafe(i, j-1, board);
        markSafe(i-1, j, board);
    }

    //solve using Disjoint Set Union
    private void solveUsingDSU(char[][] board){
        int m = board.length;
        int n = board[0].length;
        int sentinel = m * n;   //dummy index
        DSU dsu = new DSU(m*n + 1);

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 'O'){
                    int cell = i*n + j;
                    //if O lies on borders connect its parent to sentinel
                    if(i == 0 || i == m-1 || j == 0 || j == n-1){
                        dsu.union(cell, sentinel);
                    }

                    //union right and down
                    if(i+1 < m && board[i+1][j] == 'O'){
                        dsu.union(cell, (i+1) * n + j);
                    }

                    if(j+1 < n && board[i][j+1] == 'O'){
                        dsu.union(cell, i * n + (j+1));
                    }
                }
            }
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 'O' && dsu.find(i * n + j) != dsu.find(sentinel)){
                    board[i][j] = 'X';
                }
            }
        }


    }

    class DSU{
        int[] parent;
        int[] rank;

        public DSU(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i=0; i<n; i++){
                parent[i] = i;
                rank[i] = 1;
            }
        }

        private int find(int x){
            if(parent[x] == x) return x;

            return parent[x] = find(parent[x]);
        }

        private void union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);
            if(rootA == rootB) return;

            if(rank[rootA] < rank[rootB]){
                parent[rootA] = rootB;
                rank[rootB] += rank[rootA];
            } else{
                parent[rootB] = rootA;
                rank[rootA] += rank[rootB];
            }
        }
    }
}
