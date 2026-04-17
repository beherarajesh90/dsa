package com.interviewprep.dsa.recursion.basicRecursiveFunctions;

//https://leetcode.com/problems/count-good-numbers/description/
public class CountGoodNumbers {
    public static int MOD = 1000000007;
    public int countGoodNumbersIterative(long n) {
        long evenPositions = (n+1)/2;
        long oddPositions = n/2;
        long result = (modPower(5, evenPositions) * modPower(4, oddPositions)) % MOD;
        return (int)result;
    }

    private long modPower(long base, long exp){
        base = base % MOD;  //just in case base is larger. in this problem base is always 5 or  4
        long res = 1;
        while(exp > 0){
            //below equivalent to exp%2==1
            if((exp & 1) == 1){
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;    //exp/2
        }
        return res;
    }

    public int countGoodNumbers(long n) {
        return (int)((power(5, (long)Math.ceil(n/2.0)) * power(4, (long)Math.floor(n/2.0))) % MOD);
    }

    private long power(long base, long exp){
        if(exp == 0) return 1;
        if(exp%2 == 0) return power((base * base) % MOD, exp/2);
        return (base * power(base, exp - 1)) % MOD;
    }

}
