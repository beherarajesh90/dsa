package com.interviewprep.dsa.dp.optimalSubstructure;

import java.util.Map;

//https://leetcode.com/problems/house-robber/description/
public class HouseRobber {
    public int rob(int[] nums){
        // return robRec(nums, nums.length-1);

        // if(nums.length == 1) return nums[0];
        // if(nums.length == 2) return Math.max(nums[0], nums[1]);
        // Map<Integer, Integer> memo = new HashMap<>();
        // memo.put(0, nums[0]);
        // memo.put(1, Math.max(nums[0], nums[1]));
        // return robMemo(nums, nums.length-1, memo);

        // return robTab(nums);

        return robTabSpaceOptimized(nums);
    }

    //recursive approach
    private int robRec(int[] nums, int i){
        if(i == 0) return nums[i];
        if(i == 1) return Math.max(nums[i-1], nums[i]);
        return Math.max(nums[i] + robRec(nums, i-2), robRec(nums, i-1));
    }

    //memoization
    private int robMemo(int[] nums, int i, Map<Integer, Integer> memo){
        if(memo.containsKey(i)) return memo.get(i);

        int maxMoney = Math.max(nums[i] + robMemo(nums, i-2, memo), robMemo(nums, i-1, memo));

        memo.put(i, maxMoney);

        return maxMoney;
    }

    //tabulation
    private int robTab(int[] nums){
        int n = nums.length;
        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i<n; i++){
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }

        return dp[n-1];
    }

    // tabulation(space optimized)
    private int robTabSpaceOptimized(int[] nums){
        int n = nums.length;
        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0], nums[1]);

        int prevPrev = nums[0];
        int prev = Math.max(nums[0], nums[1]);
        for(int i = 2; i<n; i++){
            int cur = Math.max(nums[i] + prevPrev, prev);
            prevPrev = prev;
            prev = cur;
        }

        return prev;
    }
}
