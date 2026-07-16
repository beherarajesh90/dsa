package com.interviewprep.dsa.bitManipulation.bitCounting;

//https://leetcode.com/problems/minimum-bit-flips-to-convert-number/description/
public class MinimumBitFlipsToConvertNumber {
    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;
        return Integer.bitCount(xor);
    }
}
