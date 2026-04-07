package com.interviewprep.dsa.binarySearch.bitonicArraySearch;

//https://leetcode.com/problems/find-in-mountain-array/
public class FindInMountainArray {
    public int findInMountainArray(int target, MountainArray mountainArr) {

        //find peak index
        int peakIndex = 0;
        int left = 0, right = mountainArr.length()-1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(mountainArr.get(mid) < mountainArr.get(mid+1)){
                left = mid + 1;
            } else{
                right = mid;
            }
        }
        peakIndex = left;

        //find target in the left half
        left = 0;
        right = peakIndex;
        while(left<=right){
            int mid = left + (right - left)/2;
            if(mountainArr.get(mid) == target){
                return mid;
            } else if(mountainArr.get(mid) > target){
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }

        //find target in the right half
        left = peakIndex+1;
        right = mountainArr.length()-1;
        while(left<=right){
            int mid = left + (right - left)/2;
            if(mountainArr.get(mid) == target){
                return mid;
            } else if(mountainArr.get(mid) > target){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }

        return -1;
    }
}
