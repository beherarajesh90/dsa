package com.interviewprep.dsa.arrays.sorting;

import java.util.Arrays;

public class MinimumDifferenceBetweenHighestAndLowestOfKScores {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n=nums.length;
        int maxScore = Integer.MAX_VALUE;
        for(int start=0;start<=n-k; start++){
            int end = start+k-1;
            if(nums[end]-nums[start]<maxScore){
                maxScore = nums[end] - nums[start];
            }
        }
        return maxScore;
    }
}
