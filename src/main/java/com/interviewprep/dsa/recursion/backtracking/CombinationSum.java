package com.interviewprep.dsa.recursion.backtracking;

import java.util.*;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, result, new ArrayList<>(), 0);
        return result;
    }

    private void backtrack(int[] candidates, int target, List<List<Integer>> result, List<Integer> curRes, int curInd) {
        if (target == 0){
            result.add(new ArrayList<>(curRes));
            return;
        }

        if (target < 0 || curInd >= candidates.length){
            return;
        }

        curRes.add(candidates[curInd]);
        backtrack(candidates, target-candidates[curInd], result, curRes, curInd);
        curRes.removeLast();
        backtrack(candidates, target, result, curRes, curInd+1);
    }
    List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();
        int[] candidates = {1,2,3};
        List<List<Integer>> result = solution.combinationSum(candidates, 3);
        System.out.println(result);
    }
}
