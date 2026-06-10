package com.interviewprep.dsa.dp.knapsack;

//https://www.propeers.in/roadmaps/69692150442404dc257236a1/0-1-knapsack?todoItemId=69b483968789fde24c0c2241
public class Zero1Knapsack {
    private int[][] dp;
    public int zero1Knapsack(int[] v, int[] w, int W) {
        // return dfs(0, v.length, v, w, W);

        // int n = v.length;
        // dp = new int[n][W+1];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }
        // return zero1KnapsackMemo(0, v.length, v, w, W);

        // return zero1KnapsackTab(v, w, W);

        return zero1KnapsackTabSpace(v, w, W);
    }

    //recursive
    private int dfs(int indx, int n, int[] v, int[] w, int W){
        if(indx >= n || W == 0) return 0;

        // skip the weight if its more than capacity and continue
        if(w[indx] > W) return dfs(indx+1, n, v, w, W);

        return Math.max(v[indx] + dfs(indx+1, n, v, w, W - w[indx]), dfs(indx+1, n, v, w, W));
    }

    //memoization
    private int zero1KnapsackMemo(int indx, int n, int[] v, int[] w, int W){
        if(indx >= n || W == 0) return 0;

        if(dp[indx][W]!=-1) return dp[indx][W];

        // skip the weight if its more than capacity and continue
        if(w[indx] > W){
            dp[indx][W] = zero1KnapsackMemo(indx+1, n, v, w, W);
        } else{
            dp[indx][W] = Math.max(v[indx] + zero1KnapsackMemo(indx+1, n, v, w, W - w[indx]), zero1KnapsackMemo(indx+1, n, v, w, W));
        }

        return dp[indx][W];
    }

    //tabulation
    private int zero1KnapsackTab(int[] v, int[] w, int W){
        int n = v.length;
        int[][] dp = new int[n+1][W+1];

        for(int r=1; r<=n; r++){
            for(int c=1; c<=W; c++){
                if(w[r-1] <= c){
                    dp[r][c] = Math.max(dp[r-1][c], v[r-1] + dp[r-1][c-w[r-1]]);
                } else{
                    dp[r][c] = dp[r - 1][c];
                }
            }
        }

        return dp[n][W];
    }

    //tabulation spacce optimized (time: O(n * W), space: O(W))
    private int zero1KnapsackTabSpace(int[] v, int[] w, int W){
        int n = v.length;
        int[] dp = new int[W+1];

        for(int r=1; r<=n; r++){
            int prev = 0;
            for(int c=W; c>=0; c--){
                if(w[r-1] <= c){
                    dp[c] = Math.max(dp[c], v[r-1] + dp[c-w[r-1]]);
                }
            }
        }

        return dp[W];
    }
}
