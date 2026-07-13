package com.interviewprep.dsa.bitManipulation;

//https://leetcode.com/problems/set-mismatch/description/
public class SetMismatch {
    // negative marking - optimal
    public int[] findErrorNums(int[] nums) {
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
}
