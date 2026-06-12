package com.interviewprep.dsa.dp.counting;

//https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/description/
public class NumberOfWaysToFormATargetStringGivenADictionary {
    private static final int mod = 1_000_000_007;

    private int[][] dp;
    private int[][] count;
    public int numWays(String[] words, String target) {

        // return dfs(0, 0, words, target);

        // dp = new int[target.length()][words[0].length()];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }
        // return numWaysMemo(0, 0, words, target);

        // int n = target.length(),m = words[0].length();
        // dp = new int[n][m];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }

        // return numWaysMemo(0, 0, words, target);


        // int n = target.length(),m = words[0].length();
        // dp = new int[n+1][m+1];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }

        // count = new int[m][26];
        // for(String word: words){
        //     for(int i=0; i<m; i++){
        //         count[i][word.charAt(i)-'a']++;
        //     }
        // }
        // return numWaysMemoOptimized(0, 0, n, m, target);

        // return numWaysTab(words, target);

        return numWaysTabOptimized(words, target);
    }

    //recursive(time: n)
    //i - target index
    //k - word index
    private int dfs(int i, int k, String[] words, String target){
        if(i == target.length()) return 1;

        if(k == words[0].length()) return 0;

        int res = dfs(i, k+1, words, target);

        for(String word: words){
            if(word.charAt(k) != target.charAt(i)) continue;

            res = (res + dfs(i+1, k+1, words, target)) % mod;
        }

        return res;
    }

    //memoiazation
    private int numWaysMemo(int i, int k, String[] words, String target){
        if(i == target.length()) return 1;

        if(k == words[0].length()) return 0;

        if(dp[i][k]!=-1) return dp[i][k];

        dp[i][k] = numWaysMemo(i, k+1, words, target);

        for(String word: words){
            if(word.charAt(k) != target.charAt(i)) continue;

            dp[i][k] = (dp[i][k] + numWaysMemo(i+1, k+1, words, target)) % mod;
        }

        return dp[i][k];
    }

    //memoiazation optimized
    private int numWaysMemoOptimized(int i, int k, int n, int m, String target){
        if(i == n) return 1;
        if(k == m) return 0;
        if(dp[i][k]!=-1) return dp[i][k];

        int c = target.charAt(i) - 'a';
        dp[i][k] = numWaysMemoOptimized(i, k+1, n, m, target);
        dp[i][k] = (int)((dp[i][k] + (long) count[k][c] * numWaysMemoOptimized(i+1, k+1, n, m, target)) % mod);

        return dp[i][k];
    }

    //tabulation
    private int numWaysTab(String[] words, String target){
        int n = target.length(),m = words[0].length();
        // Number of ways to form target[i...] using columns k...m-1.
        dp = new int[n+1][m+1];
        dp[n][m] = 1;

        count = new int[m][26];
        for(String word: words){
            for(int i=0; i<m; i++){
                count[i][word.charAt(i)-'a']++;
            }
        }

        for(int i=n; i>=0; i--){
            for(int k=m-1; k>=0; k--){
                dp[i][k] = dp[i][k+1];

                if(i < n){
                    int c = target.charAt(i) - 'a';
                    dp[i][k] = (int)((dp[i][k] + (long) count[k][c] * dp[i+1][k+1]) % mod);
                }
            }
        }

        return dp[0][0];   //Number of ways to form the entire target target[0...] using all available columns 0...m-1.
    }

    //tabulation spacce optimized
    private int numWaysTabOptimized(String[] words, String target){
        int n = target.length(),m = words[0].length();
        // Number of ways to form target[i...] using columns k...m-1.
        int[] dp = new int[m+1];
        dp[m] = 1;

        count = new int[m][26];
        for(String word: words){
            for(int i=0; i<m; i++){
                count[i][word.charAt(i)-'a']++;
            }
        }

        for(int i=n; i>=0; i--){
            int next = i == n-1 ? 1 : 0;
            for(int k=m-1; k>=0; k--){
                int cur = dp[k];
                dp[k] = dp[k+1];

                if(i < n){
                    int c = target.charAt(i) - 'a';
                    dp[k] = (int)((dp[k] + (long) count[k][c] * next) % mod);
                }
                next = cur;
            }
            dp[m] = 0;
        }

        return dp[0];   //Number of ways to form the entire target target[0...] using all available columns 0...m-1.
    }
}
