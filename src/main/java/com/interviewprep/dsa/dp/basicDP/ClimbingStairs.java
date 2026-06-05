package com.interviewprep.dsa.dp.basicDP;

//https://leetcode.com/problems/climbing-stairs/description/
public class ClimbingStairs {
    public int climbStairs(int n) {
        if( n <= 2) return n;
        int n1 = 1, n2 = 2;
        for(int i=3; i<=n; i++){
            int steps = n1 + n2;
            n1 = n2;
            n2 = steps;
        }
        return n2;
    }
}
