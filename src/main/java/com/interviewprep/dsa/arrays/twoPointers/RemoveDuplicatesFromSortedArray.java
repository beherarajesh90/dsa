package com.interviewprep.dsa.arrays.twoPointers;

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int nextEleInd = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[nextEleInd-1]!=nums[i]){
                nums[nextEleInd]=nums[i];
                nextEleInd++;
            }
        }
        return nextEleInd;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        RemoveDuplicatesFromSortedArray removeDuplicatesFromSortedArray = new RemoveDuplicatesFromSortedArray();
        int result = removeDuplicatesFromSortedArray.removeDuplicates(nums);
        System.out.println(result);
    }
}
