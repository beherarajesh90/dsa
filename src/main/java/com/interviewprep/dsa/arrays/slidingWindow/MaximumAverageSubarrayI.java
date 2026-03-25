package com.interviewprep.dsa.arrays.slidingWindow;

//https://leetcode.com/problems/maximum-average-subarray-i/
public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        int left=0;
        double maxAvg=0,curSum=0;
        for(int i=0; i<k; i++){
            curSum+=nums[i];
        }
        maxAvg=curSum/k;
        for(int i=k; i<nums.length; i++){
            curSum-=nums[i-k];
            curSum+=nums[i];
            maxAvg = Math.max(maxAvg, curSum/k);
        }
        return maxAvg;
    }
}
