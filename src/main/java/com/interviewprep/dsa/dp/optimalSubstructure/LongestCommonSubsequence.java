package com.interviewprep.dsa.dp.optimalSubstructure;

public class LongestCommonSubsequence {
    private int[][] dp;
    public int longestCommonSubsequence(String text1, String text2) {

        // return dfs(0, 0, text1, text2);

        // int m = text1.length();
        // int n = text2.length();
        // dp = new int[m][n];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }
        // return lcsMemo(0, 0, m, n, text1, text2);

        return lcsTab(text1, text2);
    }

    //recursive
    private int dfs(int i, int j, String text1, String text2){
        if(i == text1.length() || j == text2.length()) return 0;

        if(text1.charAt(i) == text2.charAt(j)){
            return 1 + dfs(i+1, j+1, text1, text2);
        }

        return Math.max(dfs(i+1, j, text1, text2), dfs(i, j+1, text1, text2));
    }

    //memoization
    private int lcsMemo(int i, int j, int m, int n, String text1, String text2){
        if(i == m || j == n) return 0;

        if(dp[i][j]!=-1){
            return dp[i][j];
        }

        if(text1.charAt(i) == text2.charAt(j)){
            dp[i][j] = 1 + lcsMemo(i+1, j+1, m , n, text1, text2);
        } else{
            dp[i][j] = Math.max(lcsMemo(i+1, j, m, n, text1, text2), lcsMemo(i, j+1, m, n, text1, text2));
        }

        return dp[i][j];
    }

    //tabulation
    private int lcsTab(String text1, String text2){
        int m = text1.length();
        int n = text2.length();
        if(m < n){
            int tmpLen = m;
            m = n;
            n = tmpLen;

            String tmpText = text1;
            text1 = text2;
            text2 = tmpText;
        }

        int[] dp = new int[n+1];
        int[] nextDp = new int[n+1];

        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                if(text1.charAt(i) == text2.charAt(j)){
                    nextDp[j] = 1 + dp[j+1];
                } else{
                    nextDp[j] = Math.max(dp[j], nextDp[j+1]);
                }
            }
            System.arraycopy(nextDp, 0, dp ,0 , n+1);
        }

        return nextDp[0];
    }
}
