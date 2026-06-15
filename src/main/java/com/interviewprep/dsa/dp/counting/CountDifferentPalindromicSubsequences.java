package com.interviewprep.dsa.dp.counting;

import java.util.Arrays;
import java.util.Set;

//https://leetcode.com/problems/count-different-palindromic-subsequences/description/
public class CountDifferentPalindromicSubsequences {
    private static final int MOD = 1_000_000_007;

    private Set<String> palindromes;
    private long[][] dp;

    public int countPalindromicSubsequences(String s) {
        // palindromes = new HashSet<>();
        // dfs(0, s, new StringBuilder());
        // return palindromes.size();

        // int n = s.length();
        // return (int)dfs2(0, n-1, s);

        // int n = s.length();
        // dp = new long[n][n];
        // for(long[] arr: dp) Arrays.fill(arr, -1);
        // return (int)countDPSMemo(0, n-1, s);

        // return (int)countDPSTab(s);

        return (int)countDPSTabOptimized(s);

    }

    //recursive(we cannot use this solution further)
//    private void dfs(int i, String s, StringBuilder cur){
//
//        if(i == s.length()){
//            if(cur.length()>0 && isPalindrome(cur.toString())){
//                palindromes.add(cur.toString());
//            }
//            return;
//        }
//
//        //do not include
//        dfs(i+1, s, cur);
//
//        cur.append(s.charAt(i));
//
//        //include
//        dfs(i+1, s, cur);
//
//        //backtrack
//        cur.deleteCharAt(cur.length()-1);
//    }
//
//    private boolean isPalindrome(String str){
//        int start = 0, end = str.length()-1;
//        while(start<=end){
//            if(str.charAt(start) != str.charAt(end)) return false;
//
//            start++;
//            end--;
//        }
//        return true;
//    }

    //recursive
    private long dfs2(int i, int j, String s){
        if(i > j) return 0;
        if(i == j) return 1;

        char c1 = s.charAt(i);
        char c2 = s.charAt(j);
        long count = 0;
        if(c1 != c2){
            count = (dfs2(i+1, j, s) + dfs2(i, j-1, s) - dfs2(i+1, j-1, s)) % MOD;
        } else{
            int low = i+1, high = j-1;

            while(low <= high && s.charAt(low)!=c1) low++;

            while(low <= high && s.charAt(high)!=c1) high--;

            if(low > high){
                count = (2 * dfs2(i+1, j-1, s) + 2) % MOD;
            } else if(low == high){
                count = (2 * dfs2(i+1, j-1, s) + 1) % MOD;
            } else{
                count = (2 * dfs2(i+1, j-1, s) - dfs2(low+1, high-1, s)) % MOD;
            }
        }

        return count;
    }

    //memoization (time: O(n2), space:O(n2))
    private long countDPSMemo(int i, int j, String s){
        if(i > j) return 0;
        if(i == j) return 1;

        if(dp[i][j] != -1) return dp[i][j];

        char c1 = s.charAt(i);
        char c2 = s.charAt(j);
        long count = 0;
        if(c1 != c2){
            count = countDPSMemo(i+1, j, s) + countDPSMemo(i, j-1, s) - countDPSMemo(i+1, j-1, s);
        } else{
            int low = i+1, high = j-1;

            while(low <= high && s.charAt(low)!=c1) low++;

            while(low <= high && s.charAt(high)!=c1) high--;

            if(low > high){
                count = 2 * countDPSMemo(i+1, j-1, s) + 2;
            } else if(low == high){
                count = 2 * countDPSMemo(i+1, j-1, s) + 1;
            } else{
                count = 2 * countDPSMemo(i+1, j-1, s) - countDPSMemo(low+1, high-1, s);
            }
        }
        count %= MOD;

        if (count < 0)              //in java % is not same as mod. its remainder operator -3%MOD = -3. hence we are doing -3 + MOD to get positive value
            count += MOD;
        return dp[i][j] = count;
    }

    //tabulation(time: O(n3), space:O(n2))
    private long countDPSTab(String s){
        int n = s.length();
        long[][] dp = new long[n][n];

        for(int i=0; i<n; i++)
            dp[i][i] = 1;

        for(int len=2; len<=n; len++){
            for(int i=0; i+len-1 < n; i++){
                int j = i + len -1;

                char c1 = s.charAt(i);
                char c2 = s.charAt(j);

                if(c1 != c2){
                    dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];
                } else{
                    int low = i+1, high = j-1;

                    while(low <= high && s.charAt(low)!=c1) low++;

                    while(low <= high && s.charAt(high)!=c1) high--;

                    if(low > high){
                        dp[i][j] = 2 * dp[i+1][j-1] + 2;
                    } else if(low == high){
                        dp[i][j] = 2 * dp[i+1][j-1] + 1;
                    } else{
                        dp[i][j] = 2 * dp[i+1][j-1] - dp[low+1][high-1];
                    }
                }
                dp[i][j] %= MOD;

                if (dp[i][j] < 0)
                    dp[i][j] += MOD;
            }
        }

        return dp[0][n-1];
    }

    //tabulation optimized(time: O(n2), space:O(n2))
    private long countDPSTabOptimized(String s){
        int n = s.length();
        long[][] dp = new long[n][n];
        int[] prev = new int[n];
        int[] next = new int[n];

        //build prev
        int[] last = new int[26];
        Arrays.fill(last, -1);

        for(int i=0; i<n; i++){
            int ch = s.charAt(i) - 'a';
            prev[i] = last[ch];
            last[ch] = i;
        }

        //build next
        Arrays.fill(last, -1);

        for(int i=n-1; i>=0; i--){
            int ch = s.charAt(i) - 'a';
            next[i] = last[ch];
            last[ch] = i;
        }


        for(int i=0; i<n; i++)
            dp[i][i] = 1;

        for(int len=2; len<=n; len++){
            for(int i=0; i+len-1 < n; i++){
                int j = i + len -1;

                char c1 = s.charAt(i);
                char c2 = s.charAt(j);

                if(c1 != c2){
                    dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];
                } else{
                    int low = next[i], high = prev[j];

                    if(low > high){
                        dp[i][j] = 2 * dp[i+1][j-1] + 2;
                    } else if(low == high){
                        dp[i][j] = 2 * dp[i+1][j-1] + 1;
                    } else{
                        dp[i][j] = 2 * dp[i+1][j-1] - dp[low+1][high-1];
                    }
                }
                dp[i][j] %= MOD;

                if (dp[i][j] < 0)
                    dp[i][j] += MOD;
            }
        }

        return dp[0][n-1];
    }
}
