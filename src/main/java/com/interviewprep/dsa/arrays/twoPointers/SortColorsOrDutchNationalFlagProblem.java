package com.interviewprep.dsa.arrays.twoPointers;

import java.util.Arrays;

public class SortColorsOrDutchNationalFlagProblem {
    public int[] sortColors(int[] arr){
        int low=0, high=arr.length-1;
        for(int i=0;i<=high;){
            if(arr[i]==0){
                swap(arr,i,low);
                i++;
                low++;
            } else if(arr[i]==1){
                i++;
            } else{
                swap(arr,i,high);
                high--;
            }
        }
        return arr;
    }
    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,0,1};
        SortColorsOrDutchNationalFlagProblem sortColorsOrDutch_National_Flag_Problem = new SortColorsOrDutchNationalFlagProblem();
        int[] result = sortColorsOrDutch_National_Flag_Problem.sortColors(arr);
        System.out.println(Arrays.toString(result));
    }
}
