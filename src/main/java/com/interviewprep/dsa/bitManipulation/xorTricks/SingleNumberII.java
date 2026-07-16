package com.interviewprep.dsa.bitManipulation.xorTricks;

//https://leetcode.com/problems/single-number-ii/description/
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        // return singleNumberBitByBit(nums);
        return singleNumberStateMachine(nums);
    }

    // time: O(n), space = O(1)
    private int singleNumberStateMachine(int[] nums) {
        int ones = 0, twos = 0;
        for(int num: nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    // time: O(32 * n), space = O(1)
    private int singleNumberBitByBit(int[] nums) {
        int result = 0;

        // Process each of the 32 bit positions
        for (int i = 0; i < 32; i++) {
            int bitSum = 0;

            // Count how many numbers have a 1 at bit position i
            for (int num : nums) {
                bitSum += (num >> i) & 1;
            }

            // If the count mod 3 is 1, the single number has a 1 here
            if (bitSum % 3 == 1) {
                result |= (1 << i);
            }
        }

        return result;
    }
}
