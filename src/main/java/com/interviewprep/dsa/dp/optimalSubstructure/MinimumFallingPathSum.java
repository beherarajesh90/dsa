package com.interviewprep.dsa.dp.optimalSubstructure;

//https://leetcode.com/problems/minimum-falling-path-sum/
public class MinimumFallingPathSum {
    private int[][] dp;
    public int minFallingPathSum(int[][] matrix) {
        // int m = matrix.length;
        // int minPath = Integer.MAX_VALUE;
        // for(int c = 0; c < m; c++){
        //     minPath = Math.min(minPath, dfs(0, c, m, matrix));
        // }
        // return minPath;

        // int m = matrix.length;
        // int minPath = Integer.MAX_VALUE;
        // dp = new int[m][m];
        // for(int[] arr: dp){
        //     Arrays.fill(arr, -1);
        // }

        // for(int c = 0; c < m; c++){
        //     minPath = Math.min(minPath, minFallingPathSumMemo(0, c, m, matrix));
        // }
        // return minPath;

        // return minFallingPathSumTab(matrix);

        return minFallingPathSumTabSpaceOptimized(matrix);
    }

    //recursive(O(3^n))
     private int dfs(int i, int j, int m, int[][] matrix){
         if(i >= m) return 0;
         if(j<0 || j>=m) return Integer.MAX_VALUE;

         int leftDiag = dfs(i+1, j-1, m, matrix);
         int down = dfs(i+1, j, m, matrix);
         int rightDiag = dfs(i+1, j+1, m, matrix);

         return matrix[i][j] + Math.min(leftDiag, Math.min(down, rightDiag));
     }

    //memoization(time and space: O(n^2))
     private int minFallingPathSumMemo(int i, int j, int m, int[][] matrix){
         if(i >= m) return 0;
         if(j<0 || j>=m) return Integer.MAX_VALUE;
         if(dp[i][j]!=-1) return dp[i][j];

         int leftDiag = minFallingPathSumMemo(i+1, j-1, m, matrix);
         int down = minFallingPathSumMemo(i+1, j, m, matrix);
         int rightDiag = minFallingPathSumMemo(i+1, j+1, m, matrix);

         dp[i][j] = matrix[i][j] + Math.min(leftDiag, Math.min(down, rightDiag));
         return dp[i][j];
     }

    //tabulation(time:O(n^2) and space: O(n))
    private int minFallingPathSumTab(int[][] matrix){
        int m = matrix.length;
        int[] dp = new int[m];

        //fill the dp with first row
        for(int c=0; c<m; c++){
            dp[c] = matrix[0][c];
        }

        for(int r=1; r<m; r++){
            int leftUp = Integer.MAX_VALUE;
            for(int c=0; c<m; c++){
                int midUp = dp[c];
                int rightUp = c<m-1 ? dp[c+1] : Integer.MAX_VALUE;
                dp[c] = matrix[r][c] + Math.min(leftUp, Math.min(midUp, rightUp));
                leftUp = midUp;
            }
        }

        //iterate and find the min path
        int minPath = Integer.MAX_VALUE;
        for(int sum: dp){
            if(minPath > sum){
                minPath = sum;
            }
        }
        return minPath;
    }

    //tabulation(time:O(n^2) and space: O(1))
    private int minFallingPathSumTabSpaceOptimized(int[][] matrix){
        int m = matrix.length;

        for(int r=1; r<m; r++){
            for(int c=0; c<m; c++){
                int leftUp = c>0 ? matrix[r-1][c-1] :Integer.MAX_VALUE;
                int midUp = matrix[r-1][c];
                int rightUp = c<m-1 ? matrix[r-1][c+1] : Integer.MAX_VALUE;
                matrix[r][c] = matrix[r][c] + Math.min(leftUp, Math.min(midUp, rightUp));
            }
        }

        int minPath = Integer.MAX_VALUE;
        for(int c=0; c<m; c++){
            if(matrix[m-1][c] < minPath){
                minPath = matrix[m-1][c];
            }
        }
        return minPath;
    }
}
