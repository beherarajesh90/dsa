package com.interviewprep.dsa.dp.knapsack;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/target-sum/description/
public class TargetSum {
    private int[][] dp;
    private int totalSum;
    public int findTargetSumWays(int[] nums, int target) {
        // return dfs(0, target, nums);

        // totalSum=0;
        // for(int num: nums){
        //     totalSum += num;
        // }
        // //we can also use map as there are negative values. below is optimal way
        // dp = new int[nums.length][2*totalSum+1]; //the target can be inbetween -sum and +sum
        // for(int[] arr: dp){
        //     Arrays.fill(arr, Integer.MIN_VALUE);
        // }
        // return findTargetSumWaysMemo(0, 0, nums, target);

        // return findTargetSumWaysTab(nums, target);

        return findTargetSumWaysTabOptimized(nums, target);
    }

    //recursive
    private int dfs(int index, int target,int[] nums){
        if(index == nums.length) return target == 0 ? 1 : 0;

        int sub = dfs(index+1, target-nums[index], nums);
        int add = dfs(index+1, target+nums[index], nums);

        return sub + add;
    }

    //memoization(after submission this solution is takes less time)
    private int findTargetSumWaysMemo(int index, int total,int[] nums, int target){
        if(index == nums.length) return target == total ? 1 : 0;
        if(dp[index][total + totalSum]!=Integer.MIN_VALUE) return dp[index][total+totalSum];

        int sub = findTargetSumWaysMemo(index+1, total-nums[index], nums, target);
        int add = findTargetSumWaysMemo(index+1, total+nums[index], nums, target);

        dp[index][total+totalSum] = sub + add;

        return dp[index][total+totalSum];
    }

    //tabulation
    private int findTargetSumWaysTab(int[] nums, int target){
        int n = nums.length;
        Map<Integer, Integer>[] dp = new HashMap[n+1];
        for(int i=0; i<=n; i++){
            dp[i] = new HashMap<>();
        }
        dp[0].put(0,1); //since there is exactly one way to form sum 0 using no numbers
        for(int i=0; i<n; i++){
            for(Map.Entry<Integer,Integer> entry: dp[i].entrySet()){
                int total = entry.getKey();
                int count = entry.getValue();
                dp[i+1].put(total+nums[i], dp[i+1].getOrDefault(total+nums[i], 0) + count);
                dp[i+1].put(total-nums[i], dp[i+1].getOrDefault(total-nums[i], 0) + count);
            }
        }
        return dp[n].getOrDefault(target, 0);
    }

    //tabulation optimized space
    private int findTargetSumWaysTabOptimized(int[] nums, int target){
        int n = nums.length;
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0,1); //since there is exactly one way to form sum 0 using no numbers
        for(int i=0; i<n; i++){
            Map<Integer, Integer> nextDp = new HashMap<>();
            for(Map.Entry<Integer,Integer> entry: dp.entrySet()){
                int total = entry.getKey();
                int count = entry.getValue();
                nextDp.put(total+nums[i], nextDp.getOrDefault(total+nums[i], 0) + count);
                nextDp.put(total-nums[i], nextDp.getOrDefault(total-nums[i], 0) + count);
            }
            dp = nextDp;
        }
        return dp.getOrDefault(target, 0);
    }
}
