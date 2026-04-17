package com.interviewprep.dsa.recursion.basicRecursiveFunctions;

//https://leetcode.com/problems/powx-n/description/
public class Pow {
    public double myPow(double x, int n) {
        double base = x;
        int exp = n;
        if(n == 0) return 1;
        if(n < 0){
            base = 1/base;
            exp = Math.abs(exp);
        }
        return pow(base, exp);
    }

    private double pow(double base, int exp){
        if(exp == 0) return 1.0;
        double half = pow(base, exp/2);
        if (exp%2 == 0) return half * half;
        else return half * half * base;
    }
}
