package com.interviewprep.dsa.binarySearch.countingOccurences;

//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        int[] result = new int[]{-1,-1};

        // find left index of the target
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                result[0] = mid;
                right = mid - 1;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }

        // find right index of the target
        left = 0;
        right = nums.length-1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                result[1] = mid;
                left = mid + 1;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }

        return result;
    }
}
