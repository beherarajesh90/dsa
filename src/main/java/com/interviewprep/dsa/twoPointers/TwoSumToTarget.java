package com.interviewprep.dsa.twoPointers;

public class TwoSumToTarget {
    public int[] twoSum(int[] numbers, int target) {
        int left=0, right=numbers.length-1;
        while(left<right){
            int curSum = numbers[left]+numbers[right];
            if(curSum==target) return new int[]{left+1,right+1};
            else if(curSum<target) left++;
            else right--;
        }
        return new int[]{-1,-1};
    }
}
