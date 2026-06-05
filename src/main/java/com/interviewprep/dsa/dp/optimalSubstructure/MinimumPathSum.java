package com.interviewprep.dsa.dp.optimalSubstructure;

//https://leetcode.com/problems/minimum-path-sum/description/
public class MinimumPathSum {
    private int[][] dp;

    public int minPathSum(int[][] grid) {
        // return minPathSumRec(grid, 0, 0);

        // int m = grid.length, n = grid[0].length;
        // dp = new int[m][n];
        // for(int i=0; i<m; i++){
        //     for(int j=0; j<n; j++){
        //         dp[i][j] = -1;
        //     }
        // }
        // return minPathSumMemoDFS(grid, 0, 0);

        // return minPathSumTab(grid);

        return minPathSumTabSpaceOpt(grid);
    }

    //recursive
    private int minPathSumRec(int[][] grid, int r, int c){
        if(r == grid.length-1 && c == grid[0].length-1) return grid[r][c];
        if(r == grid.length || c == grid[0].length) return Integer.MAX_VALUE;
        return grid[r][c] + Math.min(minPathSumRec(grid, r, c+1),minPathSumRec(grid, r+1, c));
    }

    //memoization
    private int minPathSumMemoDFS(int[][] grid, int r, int c){
        if(r == grid.length-1 && c == grid[0].length-1) return grid[r][c];
        if(r == grid.length || c == grid[0].length) return Integer.MAX_VALUE;

        if(dp[r][c]!=-1){
            return dp[r][c];
        }

        dp[r][c] = grid[r][c] + Math.min(minPathSumMemoDFS(grid, r, c+1),minPathSumMemoDFS(grid, r+1, c));

        return dp[r][c];
    }

    //tabulation
    private int minPathSumTab(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m+1][n+1];
        for(int r=m; r>=0; r--){
            for(int c=n; c>=0; c--){
                dp[r][c] = Integer.MAX_VALUE;
            }
        }

        //mark the right element of last element in grid as 0
        dp[m-1][n] = 0;

        for(int r=m-1; r>=0; r--){
            for(int c=n-1; c>=0; c--){
                dp[r][c] = grid[r][c] + Math.min(dp[r][c+1],dp[r+1][c]);
            }
        }

        return dp[0][0];
    }

    //tabulation space optimized
    private int minPathSumTabSpaceOpt(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n+1];
        for(int i=0; i<=n; i++){
            dp[i] = Integer.MAX_VALUE;
        }

        //path from self is 0
        dp[n-1] = 0;

        for(int r=m-1; r>=0; r--){
            for(int c=n-1; c>=0; c--){
                dp[c] = grid[r][c] + Math.min(dp[c], dp[c+1]);
            }
        }

        return dp[0];
    }
}
