package com.interviewprep.dsa.bitManipulation.bitMasking;

//https://leetcode.com/problems/sum-of-two-integers/description/
public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        // return getSumBruteforce(a, b);

        return getSumXorAndLeftShift(a, b);
    }

    private int getSumXorAndLeftShift(int a, int b){
        while(b!=0){
            int carry = ( a & b ) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    // TLE
    private int getSumBruteforce(int a, int b){
        while(b > 0){
            a = -~a;    // a = a+ 1;
            b = ~-a;    // b = b + 1;
        }

        while(b < 0){
            a = ~-a;    // a = a - 1
            b = -~b;    // b = b + 1
        }

        return a;
    }
}
