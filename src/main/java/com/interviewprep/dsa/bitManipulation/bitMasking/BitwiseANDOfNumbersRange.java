package com.interviewprep.dsa.bitManipulation.bitMasking;

//https://leetcode.com/problems/bitwise-and-of-numbers-range/description/
public class BitwiseANDOfNumbersRange {

    // bruteforce is performing AND on all numbers from left to right

    // brian kerninghams - optimal
    public int rangeBitwiseAnd(int left, int right) {
        while(right > left){
            right = right & (right-1);
        }
        return right;
    }
}
