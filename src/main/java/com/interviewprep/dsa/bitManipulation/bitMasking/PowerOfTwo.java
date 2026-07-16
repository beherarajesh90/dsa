package com.interviewprep.dsa.bitManipulation.bitMasking;

//https://leetcode.com/problems/power-of-two/description/
public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        // return isPowerOfTwoBruteforce(n);
        // return isPowerOfTwoBrianKerninghams(n);
        return isPowerOfTwo2(n);
    }

    private boolean isPowerOfTwoBruteforce(int n){
        if(n <= 0) return false;

        while(n > 1){
            if(n%2 != 0) return false;
            n/=2;
        }

        return true;
    }

    private boolean isPowerOfTwoBrianKerninghams(int n){
        return n > 0 && (n & (n-1)) == 0;
    }

    private boolean isPowerOfTwo2(int n){
        return n>0 && (n & -(n)) == n;
    }
}
