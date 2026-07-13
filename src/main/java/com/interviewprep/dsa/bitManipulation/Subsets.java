package com.interviewprep.dsa.bitManipulation;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/subsets/description/
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        // return subsetsUsingIterativeCascading(nums);

        // return subsetsUsingBacktracking(nums);

        return subsetUsingBitManipulation(nums);
    }

    // time: n*2^n, space: n*2^n
    private List<List<Integer>> subsetsUsingIterativeCascading(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for(int num: nums){
            int size = result.size();
            for(int i=0; i<size; i++){
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }

        return result;
    }

    // time: n*2^n, space: n
    private List<List<Integer>> subsetsUsingBacktracking(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result){

        result.add(new ArrayList<>(current));
        for(int i=start; i<nums.length; i++){
            current.add(nums[i]);
            backtrack(i+1, nums, current, result);
            current.remove(current.size() - 1);
        }
    }

    // time: n*2^n, space: n*2^n
    private List<List<Integer>> subsetUsingBitManipulation(int[] nums){
        int n = nums.length;
        int totalSubsets = 1 << n;
        List<List<Integer>> result = new ArrayList<>();

        for(int mask=0; mask<totalSubsets; mask++){
            List<Integer> subset = new ArrayList<>();
            for(int i=0; i<n; i++){
                if((mask & (1 << i)) != 0){
                    subset.add(nums[i]);
                }
            }
            result.add(subset);
        }

        return result;
    }
}
