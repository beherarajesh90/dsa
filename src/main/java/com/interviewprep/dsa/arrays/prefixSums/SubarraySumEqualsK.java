package com.interviewprep.dsa.arrays.prefixSums;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum1(int[] nums, int k) {
        int count=0, curSum=0;
        Map<Integer,Integer> prefixSum = new HashMap<>();
        prefixSum.put(curSum,1);
        for(int num: nums){
            curSum+=num;
            count+=prefixSum.getOrDefault(curSum-k, 0);
            prefixSum.put(curSum,prefixSum.getOrDefault(curSum, 0)+1);
        }
        return count;
    }
}
