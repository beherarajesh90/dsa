package com.interviewprep.dsa.dp.knapsack;

//https://leetcode.com/problems/ones-and-zeroes/
public class OnesAndZeroes {
    private int[][][] dp;
    private int[][] arr;
    public int findMaxForm(String[] strs, int m, int n) {

        // int[][] arr = new int[strs.length][2];
        // for(int i=0; i<strs.length; i++){
        //     for(char c : strs[i].toCharArray()){
        //         arr[i][c - '0']++;
        //     }
        // }

        // return dfs(0, m, n, arr);

        // arr = new int[strs.length][2];
        // for(int i=0; i<strs.length; i++){
        //     for(char c : strs[i].toCharArray()){
        //         arr[i][c - '0']++;
        //     }
        // }

        // dp = new int[strs.length][m+1][n+1];
        // for(int i=0; i<strs.length; i++){
        //     for(int j=0; j<=m; j++){
        //         for(int k=0; k<=n; k++){
        //             dp[i][j][k] = -1;
        //         }
        //     }
        // }
        // return findMaxFormMemo(0, m, n);

        return findMaxFormTab(strs, m, n);
    }

    //recursive
    private int dfs(int i, int m, int n, int[][] arr){
        if(i == arr.length) return 0;

        int res = dfs(i+1, m, n, arr);

        if(m>=arr[i][0] && n>=arr[i][1]){
            res = Math.max(res, 1 + dfs(i+1, m - arr[i][0], n - arr[i][1], arr));
        }

        return res;
    }

    //memoization
    private int findMaxFormMemo(int i, int m, int n){
        if(i == arr.length) return 0;

        if(dp[i][m][n]!=-1) return dp[i][m][n];

        dp[i][m][n] = findMaxFormMemo(i+1, m, n);

        if(m>=arr[i][0] && n>=arr[i][1]){
            dp[i][m][n] = Math.max(dp[i][m][n], 1 + findMaxFormMemo(i+1, m - arr[i][0], n - arr[i][1]));
        }

        return dp[i][m][n];
    }

    //tabulation
    private int findMaxFormTab(String[] strs, int m, int n){
        int[][] arr = new int[strs.length][2];
        for(int i=0; i<strs.length; i++){
            for(char c : strs[i].toCharArray()){
                arr[i][c - '0']++;
            }
        }

        int[][][] dp = new int[strs.length+1][m+1][n+1];

        for(int i=1; i<=strs.length; i++){
            for(int j=0; j<=m; j++){
                for(int k=0; k<=n; k++){
                    dp[i][j][k] = dp[i-1][j][k];
                    if(j>=arr[i-1][0] && k>=arr[i-1][1]){
                        dp[i][j][k] = Math.max(dp[i][j][k], 1 + dp[i-1][j - arr[i-1][0]][k - arr[i-1][1]]);
                    }
                }
            }
        }

        return dp[strs.length][m][n];
    }

    //tabulation space optimized
    private int findMaxFormTabOptimized(String[] strs, int m, int n){
        int[][] arr = new int[strs.length][2];
        for(int i=0; i<strs.length; i++){
            for(char c : strs[i].toCharArray()){
                arr[i][c - '0']++;
            }
        }

        int[][] dp = new int[m+1][n+1];

        for(int[] num: arr){
            int zeroes = num[0], ones = num[1];
            for(int i=m; i>=zeroes; i--){
                for(int j=n; j>=ones; j--){
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - zeroes][j - ones]);
                }
            }
        }

        return dp[m][n];
    }
}
