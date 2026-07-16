package com.interviewprep.dsa.bitManipulation.bitCounting;

//https://leetcode.com/problems/counting-bits/description/
public class CountingBits {
    public int[] countBits(int n) {
        // return countBitsBruteforce(n);

        // return countBitsDPWithRightShift(n);

        return countBitsDPWithBrianKerninghams(n);
    }

    private int[] countBitsBruteforce(int n){
        int[] res = new int[n+1];
        for(int i=0; i<=n; i++){
            int count = 0;
            int num = i;
            while(num != 0){
                count += num & 1;
                num = num >>> 1;
            }
            res[i] = count;
        }
        return res;
    }

    private int[] countBitsDPWithRightShift(int n){
        int[] res = new int[n+1];
        for(int i=1; i<=n; i++){
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }

    private int[] countBitsDPWithBrianKerninghams(int n){
        int[] res = new int[n+1];
        for(int i=1; i<=n; i++){
            res[i] = res[i & (i-1)] + 1;
        }
        return res;
    }
}
