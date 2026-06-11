package com.interviewprep.dsa.dp.knapsack;

//https://leetcode.com/problems/last-stone-weight-ii/description/
public class LastStoneWeightII {
    private int[][] dp;
    public int lastStoneWeightII(int[] stones) {
        //partition into two set of weights left and right.
        // int stoneSum = 0;
        // for(int stone: stones){
        //     stoneSum+=stone;
        // }
        // int target = (stoneSum+1)/2;
        // return dfs(0, 0, stoneSum, target, stones);

        // int stoneSum = 0;
        // for(int stone: stones){
        //     stoneSum+=stone;
        // }
        // dp = new int[stones.length][stoneSum+1];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }
        // int target = (stoneSum+1)/2;
        // return lastStoneWeightIIMemo(0, 0, stoneSum, target, stones);

        // return lastStoneWeightIITab(stones);

        return lastStoneWeightIITabOptimized(stones);
    }

    //recursive
    private int dfs(int i, int total, int stoneSum, int target, int[] stones){
        if(total >= target || i == stones.length) return Math.abs(total - (stoneSum-total));
        return Math.min(dfs(i+1, total, stoneSum, target, stones),dfs(i+1, total + stones[i], stoneSum, target, stones));
    }

    //memoization
    private int lastStoneWeightIIMemo(int i, int total, int stoneSum, int target, int[] stones){
        if(total >= target || i == stones.length) return Math.abs(total - (stoneSum-total));

        if(dp[i][total]!=-1) return dp[i][total];

        dp[i][total] = Math.min(lastStoneWeightIIMemo(i+1, total, stoneSum, target, stones),lastStoneWeightIIMemo(i+1, total + stones[i], stoneSum, target, stones));

        return dp[i][total];
    }

    //tabulation
    private int lastStoneWeightIITab(int[] stones){
        int n = stones.length;
        int stoneSum = 0;
        for(int stone: stones){
            stoneSum+=stone;
        }
        int target = (stoneSum)/2;
        int[][] dp = new int[n+1][stoneSum+1];

        for(int i=1; i<=n; i++){
            for(int t=0; t<=target; t++){
                if(t >= stones[i-1]){
                    dp[i][t] = Math.max(dp[i-1][t], stones[i-1] + dp[i-1][t - stones[i-1]]);
                } else{
                    dp[i][t] = dp[i-1][t];
                }
            }
        }
        int subset1 = dp[n][target];
        int subset2 = stoneSum-subset1;
        return subset2-subset1;
    }

    //tabulation space optimized
    private int lastStoneWeightIITabOptimized(int[] stones){
        int n = stones.length;
        int stoneSum = 0;
        for(int stone: stones){
            stoneSum+=stone;
        }
        int target = (stoneSum)/2;
        int[] dp = new int[stoneSum+1];

        for(int i=0; i<n; i++){
            for(int t=target; t>=stones[i]; t--){
                dp[t] = Math.max(dp[t], stones[i] + dp[t - stones[i]]);
            }
        }
        int subset1 = dp[target];
        int subset2 = stoneSum-subset1;
        return subset2-subset1;
    }
}
