package com.interviewprep.dsa.arrays.mergeIntervals;

import java.util.Arrays;

public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->a[1] - b[1]);
        int end = Integer.MIN_VALUE, removedCount=0;
        for (int[] interval: intervals){
            if(interval[0]>=end){
                end = interval[1];
            } else {
                removedCount++;
            }
        }
        return removedCount;
    }
}
