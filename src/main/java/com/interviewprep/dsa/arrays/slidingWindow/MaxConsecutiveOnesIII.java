package com.interviewprep.dsa.arrays.slidingWindow;

public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int left=0, maxLen=0, zeroCount=0, n=nums.length;
        for(int right=0; right<n; right++){
            if(nums[right]==0){
                zeroCount++;
            }
            while(zeroCount>k){
                if(nums[left]==0) zeroCount--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
