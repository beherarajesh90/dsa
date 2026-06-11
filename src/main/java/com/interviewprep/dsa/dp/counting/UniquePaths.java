package com.interviewprep.dsa.dp.counting;

import java.util.Arrays;

//https://leetcode.com/problems/unique-paths/
public class UniquePaths {
    private int[][] dp;
    public int uniquePaths(int m, int n) {
        // return dfs(0, 0, m, n);

        // dp = new int[m][n];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }
        // return uniquePathsMemo(0, 0, m, n);

        // return uniquePathsTab(m, n);

        // return uniquePathsTabOptimized(m, n);

        return uniquePathsTabOptimal(m, n);
    }

    //recursive
    private int dfs(int i, int j,int m, int n){
        if(i >= m || j >= n) return 0;

        if(i == m-1 && j == n-1) return 1;

        return dfs(i+1, j, m, n) + dfs(i, j+1, m, n);
    }

    //memoization
    private int uniquePathsMemo(int i, int j,int m, int n){
        if(i >= m || j >= n) return 0;

        if(i == m-1 && j == n-1) return 1;

        if(dp[i][j]!=-1) return dp[i][j];

        dp[i][j] = uniquePathsMemo(i+1, j, m, n) + uniquePathsMemo(i, j+1, m, n);

        return dp[i][j];
    }

    //tabulation
    private int uniquePathsTab(int m, int n){
        // dp[i][j] represents: Number of unique ways to reach the destination (m-1, n-1) from cell (i, j).
        int[][] dp = new int[m+1][n+1];
        dp[m-1][n-1] = 1;   //Exactly 1 way — do nothing and stay there.
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                dp[i][j] += dp[i+1][j] + dp[i][j+1];
            }
        }
        return dp[0][0];
    }

    //tabulation space optimized
    private int uniquePathsTabOptimized(int m, int n){
        int[] row = new int[n];
        Arrays.fill(row, 1);
        for(int i=m-2; i>=0; i--){
            int[] newRow = new int[n];
            // Arrays.fill(newRow, 1);
            newRow[n-1] = 1;
            for(int j=n-2; j>=0; j--){
                newRow[j] = newRow[j+1] + row[j];
            }
            row = newRow;
        }
        return row[0];
    }

    //tabulation space optimal
    private int uniquePathsTabOptimal(int m, int n){
        int[] row = new int[n];
        Arrays.fill(row, 1);
        for(int i=m-2; i>=0; i--){
            for(int j=n-2; j>=0; j--){
                row[j] += row[j+1];
            }
        }
        return row[0];
    }

    //explore the math technique to find the paths using combinations ( optimal)
}
