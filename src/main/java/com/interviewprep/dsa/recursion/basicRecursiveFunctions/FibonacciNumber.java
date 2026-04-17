package com.interviewprep.dsa.recursion.basicRecursiveFunctions;

//https://leetcode.com/problems/fibonacci-number/description/
public class FibonacciNumber {
    public int fib(int n) {
        if(n<=1) return n;
        int a=0,b=1;
        for(int i=2; i<=n; i++){
            int curVal = a + b;
            a = b;
            b = curVal;
        }
        return b;
    }

    public int fibRec(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        return fibRec(n-1) + fibRec(n-2);
    }
}
