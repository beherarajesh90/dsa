package com.interviewprep.dsa.arrays.prefixSums;

//https://leetcode.com/problems/count-of-range-sum/description/
public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefixSum = new long[n+1];
        for(int i=0; i<n; i++){
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }
        return modifiedMergeSort(prefixSum, 0, n+1, lower, upper);
    }

    private int modifiedMergeSort(long[] prefixSum,int lo, int hi, int lower, int upper){
        if(hi - lo <= 1) return 0;
        int mid = lo + (hi-lo)/2;

        //divide
        int count = modifiedMergeSort(prefixSum,lo,mid,lower,upper) + modifiedMergeSort(prefixSum,mid,hi,lower,upper);

        //count valid pairs invalid<---valid pairs---<invalid
        int start=mid, end=mid;
        for(int i=lo; i<mid; i++){
            while(start<hi && prefixSum[start]-prefixSum[i]<lower) start++;
            while(end<hi && prefixSum[end]-prefixSum[i]<=upper) end++;
            count+=(end-start);
        }

        //merge step
        long[] sorted = new long[hi-lo];
        int p=lo, q=mid, r=0;
        while(p<mid || q<hi){
            if(q>=hi || (p<mid && prefixSum[p]<=prefixSum[q])){
                sorted[r++] = prefixSum[p++];
            } else{
                sorted[r++] = prefixSum[q++];
            }
        }

        //copy sorted array to prefixSum
        System.arraycopy(sorted,0,prefixSum,lo,sorted.length);
        return count;
    }
}
