package com.interviewprep.dsa.bitManipulation;

//https://leetcode.com/problems/set-mismatch/description/
public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        // return findErrorNumsBitManipulation(nums);

        return findErrorNumsOptimal(nums);
    }

    // negative marking - optimal
    private int[] findErrorNumsOptimal(int[] nums) {
        int n = nums.length;
        int duplicate = -1, missing = -1;

        // Mark visited indices by negating values
        for (int i = 0; i < n; i++) {
            int targetIdx = Math.abs(nums[i]) - 1;
            if (nums[targetIdx] < 0) {
                // Already visited, this is the duplicate
                duplicate = Math.abs(nums[i]);
            } else {
                nums[targetIdx] = -nums[targetIdx];
            }
        }

        // The index with a positive value is the missing number
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                missing = i + 1;
            }
        }

        return new int[]{duplicate, missing};
    }

    private int[] findErrorNumsBitManipulation(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for(int i=0; i<n; i++){
            xor = xor ^ (i+1) ^ nums[i];
        }

        // The rightmost set bit is used because it guarantees that the duplicate and missing numbers end up in different groups.
        int lsb = xor & -xor;

        int g1 = 0;
        int g2 = 0;

        for(int i=0; i<n; i++){
            if((nums[i] & lsb) != 0){
                g1 ^= nums[i];
            } else{
                g2 ^= nums[i];
            }

            if(((i+1) & lsb) != 0){
                g1 ^= i+1;
            } else{
                g2 ^= i+1;
            }
        }

        int duplicate = -1, missing = -1;
        for(int num: nums){
            if(num == g1){
                duplicate = g1;
            }
        }

        if(duplicate == -1){
            missing = g1;
            duplicate = g2;
        } else{
            missing = g2;
        }

        return new int[]{duplicate, missing};
    }
}
