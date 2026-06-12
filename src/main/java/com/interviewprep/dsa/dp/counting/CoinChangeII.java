package com.interviewprep.dsa.dp.counting;

//https://leetcode.com/problems/coin-change-ii/
public class CoinChangeII {
    private int[][] dp;
    public int change(int amount, int[] coins) {
        // return dfs(0, amount, coins);

        // int sum = 0;
        // for(int coin: coins) sum+=coin;

        // dp = new int[coins.length][amount+1];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }
        // return changeMemo(0, amount, coins);

        // return changeTab(amount, coins);

        return changeTabOptimized(amount, coins);
    }

    //recursive
    private int dfs(int i, int remaining, int[] coins){
        if(remaining < 0 || i == coins.length) return 0;
        if(remaining == 0) return 1;

        return dfs(i+1, remaining, coins) + dfs(i, remaining - coins[i], coins);
    }

    //memoization
    private int changeMemo(int i, int remaining, int[] coins){
        if(remaining < 0 || i == coins.length) return 0;
        if(remaining == 0) return 1;

        if(dp[i][remaining]!=-1) return dp[i][remaining];

        dp[i][remaining] = changeMemo(i+1, remaining, coins) + changeMemo(i, remaining - coins[i], coins);

        return dp[i][remaining];
    }

    //tabulation
    private int changeTab(int amount, int[] coins){
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        for(int i=0; i<=n; i++){
            dp[i][0] = 1;
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=amount; j++){
                if(j >= coins[i-1]){
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                } else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][amount];
    }

    //tabulation space optimized
    private int changeTabOptimized(int amount, int[] coins){
        int n = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int i=1; i<=n; i++){
            for(int j=coins[i-1]; j<=amount; j++){
                dp[j] = dp[j] + dp[j-coins[i-1]];
            }
        }
        return dp[amount];


        // int[] dp = new int[amount + 1];
        // dp[0] = 1; // One way to make amount 0: use no coins

        // // Process one coin at a time to count combinations, not permutations
        // for (int coin : coins) {
        //     for (int j = coin; j <= amount; j++) {
        //         dp[j] += dp[j - coin];
        //     }
        // }

        // return dp[amount];
    }
}
