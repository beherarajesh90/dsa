package com.interviewprep.dsa.binarySearch.bitonicArraySearch;

//https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
public class PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(arr[mid] < arr[mid+1]){
                left = mid + 1;
            } else{
                right = mid;
            }
        }
        return left;
    }
}
