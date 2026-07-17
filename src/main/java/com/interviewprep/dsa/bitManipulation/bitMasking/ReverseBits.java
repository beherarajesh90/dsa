package com.interviewprep.dsa.bitManipulation.bitMasking;

//https://leetcode.com/problems/reverse-bits/description/
public class ReverseBits {
    public int reverseBits(int n) {
        // return reverseBitsBruteforce(n);

        return reverseBitsBitSwapping(n);
    }

    private int reverseBitsBruteforce(int n){
        int result = 0;
        for(int i=0; i<32; i++){
            result = (result << 1) | (n & 1);
            n = n >>> 1;
        }
        return result;
    }

    private int reverseBitsBitSwapping(int n){
        // swap 16 bit halves
        n = (n >>> 16) | (n << 16);

        // swap 8 bit groups
        n = ((n & 0xFF00FF00) >>> 8) | ((n & 0x00FF00FF) << 8);

        // swap 4 bit nibbles
        n = ((n & 0xF0F0F0F0) >>> 4) | ((n & 0x0F0F0F0F) << 4);

        // swap 2 bit pairs
        n = ((n & 0xCCCCCCCC) >>> 2) | ((n & 0x33333333) << 2);

        // swap adjacent bits
        n = ((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1);

        return n;
    }
}
