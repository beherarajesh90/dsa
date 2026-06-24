package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/shortest-path-in-binary-matrix/description/
public class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        // return shortestPathBinaryMatrixDFS(grid);

        // return shortestPathBinaryMatrixDFS2(grid);

        return shortestPathBinaryMatrixBiDirectinalDFS(grid);
    }

    // Time: O(n^2), space: O(n^2), search space decreases by half as we are searching from both ends
    private int shortestPathBinaryMatrixBiDirectinalDFS(int[][] grid) {
        int n = grid.length;

        if(grid[0][0] == 1 || grid[n-1][n-1] == 1 ) return -1;

        if(n == 1) return 1;

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,-1},{-1,1}};

        Queue<int[]> q1 = new LinkedList<>();
        q1.offer(new int[]{0,0});    //(i,j)

        Queue<int[]> q2 = new LinkedList<>();
        q2.offer(new int[]{n-1,n-1});    //(i,j)

        int start=-1, end=-2, len = 2;
        grid[0][0] = start;
        grid[n-1][n-1] = end;

        //  process q1 and q2 one after other
        while(!q1.isEmpty() && !q2.isEmpty()){
            // add all vertices of the same level
            for(int i=q1.size(); i>0; i--){
                int[] cur = q1.poll();
                int r = cur[0];
                int c = cur[1];

                for(int[] dir: dirs){
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr>=0 && nr<n && nc>=0 && nc<n){
                        if(grid[nr][nc] == end) return len;
                        else if(grid[nr][nc] == 0){
                            grid[nr][nc] = start;
                            q1.offer(new int[]{nr,nc});
                        }
                    }
                }
            }
            len++;

            //swap queues and end
            Queue<int[]> tempQ = q1;
            q1 = q2;
            q2 = tempQ;

            int temp = start;
            start = end;
            end = temp;
        }

        return -1;
    }

    // Time: O(n^2), space: O(n^2), store length in grid itself
    private int shortestPathBinaryMatrixDFS2(int[][] grid) {
        int n = grid.length;

        //edge case
        if(n == 1 && grid[0][0]==0) return 1;

        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        int[][] dirs = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});   //(i,j)
        grid[0][0] = 1;

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            //return the length stored in grid
            if(r == n-1 && c == n-1) return grid[r][c];

            for(int[] dir: dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];
                if(nr>=0 && nc>=0 && nr<n && nc<n && grid[nr][nc] == 0 && !visited[nr][nc]){
                    q.offer(new int[]{nr, nc});
                    grid[nr][nc] = grid[r][c]+1;
                    visited[nr][nc] = true;
                }
            }
        }

        return -1;

    }


    // Time: O(n^2), space: O(n^2)
    private int shortestPathBinaryMatrixDFS(int[][] grid) {
        int n = grid.length;

        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        int[][] dirs = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0,1});   //(i,j,length)

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int len = cur[2];

            if(r == n-1 && c == n-1) return len;

            for(int[] dir: dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];
                if(nr>=0 && nc>=0 && nr<n && nc<n && grid[nr][nc] == 0 && !visited[nr][nc]){
                    q.offer(new int[]{nr, nc, len+1});
                    visited[nr][nc] = true;
                }
            }
        }

        return -1;

    }
}
