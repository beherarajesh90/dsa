package com.interviewprep.dsa.dp.counting;

//https://leetcode.com/problems/distinct-subsequences/description/
public class DistinctSubsequences {
    private int[][] dp;
    public int numDistinct(String s, String t) {
        // return dfs(0, 0, s, t);

        // int m = s.length(), n = t.length();
        // dp = new int[m][n];
        // for(int[] arr: dp) Arrays.fill(arr, -1);
        // return numDistinctMemo(0, 0, s, t);

        // return numDistinctTab(s, t);

        return numDistinctTabOptimized(s, t);
    }

    //recursive
    private int dfs(int i, int j, String s, String t){
        if(j == t.length()) return 1;

        if(i == s.length()) return 0;

        int count = 0;
        if(s.charAt(i) == t.charAt(j)){
            count = dfs(i+1, j+1, s, t) + dfs(i+1, j, s, t);
        } else{
            count = dfs(i+1, j, s, t);
        }
        return count;
    }

    //memoization
    private int numDistinctMemo(int i, int j, String s, String t){
        if(j == t.length()) return 1;

        if(i == s.length()) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int count = 0;
        if(s.charAt(i) == t.charAt(j)){
            count = numDistinctMemo(i+1, j+1, s, t) + numDistinctMemo(i+1, j, s, t);
        } else{
            count = numDistinctMemo(i+1, j, s, t);
        }
        return dp[i][j] = count;
    }

    //tabulation
    private int numDistinctTab(String s, String t){
        int m = s.length(), n = t.length();

        //dp[i][j] indicates count of subseq formed using s[i..] to achieve targett[j..]
        int[][] dp = new int[m+1][n+1];

        //Empty target can always be formed
        for(int i=0; i<=m; i++){
            dp[i][n] = 1;
        }

        //cannot form target if the source is empty
        for(int j=0; j<n; j++){
            dp[m][j] = 0;
        }

        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                if(s.charAt(i) == t.charAt(j)){
                    dp[i][j] = dp[i+1][j+1] + dp[i+1][j];
                } else{
                    dp[i][j] = dp[i+1][j];
                }
            }
        }

        return dp[0][0];
    }

    //tabulation optimized
    private int numDistinctTabOptimized(String s, String t){
        int m = s.length(), n = t.length();

        // dp[j] number of ways to form t[0..j-1]

//         Suffix DP (dp[i][j] = ways to form t[j...] from s[i...]) → easiest compression is 2 rows.
//         Prefix DP (dp[j] = ways to form first j chars of target) → can be compressed to 1 row.
        int[] dp = new int[n+1];
        dp[0] = 1;

        for(int i=0; i<m; i++){
            for(int j=n-1; j>=0; j--){
                if(s.charAt(i) == t.charAt(j)){
                    dp[j+1] += dp[j];
                }
            }
        }

        return dp[n];
    }

    //tabulation optimized 2 (different way to write the above solution)
    private int numDistinctTabOptimized2(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            // Iterate right to left to preserve previous row's values
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}
