package com.interviewprep.dsa.dp.intervalOrRangeDP;

import java.util.Arrays;
import java.util.Map;

//https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
public class MinimumCostToCutAStick {
    private int[][] dp;
    private Map<String, Integer> memo;
    public int minCost(int n, int[] cuts) {
        // return dfs(0, n, cuts);

        // dp = new int[n+1][n+1];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }
        // return minCostMemo(0, n, cuts);

        // memo = new HashMap<>();
        // return minCostMemoOptimized(0, n, cuts);
        //--------------------------------------------------------------------
        // //sort the cuts array to avoid unnecessary checks
        // Arrays.sort(cuts);
        // int m = cuts.length;
        // dp = new int[m+2][m+2];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }
        // //pad with 0 and n
        // int arr[] = new int[m+2];
        // for(int i=0; i<m; i++){
        //     arr[i+1] = cuts[i];
        // }
        // arr[0] = 0;
        // arr[m+1] = n;
        // return minCostMemoOptimal(0, arr.length-1, arr);

        return minCostTab(n, cuts);
    }

    //recursive - TLE (check the optimal solution with recursive)
    // private int dfs(int left, int right, int[] cuts){
    //     if(right - left == 1) return 0;

    //     int minCuts = Integer.MAX_VALUE;
    //     for(int cut: cuts){
    //         if(left < cut && cut < right){
    //             int cost = (right - left) + dfs(left, cut, cuts) + dfs(cut, right, cuts);
    //             minCuts = Math.min(minCuts, cost);
    //         }
    //     }

    //     return minCuts == Integer.MAX_VALUE ? 0 : minCuts;
    // }

    //memoization - MLE
    // private int minCostMemo(int left, int right, int[] cuts){
    //     if(right - left == 1) return 0;

    //     if(dp[left][right]!=-1) return dp[left][right];

    //     int minCuts = Integer.MAX_VALUE;
    //     for(int cut: cuts){
    //         if(left < cut && cut < right){
    //             int cost = (right - left) + minCostMemo(left, cut, cuts) + minCostMemo(cut, right, cuts);
    //             minCuts = Math.min(minCuts, cost);
    //         }
    //     }

    //     return dp[left][right] = minCuts == Integer.MAX_VALUE ? 0 : minCuts;
    // }

    //memoization
    // private int minCostMemoOptimized(int left, int right, int[] cuts){
    //     if(right - left == 1) return 0;

    //     String key = left+","+right;
    //     if(memo.containsKey(key)) return memo.get(key);

    //     int minCuts = Integer.MAX_VALUE;
    //     for(int cut: cuts){
    //         if(left < cut && cut < right){
    //             int cost = (right - left) + minCostMemoOptimized(left, cut, cuts) + minCostMemoOptimized(cut, right, cuts);
    //             minCuts = Math.min(minCuts, cost);
    //         }
    //     }

    //     int val = minCuts == Integer.MAX_VALUE ? 0 : minCuts;
    //     memo.put(key, val);
    //     return memo.get(key);
    // }

    //memoization optimal
    private int minCostMemoOptimal(int left, int right, int[] arr){
        if(right - left <= 1) return 0;

        if(dp[left][right] != -1) return dp[left][right];

        int minCuts = Integer.MAX_VALUE;
        for(int k = left+1; k<right; k++){
            int cost = (arr[right] - arr[left]) + minCostMemoOptimal(left, k, arr) + minCostMemoOptimal(k, right, arr);
            minCuts = Math.min(minCuts, cost);
        }

        dp[left][right] = minCuts == Integer.MAX_VALUE ? 0 : minCuts;
        return dp[left][right];
    }

    //tabulation
    private int minCostTab(int n, int[] cuts){
        Arrays.sort(cuts);
        int m = cuts.length;
        int[][] dp = new int[m+2][m+2];

        //pad with 0 and n
        int arr[] = new int[m+2];
        for(int i=0; i<m; i++){
            arr[i+1] = cuts[i];
        }
        arr[0] = 0;
        arr[m+1] = n;

        for(int gap=2; gap < m+2; gap++){
            for(int left = 0; left+gap < m+2; left++){
                int right = left+gap;

                int minCuts = Integer.MAX_VALUE;
                for(int k = left+1; k<right; k++){
                    int cost = (arr[right] - arr[left]) + dp[left][k] + dp[k][right];
                    minCuts = Math.min(minCuts, cost);
                }
                dp[left][right] = minCuts == Integer.MAX_VALUE ? 0 : minCuts;
            }
        }
        return dp[0][m+1];
    }
}
