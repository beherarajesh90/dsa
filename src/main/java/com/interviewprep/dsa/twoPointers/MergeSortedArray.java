package com.interviewprep.dsa.twoPointers;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1, j = n-1;
        int k=m+n-1;
        while(k>=0 && i>=0 && j>=0){
            if(nums1[i]>nums2[j]){
                nums1[k] = nums1[i];
                i--;
            } else{
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        while(j>=0){
            nums1[k--]=nums2[j--];
        }
    }

    public static void main(String[] args) {
//        int[] nums1 = new int[1];
        MergeSortedArray mergeSortedArray = new MergeSortedArray();
        mergeSortedArray.merge(new int[]{0},0,new int[]{1},1);
    }
}
