package com.interviewprep.dsa.dp.optimalSubstructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // return dfs(nums, 0, -1); //i - curIndx, j - last included element index

        // return lengthOfLISTab(nums);
        return lengthOfLISTabBinarySearch(nums);
    }

    //recursive(O(2^n) as each number has two possibilities)
    private int dfs(int[] nums, int i, int j){
        if(i == nums.length) return 0;

        int LIS = dfs(nums, i+1, j);    //do not include

        if(j == -1 || nums[j] < nums[i]){
            LIS = Math.max(LIS, 1 + dfs(nums, i+1, i)); //include
        }

        return LIS;
    }

    private int lengthOfLISTab(int[] nums){
        int[] LIS = new int[nums.length];
        Arrays.fill(LIS, 1);

        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    LIS[i] = Math.max(LIS[i], 1 + LIS[j]);
                }
            }
        }

        return Arrays.stream(LIS).max().getAsInt();
    }

    //tabulation with binary search(O(nlogn))
    private int lengthOfLISTabBinarySearch(int[] nums){
        List<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);

        int LIS = 1;
        for (int i = 1; i < nums.length; i++) {
            if (dp.getLast() < nums[i]) {
                dp.add(nums[i]);
                LIS++;
                continue;
            }

            int idx = Collections.binarySearch(dp, nums[i]);
            if (idx < 0) idx = -idx - 1;
            dp.set(idx, nums[i]);
        }

        return LIS;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        System.out.println(lis.lengthOfLIS(new int[] { 3,5,4,2,3,4,5,6 }));
    }
}
