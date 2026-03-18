package com.interviewprep.dsa.twoPointers;

public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        int nextEleInd = 2;
        for(int i=2;i<nums.length;i++){
            if(nums[nextEleInd-2]!=nums[i]){
                nums[nextEleInd]=nums[i];
                nextEleInd++;
            }
        }
        return nextEleInd;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        RemoveDuplicatesFromSortedArrayII removeDuplicatesFromSortedArrayII = new RemoveDuplicatesFromSortedArrayII();
        int result = removeDuplicatesFromSortedArrayII.removeDuplicates(nums);
        System.out.println(result);
    }
}
