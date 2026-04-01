package com.interviewprep.dsa.arrays.prefixSums;

import java.util.TreeSet;

//https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/description/
public class MaxSumOfRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length, cols = matrix[0].length, result = Integer.MIN_VALUE;

        for (int left = 0; left < cols; left++) {
            int[] rowSum = new int[rows];
            for (int right = left; right < cols; right++) {
                for (int i = 0; i < rows; i++) {
                    rowSum[i] += matrix[i][right];
                }
                // TreeSet to maintain the prefix sums in sorted order
                TreeSet<Integer> prefixSums = new TreeSet<>();
                prefixSums.add(0);
                int currSum = 0,currMax=Integer.MIN_VALUE;
                for (int sum : rowSum) {
                    currSum += sum;
                    // find the smallest prefix >= currSum - k
                    Integer target = prefixSums.ceiling(currSum - k);
                    if (target != null) {
                        currMax = Math.max(currMax, currSum - target);
                    }
                    prefixSums.add(currSum);
                }
                result = Math.max(result, currMax);
            }
        }
        return result;
    }

    public int maxSumSubmatrixMuchOptimized(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;

        // Optimization: Always iterate based on the smaller dimension
        // If rows > cols, we squash rows into 1D columns.
        // Otherwise, we squash columns into 1D rows.
        boolean isRowLarger = rows > cols;
        int outerLimit = isRowLarger ? cols : rows;
        int innerLimit = isRowLarger ? rows : cols;

        for (int i = 0; i < outerLimit; i++) {
            int[] sums = new int[innerLimit];
            for (int j = i; j < outerLimit; j++) {
                for (int c = 0; c < innerLimit; c++) {
                    // Access matrix correctly based on orientation
                    sums[c] += isRowLarger ? matrix[c][j] : matrix[j][c];
                }

                // Solve the 1D problem for this "squashed" slice
                maxSum = Math.max(maxSum, findMaxSubarraySum(sums, k));
                if (maxSum == k) return k;
            }
        }
        return maxSum;
    }

    private int findMaxSubarraySum(int[] arr, int k) {
        // Optimization 1: Check Kadane's first.
        // If the unconstrained max is <= k, it's our best candidate.
        int currentMax = 0, maxKadane = Integer.MIN_VALUE;
        for (int x : arr) {
            currentMax = Math.max(x, currentMax + x);
            maxKadane = Math.max(maxKadane, currentMax);
        }
        if (maxKadane <= k) return maxKadane;

        // Optimization 2: Use TreeSet only if Kadane fails (i.e., Kadane > k)
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int prefixSum = 0;
        int result = Integer.MIN_VALUE;

        for (int x : arr) {
            prefixSum += x;
            // We want: prefixSum - target <= k  =>  target >= prefixSum - k
            Integer target = set.ceiling(prefixSum - k);
            if (target != null) {
                result = Math.max(result, prefixSum - target);
            }
            if (result == k) return k;
            set.add(prefixSum);
        }
        return result;
    }
}
