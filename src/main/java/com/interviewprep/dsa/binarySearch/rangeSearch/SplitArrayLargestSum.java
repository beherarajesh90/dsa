package com.interviewprep.dsa.binarySearch.rangeSearch;

public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        int left = Integer.MIN_VALUE, right = 0;
        for(int num: nums){
            left = Math.max(left, num);
            right+= num;
        }
        int result = right;
        while(left<=right){
            int mid = left + (right - left)/2;
            if(canSplit(nums, k, mid)){
                result = mid;
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return result;
    }

    private boolean canSplit(int[] nums, int k, int sum){
        int splits = 1, curSum = 0;
        for(int num: nums){
            if(curSum+num<=sum){
                curSum+=num;
            } else{
                splits++;
                curSum = num;
                if(splits>k) return false;
            }
        }
        return true;
    }
}
