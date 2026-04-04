package com.interviewprep.dsa.binarySearch.rangeSearch;

public class MaximumValueAtAGivenIndexInABoundedArray {
    public int maxValue(int n, int index, int maxSum) {
        long low = 1, high = maxSum, ans = 1;
        while (low <= high) {
            long mid = (low + high) / 2;
            long left = calc(mid - 1, index);
            long right = calc(mid - 1, n - index - 1);
            long total = mid + left + right;
            if (total <= maxSum) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) ans;
    }

    // Helper function to calculate sum for a segment of length m, starting from peak value
    private long calc(long peak, int m) {
        if (peak > m) {
            // arithmetic series sum from (peak - m + 1) to peak
            return (peak + (peak - m + 1)) * m / 2;
        } else {
            // if peak is not large enough, sum from 1 to peak and add extra ones
            return (peak * (peak + 1)) / 2 + (m - peak);
        }
    }
}
