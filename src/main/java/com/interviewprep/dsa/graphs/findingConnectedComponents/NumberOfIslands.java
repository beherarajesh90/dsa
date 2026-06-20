package com.interviewprep.dsa.graphs.findingConnectedComponents;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {

        // int m = grid.length;
        // int n = grid[0].length;

        // int count = 0;
        // for(int i=0; i<m; i++){
        //     for(int j=0; j<n; j++){
        //         if(grid[i][j] == '1'){
        //             count++;
        //             // dfs(i, j, m, n, grid);   // dfs - stackoverflow for larger inputs
        //             bfs(i, j, m, n, grid);      //bfs
        //         }
        //     }
        // }
        // return count;

        return numIslandsDSU(grid);
    }

    //dfs
    private void dfs(int r, int c, int m, int n, char[][] grid){
        if(r<0 || r>=m || c<0 || c>=n || grid[r][c] == '0'){
            return;
        }

        //sink the current cell to mark as visited
        grid[r][c] = '0';

        dfs(r, c+1, m, n, grid);
        dfs(r+1, c, m, n, grid);
        dfs(r, c-1, m, n, grid);
        dfs(r-1, c, m, n, grid);
    }

    //bfs
    private void bfs(int r, int c, int m, int n, char[][] grid){

        grid[r][c] = '0';
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while(!q.isEmpty()){
            int[] cell = q.poll();
            for(int[] dir: directions){
                int nr = cell[0] + dir[0];
                int nc = cell[1] + dir[1];
                if(nr >=0 && nr <m && nc>=0 && nc<n && grid[nr][nc] == '1'){
                    // mark visited
                    grid[nr][nc] = '0';
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }

    public int numIslandsDSU(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        DSU dsu = new DSU(m*n);

        int islands = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                // initially each every element is an island of its own
                if(grid[i][j] == '1'){
                    islands++;
                }
            }
        }

        int[][] dirs = {{0,1},{1,0}};
        //consider right and down directions only. By the time we reach some element left and up is not required as the elements would have been already connected

        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(grid[r][c] == '0')
                    continue;

                int id1 = r * n + c;
                for(int[] dir: dirs){
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr<m && nc<n && grid[nr][nc] == '1'){
                        int id2 = nr * n + nc;
                        if(dsu.union(id1, id2)){
                            // merged
                            islands--;
                        }
                    }
                }
            }
        }

        return islands;
    }

    class DSU{
        int[] parent;
        int[] size;

        public DSU(int n){
            parent = new int[n];
            size = new int[n];

            for(int i=0; i<n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int x){
            if(parent[x] == x){
                return x;
            }
            return parent[x] = find(parent[x]);     //path compression
        }

        private boolean union(int a, int b){

            int rootA = find(a);
            int rootB = find(b);

            if(rootA == rootB){
                return false;
            }

            if(size[rootA] < size[rootB]){
                parent[rootA] = rootB;
                size[rootB] += size[rootA];
            } else{
                parent[rootB] = rootA;
                size[rootA] += size[rootB];
            }

            return true;
        }
    }
}
