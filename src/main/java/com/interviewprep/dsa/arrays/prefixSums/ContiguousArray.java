package com.interviewprep.dsa.arrays.prefixSums;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/contiguous-array/description/
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        Map<Integer,Integer> countToIndex = new HashMap<>();
        countToIndex.put(0,-1);
        int maxLen=0, count=0;
        for(int i=0; i<nums.length; i++){
            count+=(nums[i]==1 ? 1 : -1);
            if(countToIndex.containsKey(count)){
                maxLen = Math.max(maxLen, i - countToIndex.get(count));
            } else{
                countToIndex.put(count, i);
            }
        }
        return maxLen;
    }
}
