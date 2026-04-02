package com.interviewprep.dsa.binarySearch.basicBinarySearch;

public class ArrangingCoins {
    public int arrangeCoins(int n) {
        long left=1, right=n;
        while(left<=right){
            long mid = left + (right-left)/2;
            long cur = mid * (mid+1)/2;
            if(cur == n) return (int)mid;
            else if(cur < n) left = mid+1;
            else right = mid -1;
        }
        return (int)right;
    }
}
