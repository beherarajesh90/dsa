package com.interviewprep.dsa.bitManipulation;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/missing-number/description/
public class MissingNumber {
    public int missingNumber(int[] nums) {
        // return missingNumberUsingHashSet(nums);

        // return missingNumberGaussFormula(nums);

        return missingNumberUsingBitManipulation(nums);
    }

    // bit manipulation (using xor a^a = 0, a^0 = a)
    public int missingNumberUsingBitManipulation(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for(int i=0; i<n; i++){
            xor = xor ^ (i+1) ^ nums[i];
        }
        return xor;
    }

    // Gauss's formula = (n * (n+1))/2
    public int missingNumberGaussFormula(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for(int num: nums){
            sum += num;
        }

        return (n*(n+1))/2 - sum;
    }

    private int missingNumberUsingHashSet(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int num: nums){
            set.add(num);
        }

        for(int i=0; i<=nums.length; i++){
            if(!set.contains(i)) return i;
        }

        return -1;
    }
}
