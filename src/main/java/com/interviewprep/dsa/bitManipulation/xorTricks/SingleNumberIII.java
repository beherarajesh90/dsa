package com.interviewprep.dsa.bitManipulation.xorTricks;

//https://leetcode.com/problems/single-number-iii/description/
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {

        // find xor of all the numbers
        int xor = 0;
        for(int num: nums){
            xor ^= num;
        }

        // find the right most bit set to 1
        int lsb = xor & -(xor);

        // g1 -> lsb=1, g2 -> lsb=0
        int g1 = 0, g2 = 0;
        for(int num: nums){
            if((num & lsb) != 0){
                g1 ^= num;
            } else{
                g2 ^= num;
            }
        }

        return new int[]{g1, g2};
    }
}
