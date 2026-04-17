package com.interviewprep.dsa.recursion.divideAndConquer;

//https://leetcode.com/problems/maximum-subarray/description/
public class MaximumSubarray {
    //Kadane's Algorithm
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE, curSum = 0;
        for (int num : nums) {
            curSum += num;
            maxSum = Math.max(maxSum, curSum);
            if (curSum < 0) curSum = 0;
        }
        return maxSum;
    }
}
