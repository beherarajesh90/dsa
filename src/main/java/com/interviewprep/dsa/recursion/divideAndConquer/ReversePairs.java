package com.interviewprep.dsa.recursion.divideAndConquer;

import java.util.ArrayList;
import java.util.List;

public class ReversePairs {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length);
    }

    private int mergeSort(int[] nums, int left, int right){
        if(right - left <= 1) return 0;
        int mid = left + (right - left)/2;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid, right);
        return merge(nums, left, mid, right, count);
    }

    private int merge(int[] nums, int left, int mid, int right, int result){
        int[] temp = new int[right - left];
        int i = left, j = mid, n = mid, k=0;

        //count the number of pairs
        for (int m=left; m<mid; m++){
            while (n < right && (long)nums[m]>(long)2*nums[n]) n++;
            result += n - mid;
        }

        while(i < mid && j<right){
            if(nums[j] < nums[i]){
                temp[k++] = nums[j++];
            } else{
                temp[k++] = nums[i++];
            }
        }

        while(i < mid){
            temp[k++] = nums[i++];
        }

        while(j < right){
            temp[k++] = nums[j++];
        }

        for(int t=0; t<temp.length; t++){
            nums[left + t] = temp[t];
        }

        return result;
    }

    public static void main(String[] args) {
        ReversePairs solution = new ReversePairs();
        int[] nums = {1,3,2,3,1};
        System.out.println(solution.reversePairs(nums));
    }
}
