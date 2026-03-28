package com.interviewprep.dsa.arrays.prefixSums;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0,1);
        int count=0, prefixSumMod=0;
        for(int num: nums){
            prefixSumMod = (prefixSumMod+num)%k;
            prefixSumMod = prefixSumMod < 0 ? prefixSumMod+k : prefixSumMod;
            count+=remainderCount.getOrDefault(prefixSumMod,0);
            remainderCount.put(prefixSumMod, remainderCount.getOrDefault(prefixSumMod,0)+1);
        }
        return count;
    }
}
