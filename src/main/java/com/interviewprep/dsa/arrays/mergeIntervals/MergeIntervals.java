package com.interviewprep.dsa.arrays.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][0];

        // Sort intervals based on the starting time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            // If the merged list is empty or there is no overlap
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                // There is overlap, merge the intervals
                merged.getLast()[1] = Math.max(merged.getLast()[1],interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
