package com.interviewprep.dsa.dp.basicDP;

//https://leetcode.com/problems/min-cost-climbing-stairs/
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        // return minCost(cost, cost.length);

        // Map<Integer, Integer> memo = new HashMap<>();
        // memo.put(0,0);
        // memo.put(1,0);
        // return minCostMemo(cost, cost.length, memo);

        // return minCostTab(cost);

        return minCostOpt(cost);
    }

    //recursive
    // private int minCost(int[] cost, int i){
    //     if(i < 2) return 0;
    //     return Math.min(cost[i-1] + minCost(cost, i-1), cost[i-2] + minCost(cost, i-2));
    // }

    //memoization (top-down)
    // private int minCostMemo(int[] cost, int i, Map<Integer, Integer> memo){
    //     if(memo.containsKey(i)) return memo.get(i);

    //     int minCost = Math.min(cost[i-1] + minCostMemo(cost, i-1, memo), cost[i-2] + minCostMemo(cost, i-2, memo));

    //     memo.put(i, minCost);

    //     return minCost;
    // }

    //tabulation(bottom-up)
    // private int minCostTab(int[] cost){
    //     int n = cost.length;
    //     if(n < 2) return 0;

    //     int[] dp = new int[cost.length+1];

    //     for(int i=2; i<=n; i++){
    //         dp[i] = Math.min(cost[i-2] + dp[i-2], cost[i-1] + dp[i-1]);
    //     }

    //     return dp[n];
    // }

    //space optimized bottom-up
    private int minCostOpt(int[] cost){
        int prev2 = 0; //cost to take 0 step
        int prev1 = 0; //cost to take 1 step

        int n = cost.length;
        if(n < 2) return 0;

        //here top is n
        for(int i=2; i<=n; i++){
            int minSteps = Math.min(prev2 + cost[i - 2], prev1 + cost[i-1]);
            prev2 = prev1;
            prev1 = minSteps;
        }

        return prev1;
    }
}
