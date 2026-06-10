package com.interviewprep.dsa.dp.knapsack;

//https://leetcode.com/problems/partition-equal-subset-sum/
public class PartitionEqualSubsetSum {
    private Boolean dp[][];
    public boolean canPartition(int[] nums) {

        int sum = 0;
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
        }
        // if the sum is odd, its impossible to split the array
        if(sum%2 == 1) return false;
        sum/=2;

        // return dfs(0, sum, nums);

        // dp = new Boolean[nums.length][sum+1];
        // return canPartitionMemo(0, sum, nums);

        // return canPartitionTab(sum, nums);

        return canPartitionTabSpace(sum, nums);
    }

    //recursive
    private boolean dfs(int i, int target,int[] nums){
        if(i == nums.length) return target == 0;
        if(target < 0) return false;
        return dfs(i+1, target, nums) || dfs(i+1, target - nums[i], nums);
    }

    //memoization
    private boolean canPartitionMemo(int i, int target,int[] nums){
        if(i == nums.length) return target == 0;
        if(target < 0) return false;
        if(dp[i][target]!=null) return dp[i][target];

        dp[i][target] = canPartitionMemo(i+1, target, nums) || canPartitionMemo(i+1, target - nums[i], nums);
        return dp[i][target];
    }

    //tabulation
    private boolean canPartitionTab(int target,int[] nums){

        int n = nums.length;
        boolean[][] dp = new boolean[n+1][target+1];
        for(boolean[] arr: dp){
            arr[0] = true;
        }

        for(int r=1; r<=n; r++){
            for(int c=target; c>=1; c--){
                if(c >= nums[r-1]){
                    dp[r][c] = dp[r-1][c] || dp[r-1][c - nums[r-1]];    //skip || dont skip
                } else{
                    dp[r][c] = dp[r-1][c];
                }
            }
        }
        return dp[n][target];
    }

    //tabulation space optimized
    private boolean canPartitionTabSpace(int target,int[] nums){

        int n = nums.length;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;

        for(int r=0; r<n; r++){
            for(int c=target; c>=nums[r]; c--){
                dp[c] = dp[c] || dp[c - nums[r]];    //skip || dont skip
            }
        }
        return dp[target];
    }
}
