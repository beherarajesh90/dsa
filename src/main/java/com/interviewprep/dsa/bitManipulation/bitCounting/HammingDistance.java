package com.interviewprep.dsa.bitManipulation.bitCounting;

//https://leetcode.com/problems/hamming-distance/description/
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        // int xor = x ^ y;
        // return Integer.bitCount(xor);

        // brian kerninghams algo
        int xor = x ^ y;
        int count = 0;
        while(xor != 0){
            xor = xor & (xor-1);
            count++;
        }
        return count;
    }
}
