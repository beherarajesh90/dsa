package com.interviewprep.dsa.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumToZero {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0) break;
            if(i>0 && nums[i]==nums[i-1]) continue;
            findTwoSum(nums,i+1,nums[i],result);
        }
        return result;
    }

    public void findTwoSum(int[] nums, int startInd, int target, List<List<Integer>> result){
        int left=startInd, right=nums.length-1;
        while(left<right){
            int sum = target+nums[left]+nums[right];
            if(sum==0){
                result.add(Arrays.asList(target,nums[left],nums[right]));
                left++;
                right--;
                while(left<right && nums[left]==nums[left-1]){
                    left++;
                }
                while(left<right && nums[right]==nums[right+1]){
                    right--;
                }
            }else if(sum < 0){
                left++;
            } else{
                right--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        ThreeSumToZero threeSumToZero = new ThreeSumToZero();
        List<List<Integer>> result = threeSumToZero.threeSum(nums);
        System.out.println(result);
    }
}
