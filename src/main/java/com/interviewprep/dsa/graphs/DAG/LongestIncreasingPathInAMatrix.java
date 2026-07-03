package com.interviewprep.dsa.graphs.DAG;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/
public class LongestIncreasingPathInAMatrix {
    private static final int[][] DIRS = {{0,1},{1,0},{0,-1},{-1,0}};
    private int[][] memo;

    public int longestIncreasingPath(int[][] matrix) {
        // return longestIncreasingPathMemo(matrix);

        return longestIncreasingPathTopoSort(matrix);
    }

    private int longestIncreasingPathTopoSort(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] inDegree = new int[m][n];

        // find indegrees of every element in matrix
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                for(int[] dir: DIRS){
                    int nr = i + dir[0];
                    int nc = j + dir[1];
                    if(nr<0 || nc <0 || nr>=m || nc >=n) continue;
                    if(matrix[i][j] > matrix[nr][nc]){
                        inDegree[i][j]++;
                    }
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();

        // add elements with 0 indegree in queue
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(inDegree[i][j] == 0)
                    q.offer(new int[]{i,j});
            }
        }
        int levels = 0;
        while(!q.isEmpty()){
            levels++;
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                for(int[] dir: DIRS){
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr<0 || nc <0 || nr>=m || nc>=n) continue;

                    // Only traverse outgoing edges (smaller -> larger)
                    if(matrix[nr][nc] > matrix[r][c]){
                        inDegree[nr][nc]--;
                        if(inDegree[nr][nc] == 0)
                            q.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return levels;
    }


    // bruteforce time: O(4^(m*n))
    // memoization time: O(m*n)
    private int longestIncreasingPathMemo(int[][] matrix){

        int m = matrix.length;
        int n = matrix[0].length;

        ///memoization
        memo = new int[m][n];
        for(int[] arr: memo){
            Arrays.fill(arr, -1);
        }

        int res = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int path = dfs(i, j, matrix);
                res = Math.max(res, path);
            }
        }
        return res;
    }

    private int dfs(int r, int c, int[][] matrix){

        if(memo[r][c] != -1) return memo[r][c];

        int bestPath = 1;

        for(int[] dir: DIRS){
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr>=0 && nr<matrix.length && nc>=0 && nc<matrix[0].length && matrix[r][c] < matrix[nr][nc]){
                bestPath = Math.max(bestPath, 1 + dfs(nr, nc, matrix));
            }
        }

        memo[r][c] = bestPath;

        return memo[r][c];
    }
}
