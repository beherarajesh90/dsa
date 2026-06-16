package com.interviewprep.dsa.dp.intervalOrRangeDP;

//https://www.propeers.in/roadmaps/69692150442404dc257236a1/matrix-chain-multiplication?todoItemId=69b4850b8789fde24c0d960b
public class MatrixChainMultiplication {

    private int[][] dp;
    public int matrixChainMultiplication(int[] nums) {
        // return dfs(0, nums.length-1, nums);

        // int n = nums.length;
        // dp = new int[n][n];
        // for(int[] arr: dp) Arrays.fill(arr, -1);
        // return matrixChainMultiplicationMemo(0, nums.length-1, nums);

        return matrixChainMultiplicationTab(nums);
    }

    //recursive (Using Recursion - O(2^n) and O(n) Space)
    private int dfs(int left, int right, int[] nums){
        if(left+1 == right) return 0;

        int minCalc = Integer.MAX_VALUE;
        for(int k = left+1; k<right; k++){
            int calc = dfs(left, k, nums) + dfs(k, right, nums) + (nums[left] * nums[k] * nums[right]);
            minCalc = Math.min(minCalc, calc);
        }

        return minCalc == Integer.MAX_VALUE ? 0 : minCalc;
    }

    //memoization(time: O(n^3), space: O(n^2))
    private int matrixChainMultiplicationMemo(int left, int right, int[] nums){
        if(left+1 == right) return 0;

        if(dp[left][right]!=-1) return dp[left][right];

        int minCalc = Integer.MAX_VALUE;
        for(int k = left+1; k<right; k++){
            int calc = matrixChainMultiplicationMemo(left, k, nums) + matrixChainMultiplicationMemo(k, right, nums) + (nums[left] * nums[k] * nums[right]);
            minCalc = Math.min(minCalc, calc);
        }

        return dp[left][right] = minCalc == Integer.MAX_VALUE ? 0 : minCalc;
    }

    //tabulation
    private int matrixChainMultiplicationTab(int[] nums){
        int n = nums.length;
        int[][] dp = new int[n][n];

        for(int len=2; len<n; len++){
            for(int left = 0; left+len < n; left++){
                int right = left+len;

                int minCalc = Integer.MAX_VALUE;
                for(int k = left+1; k<right; k++){
                    int calc = dp[left][k] + dp[k][right] + (nums[left] * nums[k] * nums[right]);
                    minCalc = Math.min(minCalc, calc);
                }

                dp[left][right] = minCalc == Integer.MAX_VALUE ? 0 : minCalc;
            }
        }

        return dp[0][n-1];
    }

}
