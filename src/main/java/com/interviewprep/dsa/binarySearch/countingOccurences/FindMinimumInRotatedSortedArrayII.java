package com.interviewprep.dsa.binarySearch.countingOccurences;

public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        while(left<right){
            /*
            if the mid element greater than the right most element the smallest element would be in the right half
            if the mid element less than the right most element the smallest element would be in the left half
            if the mid element equal to the right most element decrease the right by 1
             */
            int mid = (left + right)/2;
            if(nums[mid] > nums[right]){
                left = mid + 1;
            } else if(nums[mid] < nums[right]){
                right = mid;
            } else{
                right-=1;
            }
        }
        return nums[left];
    }
}
