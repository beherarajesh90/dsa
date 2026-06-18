package com.interviewprep.dsa.dp.intervalOrRangeDP;

public class MinimumCostToMergeStones {
    private int[][] dp;
    private int[] prefixSum;
    public int mergeStones(int[] stones, int k) {

        // int n = stones.length;
        // if((n-1) % (k-1) != 0) return -1;
        // prefixSum = new int[n+1];
        // for(int i=0; i<n; i++) prefixSum[i+1] = prefixSum[i] + stones[i];
        // return dfs(0, stones.length-1, k, stones);

        // int n = stones.length;
        // if((n-1) % (k-1) != 0) return -1;
        // prefixSum = new int[n+1];
        // for(int i=0; i<n; i++) prefixSum[i+1] = prefixSum[i] + stones[i];

        // dp = new int[n][n];
        // for(int[] arr: dp)  Arrays.fill(arr, -1);
        // return mergeStonesMemo(0, stones.length-1, k, stones);

        return mergeStonesTab(stones, k);
    }

    //recursive
    private int dfs(int left, int right, int k, int[] stones){

        if(left == right) return 0;

        int minTotal = Integer.MAX_VALUE;
        for(int m=left; m<right; m+=(k-1)){
            minTotal = Math.min(minTotal, dfs(left, m, k, stones) + dfs(m+1, right, k, stones));
        }

        if((right - left)%(k-1) == 0){
            minTotal += (prefixSum[right+1] - prefixSum[left]);
        }

        return minTotal;
    }

    //memoization
    private int mergeStonesMemo(int left, int right, int k, int[] stones){

        if(left == right) return 0;

        if(dp[left][right] != -1) return dp[left][right];

        int minTotal = Integer.MAX_VALUE;
        for(int m=left; m<right; m+=(k-1)){
            minTotal = Math.min(minTotal, mergeStonesMemo(left, m, k, stones) + mergeStonesMemo(m+1, right, k, stones));
        }

        if((right - left)%(k-1) == 0){
            minTotal += (prefixSum[right+1] - prefixSum[left]);
        }

        return dp[left][right] = minTotal;
    }

    //tabulation
    private int mergeStonesTab(int[] stones, int k){
        int n = stones.length;
        if((n-1) % (k-1) != 0) return -1;

        int[] prefixSum = new int[n+1];
        for(int i=0; i<n; i++) prefixSum[i+1] = prefixSum[i] + stones[i];

        int[][] dp = new int[n][n];

        for(int len=2; len<=n; len++){
            for(int left=0; left+len-1<n; left++){
                int right = left + len - 1;

                int minTotal = Integer.MAX_VALUE;
                for(int m=left; m<right; m+=(k-1)){
                    minTotal = Math.min(minTotal, dp[left][m] + dp[m+1][right]);
                }

                if((right - left)%(k-1) == 0){
                    minTotal += (prefixSum[right+1] - prefixSum[left]);
                }

                dp[left][right] = minTotal;

            }
        }

        return dp[0][n-1];
    }
}
