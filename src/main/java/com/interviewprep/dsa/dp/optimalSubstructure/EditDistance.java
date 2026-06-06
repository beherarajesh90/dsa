package com.interviewprep.dsa.dp.optimalSubstructure;

//https://leetcode.com/problems/edit-distance/
public class EditDistance {
    private int[][] dp;
    public int minDistance(String word1, String word2) {
        // return dfs(0, 0, word1.length(), word2.length(), word1, word2);

        // int m = word1.length(), n = word2.length();
        // dp = new int[m][n];
        // for(int i=0; i<m; i++){
        //     for(int j=0; j<n; j++){
        //         dp[i][j] = -1;
        //     }
        // }
        // return minDistanceMemo(0, 0, m, n, word1, word2);

        // return minDistanceTab(word1, word2);

        return minDistanceTabSpaceOptimized(word1, word2);
    }

    //recursive(time: O(3^ (m * n)), space optimized: O(m + n))
    private int dfs(int i, int j, int m, int n, String word1, String word2){

        if(i == m) return n - j;    //if word1 reached end, then ramaining letters in word2 needs to be inserted
        if(j == n) return m - i;    //if word2 reached end, then ramaining letters in word1 needs to be deleted

        if(word1.charAt(i) == word2.charAt(j)){
            return dfs(i+1, j+1, m, n, word1, word2);
        }

        //Delete from word1: dfs(i + 1, j)
        //Insert into word1: dfs(i, j + 1)
        int res = Math.min(dfs(i+1, j, m, n, word1, word2), dfs(i, j+1, m, n, word1, word2));

        res = Math.min(res, dfs(i+1, j+1, m, n, word1, word2)); //Replace the character: dfs(i + 1, j + 1)

        return 1 + res;
    }

    //memoization(time: O(m * n), space optimized: O(m * n))
    private int minDistanceMemo(int i, int j, int m, int n, String word1, String word2){

        if(i == m) return n - j;    //if word1 reached end, then ramaining letters in word2 needs to be inserted
        if(j == n) return m - i;    //if word2 reached end, then ramaining letters in word1 needs to be deleted

        if(dp[i][j]!=-1) return dp[i][j];

        if(word1.charAt(i) == word2.charAt(j)){
            dp[i][j] = minDistanceMemo(i+1, j+1, m, n, word1, word2);
        } else{
            //Delete from word1: dfs(i + 1, j)
            //Insert into word1: dfs(i, j + 1)
            int res = Math.min(minDistanceMemo(i+1, j, m, n, word1, word2), minDistanceMemo(i, j+1, m, n, word1, word2));

            res = Math.min(res, minDistanceMemo(i+1, j+1, m, n, word1, word2)); //Replace the character: dfs(i + 1, j + 1)
            dp[i][j] = res + 1;
        }

        return dp[i][j];
    }

    //tabulation(time: O(m * n), space optimized: O(m * n))
    private int minDistanceTab(String word1, String word2){
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];

        for(int i=0; i<=n; i++){
            dp[m][i] = n - i;
        }

        for(int j=0; j<=m; j++){
            dp[j][n] = m - j;
        }

        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                if(word1.charAt(i) == word2.charAt(j)){
                    dp[i][j] = dp[i+1][j+1];
                } else{
                    dp[i][j] = 1 + Math.min(dp[i+1][j+1], Math.min(dp[i+1][j], dp[i][j+1]));
                }
            }
        }

        return dp[0][0];
    }

    //tabulation(time: O(m * n), space optimized: O(min(m,n)))
    private int minDistanceTabSpaceOptimized(String word1, String word2){
        int m = word1.length(), n = word2.length();
        if(m < n){
            int temp = m;
            m = n;
            n = temp;

            String tempW = word1;
            word1 = word2;
            word2 = tempW;
        }

        int[] dp = new int[n+1];
        int[] nextDp = new int[n+1];

        for(int i=0; i<=n; i++){
            dp[i] = n - i;
        }

        for(int i=m-1; i>=0; i--){
            nextDp[n] = m - i;
            for(int j=n-1; j>=0; j--){
                if(word1.charAt(i) == word2.charAt(j)){
                    nextDp[j] = dp[j+1];
                } else{
                    nextDp[j] = 1 + Math.min(dp[j], Math.min(nextDp[j+1], dp[j+1]));
                }
            }
            System.arraycopy(nextDp, 0, dp, 0, n+1);
        }

        return dp[0];
    }
}
