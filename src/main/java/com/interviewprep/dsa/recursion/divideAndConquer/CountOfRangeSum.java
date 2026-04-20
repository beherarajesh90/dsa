package com.interviewprep.dsa.recursion.divideAndConquer;

public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefixSum = new long[n+1];
        long curSum = 0;
        for (int i=0; i<n; i++){
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }
        return mergeSort(prefixSum, 0, n+1, lower, upper);
    }

    private int mergeSort(long[] prefixSum, int left, int right, int lower, int upper) {
        if(right - left <= 1) return 0;
        int mid = left + (right - left)/2;
        int count = mergeSort(prefixSum, left, mid, lower, upper) + mergeSort(prefixSum, mid, right, lower, upper);
        return merge(prefixSum, left, mid, right, lower, upper, count);
    }

    private int merge(long[] prefixSum, int left, int mid, int right, int lower, int upper, int count) {
        int start = mid, end = mid;
        for (int l = left; l < mid; l++){
            while (start < right && prefixSum[start] - prefixSum[l] < lower) start++;
            while (end < right && prefixSum[end] - prefixSum[l] <= upper) end++;
            count+=(end - start);
        }

        long[] temp = new long[right -left];
        int i = left, j = mid, k = 0;
        while (i < mid && j<right){
            if(prefixSum[j] < prefixSum[i]) temp[k++] = prefixSum[j++];
            else temp[k++] = prefixSum[i++];
        }
        while (i < mid){
            temp[k++] = prefixSum[i++];
        }
        while (j<right){
            temp[k++] = prefixSum[j++];
        }
        System.arraycopy(temp, 0, prefixSum, left, temp.length);
        return count;
    }

    public static void main(String[] args) {
        CountOfRangeSum solution = new CountOfRangeSum();
        int[] nums = {-2,5,-1};
        System.out.println(solution.countRangeSum(nums, -2, 2));
    }
}
