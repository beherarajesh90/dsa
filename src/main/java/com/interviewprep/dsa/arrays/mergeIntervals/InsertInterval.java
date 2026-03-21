package com.interviewprep.dsa.arrays.mergeIntervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();
        int i=0, n = intervals.length;
        int newStart = newInterval[0], newEnd = newInterval[1];
        while (i<n && intervals[i][1]<newStart){
            merged.add(intervals[i]);
            i++;
        }

        while (i<n && intervals[i][0]<=newEnd){
            newStart = Math.min(newStart, intervals[i][0]);
            newEnd = Math.max(newEnd, intervals[i][1]);
            i++;
        }
        merged.add(new int[]{newStart, newEnd});

        while (i<n){
            merged.add(intervals[i]);
            i++;
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
