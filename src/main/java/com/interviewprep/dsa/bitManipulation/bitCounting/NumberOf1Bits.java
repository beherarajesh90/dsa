package com.interviewprep.dsa.bitManipulation.bitCounting;

//https://leetcode.com/problems/number-of-1-bits/description/
public class NumberOf1Bits {
    public int hammingWeight(int n) {
        // int setBitCount = 0;
        // for(int i=0; i<31; i++){
        //     setBitCount += (n & (1 << i)) !=0 ? 1 : 0;
        // }
        // return setBitCount;

        // O(32)
        // int count = 0;
        // while(n!=0){
        //     count += n & 1;
        //     n = n >>> 1;
        // }
        // return count;

        // brian kerninghams algo(optimal) - O(k) - k is no of set bits
        int count = 0;
        while(n!=0){
            n = n & (n-1);
            count++;
        }
        return count;

        // lookup table - time: O(256), space: O(256)
        // int[] table = new int[256];
        // for(int i=1; i<256; i++){
        //     table[i] = table[i >> 1] + (i & 1);
        // }

        // return table[n & 0xFF] + table[n >> 8 & 0xFF] + table[n >> 16 & 0xFF] + table[n >> 24 & 0xFF];
    }
}
