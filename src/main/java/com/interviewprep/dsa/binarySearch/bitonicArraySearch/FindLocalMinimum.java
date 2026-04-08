package com.interviewprep.dsa.binarySearch.bitonicArraySearch;

//https://www.propeers.in/roadmaps/69692150442404dc257236a1/find-local-minima?todoItemId=69b476ad8789fde24cfdc964
public class FindLocalMinimum {
    public int findLocalMinimum(int[] nums) {
        int left = 0, right = nums.length - 1, localMin = -1;
        while (left<=right){
            int mid = left + (right - left)/2;

            // Check if mid is a local minimum
            if((mid == 0 || nums[mid] < nums[mid-1]) && (mid == nums.length-1 || nums[mid] < nums[mid+1])){

                //store the local minimum index
                localMin = mid;
                //continue searching for a smaller index
                right = mid - 1;
            }
            //if left neighbour is smaller move left
            else if (mid > 0 && nums[mid] > nums[mid-1]) {

                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return localMin;
    }
}
