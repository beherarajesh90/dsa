package com.interviewprep.dsa.arrays.twoPointers;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int closestSum = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for(int i=0; i<nums.length; i++){
            if(i>0 && nums[i]==nums[i-1]) continue;
            closestSum=findClosestToTarget(nums,i+1,target,closestSum);
        }
        return closestSum;
    }

    private int findClosestToTarget(int[] nums, int startInd, int target, int closestSum) {
        int left=startInd, right=nums.length-1;
        while (left<right){
            int sum = nums[startInd-1]+nums[left]+nums[right];
            if(Math.abs(target-sum)<Math.abs(target-closestSum)) closestSum = sum;
            if(sum<target) left++;
            else right--;
        }
        return closestSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,8,9};
        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        int result = threeSumClosest.threeSumClosest(nums, -1);
        System.out.println(result);
    }
}
