package com.interviewprep.dsa.dp.optimalSubstructure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/coin-change/description/
public class CoinChange {
    private Map<Integer,Integer> memo = new HashMap<>();
    public int coinChange(int[] coins, int amount) {
        // int minCoins = dfs(coins, amount);
        // return minCoins==1e9 ? -1 : minCoins;

        // int minCoins = coinChangeMemo(coins, amount);
        // return minCoins == Integer.MAX_VALUE ? -1 : minCoins;

        return coinChangeTab(coins, amount);
    }

    //recursive
     private int dfs(int[] coins, int amount){
         if(amount == 0) return 0;
         int res = (int)1e9;
         for(int coin: coins){
             if(amount - coin >= 0){
                 res = Math.min(res, 1 + dfs(coins, amount-coin));
             }
         }
         return res;
     }


    private int coinChangeMemo(int[] coins, int amount){
        if (amount == 0) return 0;
        if (memo.containsKey(amount))
            return memo.get(amount);

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount - coin >= 0) {
                int result = coinChangeMemo(coins, amount - coin);
                if (result != Integer.MAX_VALUE) {
                    res = Math.min(res, 1 + result);
                }
            }
        }

        memo.put(amount, res);
        return res;
    }

    private int coinChangeTab(int[] coins, int amount){
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;   // base case: 0 amount requires 0 coins
        Arrays.sort(coins);

        for(int i=1; i<=amount; i++){
            for(int coin: coins){
                if(i - coin < 0) break;
                if(dp[i-coin]!=Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
