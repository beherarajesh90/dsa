package com.interviewprep.dsa.dp.optimalSubstructure;

//https://leetcode.com/problems/maximum-subarray/description/
public class MaximumSubarray {
    //using kadanes algo - optimal
    public int maxSubArray(int[] nums) {
        int current_sum = nums[0];
        int max_sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            current_sum = Math.max(nums[i], current_sum + nums[i]);
            max_sum = Math.max(max_sum, current_sum);
        }
        return max_sum;
    }

    //using DP
    // public int maxSubArray(int[] nums){
    //     int n = nums.length;
    //     if(n == 1) return nums[0];

    //     int[] dp = new int[n];
    //     dp[0] = nums[0];
    //     int maxSum = nums[0];
    //     for(int i=1; i<n; i++){
    //         dp[i] = Math.max(nums[i] + dp[i-1], nums[i]);
    //         maxSum = Math.max(maxSum, dp[i]);
    //     }

    //     return maxSum;
    // }
}
