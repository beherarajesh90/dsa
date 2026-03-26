package com.interviewprep.dsa.arrays.prefixSums;

public class FindPivotIndex {
    public int pivotIndexMyOwnSolution(int[] nums) {
        int totalSum=0, res=-1;
        for(int num: nums){
            totalSum+=num;
        }
        int leftSum=0, rightSum=totalSum;
        for(int i=0;i<nums.length;i++){
            if(i!=0) leftSum+=nums[i-1];
            rightSum-=nums[i];
            if(leftSum == rightSum){
                res = i ;
                break;
            }
        }
        return res;
    }

    public int pivotIndex(int[] nums) {
        int total = 0, leftSum = 0;
        for (int n : nums) total += n;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == total - leftSum - nums[i]) return i;
            leftSum += nums[i];
        }
        return -1;
    }
}
