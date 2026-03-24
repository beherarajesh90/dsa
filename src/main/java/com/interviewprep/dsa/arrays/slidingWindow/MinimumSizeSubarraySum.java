package com.interviewprep.dsa.arrays.slidingWindow;

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int left=0, curSum=0, minWindowSize=Integer.MAX_VALUE, n=nums.length;
        for(int right=0; right<n;right++){
            curSum+=nums[right];
            while(curSum>=target){
                minWindowSize = Math.min(minWindowSize, right-left+1);
                curSum-=nums[left++];
            }
        }
        return minWindowSize==Integer.MAX_VALUE ? 0 : minWindowSize;
    }
}
