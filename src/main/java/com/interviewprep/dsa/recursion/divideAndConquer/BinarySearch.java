package com.interviewprep.dsa.recursion.divideAndConquer;

public class BinarySearch {
    public int search(int[] nums, int target) {
        return recursiveBinarySearch(nums, target, 0, nums.length-1);
    }

    private int recursiveBinarySearch(int[] nums, int target, int left, int right){
        if(left>right) return -1;
        int mid = left + (right - left)/2;
        if(nums[mid] == target) return mid;
        if(nums[mid] < target){
            return recursiveBinarySearch(nums, target, mid+1, right);
        }
        return recursiveBinarySearch(nums, target, left, mid-1);
    }
}
