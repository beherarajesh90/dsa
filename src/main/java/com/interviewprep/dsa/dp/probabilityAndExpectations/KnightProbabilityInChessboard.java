package com.interviewprep.dsa.dp.probabilityAndExpectations;

//https://leetcode.com/problems/knight-probability-in-chessboard/description/
public class KnightProbabilityInChessboard {
    private double[][][] dp;
    private int[][] dirs;
    public double knightProbability(int n, int k, int row, int column) {
        // dirs = new int[][]{{1,2},{-1,2},{1,-2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};

        // return dfs(n, k, row, column);

        // dp = new double[k+1][n][n];
        // for(double[][] arr: dp){
        //     for(double[] a: arr){
        //         Arrays.fill(a, -1);
        //     }
        // }
        // return knightProbabilityMemo(n, k, row, column);

        return knightProbabilityTab(n, k, row, column);
    }

    //recursive
    //formula P(cur state) = summation(P(cur to next state) * P(next state))
    private double dfs(int n, int k, int row, int col){

        //base case: if indexes move out of chess board then probability of knight being on the board is zero
        if(row < 0 || row >= n || col<0 || col>=n) return 0.0;

        // base case: if not moves, knight stays on the board which is 1 probability
        if(k == 0) return 1.0;

        double probability = 0;
        for(int[] dir: dirs){
            probability += dfs(n, k-1, row + dir[0], col + dir[1]);
        }

        //probability of each direction is 1/8
        return probability/8.0;
    }

    //memoization
    //formula P(cur state) = summation(P(cur to next state) * P(next state))
    private double knightProbabilityMemo(int n, int k, int row, int col){

        //base case: if indexes move out of chess board then probability of knight being on the board is zero
        if(row < 0 || row >= n || col<0 || col>=n) return 0.0;

        // base case: if not moves, knight stays on the board which is 1 probability
        if(k == 0) return 1.0;

        if(dp[k][row][col] != -1) return dp[k][row][col];

        double probability = 0;
        for(int[] dir: dirs){
            probability += knightProbabilityMemo(n, k-1, row + dir[0], col + dir[1]);
        }

        //probability of deach direction is 1/8
        return dp[k][row][col] = probability/8.0;
    }

    //tabulation
    private double knightProbabilityTab(int n, int k, int row, int col){
        dirs = new int[][]{{1,2},{-1,2},{1,-2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};

        // cur[r][c] = probability of knight being at (r,c) after current step
        double[][] cur = new double[n][n];
        // probability of knight being at row , col is possible with 0 steps which is 1 probablity
        cur[row][col] = 1.0;

        for(int step=1; step<=k; step++){
            double[][] next = new double[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(cur[i][j] == 0) continue;

                    // Spread this cell's probability to all valid knight destinations
                    for(int[] dir: dirs){
                        int nr = i + dir[0], nc = j + dir[1];
                        if(nr >=0 && nr < n && nc>=0 && nc<n){
                            next[nr][nc] += cur[i][j]/8.0;
                        }
                    }
                }
            }
            cur = next;
        }

        // Sum remaining probability across the entire board
        double result = 0.0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                result+=cur[i][j];
            }
        }
        return result;
    }
}
