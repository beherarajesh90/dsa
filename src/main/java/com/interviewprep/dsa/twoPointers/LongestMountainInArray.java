package com.interviewprep.dsa.twoPointers;

public class LongestMountainInArray {
    public int longestMountain(int[] arr) {
        int n=arr.length, maxLen=0, i=1;
        while(i<n-1){
            if(arr[i-1]<arr[i] && arr[i]>arr[i+1]){
                int left=i-1, right=i+1;
                while(left>0 && arr[left]>arr[left-1]) left--;
                while(right<n-1 && arr[right]>arr[right+1]) right++;
                maxLen = Math.max(maxLen,right-left+1);
                i=right;
            } else{
                i++;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,1,4,7,3,2,5};
        LongestMountainInArray longestMountainInArray = new LongestMountainInArray();
        int result = longestMountainInArray.longestMountain(arr);
        System.out.println(result);
    }
}
