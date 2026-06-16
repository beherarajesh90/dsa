package com.interviewprep.dsa.dp.intervalOrRangeDP;

//https://leetcode.com/problems/burst-balloons/description/
public class BurstBalloons {
    private int[][] dp;
    public int maxCoins(int[] nums) {
        // List<Integer> balloons = new ArrayList<>();
        // for(int balloon: nums) balloons.add(balloon);
        // return backtrack(balloons);

        // int n = nums.length;
        // int[] arr = new int[n+2];
        // //pad 1 at the beginning and at the end
        // arr[0] = 1;
        // arr[n+1] = 1;

        // for(int i=0; i<n; i++){
        //     arr[i+1] = nums[i];
        // }

        // return dfs(0, n+1, arr);

        // int n = nums.length;
        // int[] arr = new int[n+2];
        // //pad 1 at the beginning and at the end
        // arr[0] = 1;
        // arr[n+1] = 1;
        // for(int i=0; i<n; i++){
        //     arr[i+1] = nums[i];
        // }

        // // dp(left, right) maximum coins obtainable from balloons strictly between left and right.
        // dp = new int[n+2][n+2];
        // return maxCoinsMemo(0, n+1, arr);

        return maxCoinsTab(nums);
    }

    //backtrack this solution cannot be optimized
    // private int backtrack(List<Integer> balloons){
    //     if(balloons.isEmpty()) return 0;

    //     int maxCoins = 0;

    //     for(int i=0; i<balloons.size(); i++){

    //         int left = (i==0) ? 1 : balloons.get(i-1);
    //         int right = (i==balloons.size()-1) ? 1 : balloons.get(i+1);
    //         int coins = left * balloons.get(i) * right;

    //         int val = balloons.remove(i);
    //         maxCoins = Math.max(maxCoins, coins + backtrack(balloons));
    //         balloons.add(i, val);
    //     }

    //     return maxCoins;
    // }

    //recursive
    private int dfs(int left, int right, int[] arr){
        //no baloons to burst
        if(left+1 == right) return 0;

        int maxCoins = 0;
        for(int k = left+1; k < right; k++){
            //burst each balloon at last.
            int coins = dfs(left, k, arr) + dfs(k, right, arr) + (arr[left] * arr[k] * arr[right]);
            maxCoins = Math.max(maxCoins, coins);
        }

        return maxCoins;
    }

    //memoization
    private int maxCoinsMemo(int left, int right, int[] arr){
        //no baloons to burst
        if(left+1 == right) return 0;

        if(dp[left][right]!=0) return dp[left][right];

        for(int k = left+1; k < right; k++){
            //burst each balloon at last.
            int coins = maxCoinsMemo(left, k, arr) + maxCoinsMemo(k, right, arr) + (arr[left] * arr[k] * arr[right]);
            dp[left][right] = Math.max(dp[left][right], coins);
        }

        return dp[left][right];
    }

    //tabulation
    private int maxCoinsTab(int[] nums){
        int n = nums.length;
        int[] arr = new int[n+2];
        //pad 1 at the beginning and at the end
        arr[0] = 1;
        arr[n+1] = 1;
        for(int i=0; i<n; i++){
            arr[i+1] = nums[i];
        }

        // dp(left, right) maximum coins obtainable from balloons strictly between left and right.
        int[][] dp = new int[n+2][n+2];

        for(int len = 2; len<n+2; len++){
            for(int left = 0; left+len < n+2; left++){
                int right = left + len;
                for(int k = left+1; k < right; k++){

                    int coins = dp[left][k] + dp[k][right] + (arr[left] * arr[k] * arr[right]);
                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }

        return dp[0][n+1];
    }
}

/*
    Instead of choosing the first balloon to burst,
    choose the last balloon to burst in an interval.

Let

arr = [1, 3, 1, 5, 8, 1]

Define:

dp(left, right) = maximum coins obtainable from balloons strictly between left and right.

If balloon k is burst last inside (left, right):

left ... k ... right

then all other balloons inside are already gone.

So the final burst gives:

arr[left] * arr[k] * arr[right]

and

dp(left,right)
=
max(
    dp(left,k)
  + dp(k,right)
  + arr[left]*arr[k]*arr[right]
)
*/