package com.interviewprep.dsa.dp.intervalOrRangeDP;

//https://leetcode.com/problems/strange-printer/description/
public class StrangePrinter {
    private int[][] dp;
    public int strangePrinter(String s) {
        // int n = s.length();
        // return dfs(0, n-1, s);

        // int n = s.length();
        // dp = new int[n][n];
        // for(int[] arr: dp) Arrays.fill(arr, -1);
        // return strangePrinterMemo(0, n-1, s);

        return strangePrinterTab(s);
    }

    //recursive
    private int dfs(int left, int right, String s){
        if(left == right) return 1;

        int minTurns = Integer.MAX_VALUE;
        for(int k=left; k<right; k++){
            int leftPart = dfs(left, k, s);
            int rightPart = dfs(k+1, right, s);
            minTurns = Math.min(minTurns, leftPart + rightPart);
        }

        //edge case
        return s.charAt(left) == s.charAt(right) ? minTurns - 1 : minTurns;
    }

    //memoization
    private int strangePrinterMemo(int left, int right, String s){
        if(left == right) return 1;

        if(dp[left][right] != -1) return dp[left][right];

        int minTurns = Integer.MAX_VALUE;
        for(int k=left; k<right; k++){
            int leftPart = strangePrinterMemo(left, k, s);
            int rightPart = strangePrinterMemo(k+1, right, s);
            minTurns = Math.min(minTurns, leftPart + rightPart);
        }

        //edge case
        return dp[left][right] = s.charAt(left) == s.charAt(right) ? minTurns - 1 : minTurns;
    }

    //tabulation
    private int strangePrinterTab(String s){
        int n = s.length();
        int[][] dp = new int[n][n];


        for(int i=0; i<n; i++){
            dp[i][i] = 1;
        }

        for(int gap = 2; gap <= n; gap++){
            for(int left = 0; left + gap - 1 < n; left++){
                int right = left + gap - 1;

                int minTurns = Integer.MAX_VALUE;
                for(int k=left; k<right; k++){
                    int leftPart = dp[left][k];
                    int rightPart = dp[k+1][right];
                    minTurns = Math.min(minTurns, leftPart + rightPart);
                }

                dp[left][right] = s.charAt(left) == s.charAt(right) ? minTurns - 1 : minTurns;
            }
        }

        return dp[0][n-1];
    }
}
