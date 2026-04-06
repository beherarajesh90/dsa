package com.interviewprep.dsa.binarySearch.countingOccurences;

public class SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right  = nums.length-1;
        while(left < right){
            int mid = left + (right - left)/2;
            // The main realization is that before the single element, pairs start at even indices,
            // and after the single element, pairs start at odd indices.
            if(mid%2 == 0){
                //mid even

                //mid and mid+1 element equal then single element is in right
                //mid and mid-1 element equal then single element is in left
                if(nums[mid] == nums[mid+1]) left = mid + 2;
                else right = mid;
            } else{
                //mid odd

                //mid and mid+1 element equal then single element is in right
                //mid and mid-1 element equal then single element is in left
                if(nums[mid] == nums[mid+1]) right = mid - 1;
                else left = mid + 1;
            }
        }
        return nums[left];
    }
}
