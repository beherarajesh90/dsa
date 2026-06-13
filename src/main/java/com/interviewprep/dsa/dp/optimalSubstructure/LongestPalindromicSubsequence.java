package com.interviewprep.dsa.dp.optimalSubstructure;

import java.util.Arrays;

//https://leetcode.com/problems/longest-palindromic-subsequence/description/
public class LongestPalindromicSubsequence {
    private int[][] dp;
    public int longestPalindromeSubseq(String s) {
        // return lcsTab(s, new StringBuilder(s).reverse().toString());

        // int n = s.length();
        // int res = 0;
        // for(int i=0; i<n; i++){
        //     // dfs(i, i, s) for odd length palindromes
        //     // dfs(i, i+1) for even length palindromes
        //     res = Math.max(res, Math.max(dfs(i, i, n, s) , dfs(i, i+1, n, s)));
        // }
        // return res;

        // int n = s.length();
        // int res = 0;
        // dp = new int[n][n];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }
        // for(int i=0; i<n; i++){
        //     // dfs(i, i, s) for odd length palindromes
        //     // dfs(i, i+1) for even length palindromes
        //     res = Math.max(res, Math.max(longestPalindromeSubseqMemo(i, i, n, s) , longestPalindromeSubseqMemo(i, i+1, n, s)));
        // }
        // return res;

        // int n = s.length();
        // dp = new int[n][n];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }
        // return longestPalindromeSubseqMemo2(0, n-1, s);

        // return longestPalindromeSubseqTab2(s);

        return longestPalindromeSubseqTab3(s);
    }

    //tabulation using Longest Common Seq (LCS) approach
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

    //other approach
    //recursive
    private int dfs(int i, int j, int n, String s){
        if(i<0 || j == n) return 0;

        if(s.charAt(i) == s.charAt(j)){
            int len = (i == j) ? 1 : 2;
            return len + dfs(i-1, j+1, n, s);
        } else{
            return Math.max(dfs(i-1, j, n, s), dfs(i, j+1, n, s));
        }
    }

    //memoization
    private int longestPalindromeSubseqMemo(int i, int j, int n, String s){
        if(i<0 || j == n) return 0;

        if(dp[i][j]!=-1) return dp[i][j];

        if(s.charAt(i) == s.charAt(j)){
            int len = (i == j) ? 1 : 2;
            dp[i][j] = len + longestPalindromeSubseqMemo(i-1, j+1, n, s);
        } else{
            dp[i][j] = Math.max(longestPalindromeSubseqMemo(i-1, j, n, s), longestPalindromeSubseqMemo(i, j+1, n, s));
        }

        return dp[i][j];
    }

    //memoization optimized
    private int longestPalindromeSubseqMemo2(int i, int j, String s){
        if(i > j) return 0;

        if(i == j) return 1;    //single character is a palindrome of length 1

        if(dp[i][j]!=-1) return dp[i][j];

        if(s.charAt(i) == s.charAt(j)){
            dp[i][j] = 2 + longestPalindromeSubseqMemo2(i+1, j-1, s);
        } else{
            dp[i][j] = Math.max(longestPalindromeSubseqMemo2(i+1, j, s), longestPalindromeSubseqMemo2(i, j-1, s));
        }

        return dp[i][j];
    }

    //tabulation
    private int longestPalindromeSubseqTab2(String s){
        int n = s.length();
        int[][] dp = new int[n][n];

        //dp[i][j] Longest Palindromic Subsequence inside the substring s[i...j]
        for(int i=n-1; i>=0; i--){
            dp[i][i] = 1;
            for(int j=i+1; j<n; j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][n-1];
    }

    //tabulation space optimized
    private int longestPalindromeSubseqTab3(String s){
        int n = s.length();
        int[] dp = new int[n];

        //dp[i][j] Longest Palindromic Subsequence inside the substring s[i...j]
        for(int i=n-1; i>=0; i--){
            dp[i] = 1;
            int prevDiagVal = 0;
            for(int j=i+1; j<n; j++){
                int temp = dp[j];
                if(s.charAt(i) == s.charAt(j)){
                    dp[j] = 2 + prevDiagVal;
                } else{
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
                prevDiagVal = temp;
            }
        }

        return dp[n-1];
    }

    //algo master approach

    public int longestPalindromeSubseqMemoAM(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return solve(s, 0, n - 1, memo);
    }

    private int solve(String s, int i, int j, int[][] memo) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (memo[i][j] != -1) return memo[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = solve(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(solve(s, i + 1, j, memo), solve(s, i, j - 1, memo));
        }
        return memo[i][j];
    }

    public int longestPalindromeSubseqTabAM(String s) {
        int n = s.length();
        int[] dp = new int[n];

        // Base case: every character is a palindrome of length 1
        Arrays.fill(dp, 1);

        // Process rows from bottom to top
        for (int i = n - 2; i >= 0; i--) {
            int prev = 0; // Stores old dp[j-1] before overwrite
            for (int j = i + 1; j < n; j++) {
                int temp = dp[j]; // Save current value before overwrite
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = prev + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = temp;
            }
        }

        return dp[n - 1];
    }
}
