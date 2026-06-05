package com.interviewprep.dsa.dp.basicDP;

//https://leetcode.com/problems/fibonacci-number/description/
public class FibonacciNumber {
    public int fib(int n) {
        if(n <=1 ) return n;
        int a = 0, b = 1;
        for(int i=2; i<=n; i++){
            int cur = a + b;
            a = b;
            b = cur;
        }
        return b;
    }
}
