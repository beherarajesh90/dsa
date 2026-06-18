package com.interviewprep.dsa.dp.intervalOrRangeDP;

public class RemoveBoxes {
    private int[][][] dp;

    public int removeBoxes(int[] boxes) {

        // return dfs(0, boxes.length-1, 0, boxes);

        // int n = boxes.length;
        // // memo[i][j][k] stores the result for subarray boxes[i...j]
        // // with k boxes of color boxes[i] already waiting on the left.
        // dp = new int[n][n][n];
        // return removeBoxesMemo(0, n-1, 0, boxes);

        return removeBoxesTab(boxes);
    }

    //recursive
    private int dfs(int l, int r, int streak, int[] boxes) {
        //no boxes left to remove
        if (l > r) return 0;

        while (l + 1 <= r && boxes[l] == boxes[l + 1]) {
            l++;
            streak++;
        }

        int res = (streak + 1) * (streak + 1) + dfs(l + 1, r, 0, boxes);

        for (int m = l + 1; m <= r; m++) {
            if (boxes[m] == boxes[l]) {
                res = Math.max(res, dfs(l + 1, m - 1, 0, boxes) + dfs(m, r, streak + 1, boxes));
            }
        }

        return res;
    }

    //memoization
    private int removeBoxesMemo(int l, int r, int streak, int[] boxes) {
        //no boxes left to remove
        if (l > r) return 0;
        if (dp[l][r][streak] > 0) return dp[l][r][streak];

        // --- OPTIMIZATION: Skip identical consecutive boxes ---
        // If we have [1, 1, 1, 2], we treat the three 1s as one unit.
        // We move 'i' forward and increase our "waiting" count 'k'.
        int tmpL = l;
        int tmpStreak = streak;
        while (l + 1 <= r && boxes[l] == boxes[l + 1]) {
            l++;
            streak++;
        }

        // --- STRATEGY A: Pop the current group immediately ---
        // Score = (number of boxes)^2 + remaining subproblem
        int res = (streak + 1) * (streak + 1) + removeBoxesMemo(l + 1, r, 0, boxes);

        // --- STRATEGY B: Wait and Merge ---
        // Look for another box 'm' of the same color further down the array.
        for (int m = l + 1; m <= r; m++) {
            // If we find one, we try to clear the boxes between i and m: dp(i+1, m-1, 0)
            // And then solve the rest, carrying over our current streak: dp(m, j, k+1)
            if (boxes[m] == boxes[l]) {
                res = Math.max(res, removeBoxesMemo(l + 1, m - 1, 0, boxes) + removeBoxesMemo(m, r, streak + 1, boxes));
            }
        }
        // Store in dp using the original i and k before the optimization loop
        return dp[tmpL][r][tmpStreak] = res;
    }

    //tabulation
    private int removeBoxesTab(int[] boxes) {
        int n = boxes.length;
        if (n == 0) return 0;

        // dp[i][j][k] = max score for boxes[i...j] with k boxes
        // of color boxes[j] attached to the right of j.
        int[][][] dp = new int[n][n][n];

        // 1. Iterate over every possible subarray length
        for (int len = 1; len <= n; len++) {
            // 2. Iterate over every possible start index 'i'
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1; // End index 'j'

                // 3. Iterate over every possible 'k' (boxes on the right)
                // 'k' can't be more than the remaining space in the original array
                for (int k = 0; k <= n - 1 - j; k++) {

                    // OPTION 1: Pop the last box (j) and its k followers
                    // Score = (k+1)^2 + the score of the remaining left part
                    int res = (k + 1) * (k + 1) + (i <= j - 1 ? dp[i][j - 1][0] : 0);

                    // OPTION 2: Try to merge boxes[j] with a box boxes[m] earlier in the array
                    for (int m = i; m < j; m++) {
                        if (boxes[m] == boxes[j]) {
                            // Split the range:
                            // 1. Solve the middle gap: dp[m+1][j-1][0]
                            // 2. Solve the merged part: dp[i][m][k+1]
                            int middleGap = (m + 1 <= j - 1) ? dp[m + 1][j - 1][0] : 0;
                            res = Math.max(res, dp[i][m][k + 1] + middleGap);
                        }
                    }

                    dp[i][j][k] = res;
                }
            }
        }

        return dp[0][n - 1][0];
    }
}
