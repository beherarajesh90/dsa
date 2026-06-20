package com.interviewprep.dsa.dp.probabilityAndExpectations;

//https://leetcode.com/problems/probability-of-a-two-boxes-having-the-same-number-of-distinct-balls/description/
public class ProbabilityOfATwoBoxesHavingTheSameNumberOfDistinctBalls {
    private double[][][][] dp;
    private boolean[][][] seen;
    private double validWays;
    private double totalWays;
    private double[][] comb;
    private int totalBalls;

    public double getProbability(int[] balls) {

        // for(int b: balls){
        //     totalBalls += b;
        // }

        // comb = new double[7][7];
        // for(int i=0; i<=6; i++){
        //     comb[i][0] = 1;
        //     comb[i][i] = 1;
        //     for(int j=1; j<i; j++){
        //         comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
        //     }
        // }

        // //No colors processed yet.Current path represents 1 way.
        // dfs(0,0,0,0,0,1,balls);

        // return validWays / totalWays;

        // for(int b: balls){
        //     totalBalls += b;
        // }

        // comb = new double[7][7];
        // for(int i=0; i<=6; i++){
        //     comb[i][0] = 1;
        //     comb[i][i] = 1;
        //     for(int j=1; j<i; j++){
        //         comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
        //     }
        // }

        // //dp[index][box1balls][distinct diff][valid&total]
        // dp = new double[balls.length + 1][totalBalls/2 + 1][17][2];
        // seen = new boolean[balls.length + 1][totalBalls/2 + 1][17];

        // double[] res = getProbabilityMemo(0, 0, 0, balls);
        // return res[0]/res[1];

        return getProbabilityTab(balls);
    }

    //recursive
    private void dfs(int indx, int box1Balls, int box2Balls, int distinct1, int distinct2, double ways, int[] balls){

        //pruning this is an optimization
        if(box1Balls > totalBalls/2) return;
        if(box2Balls > totalBalls/2) return;

        //all colors processed
        if(indx == balls.length){
            if(box1Balls == totalBalls/2 && box2Balls == totalBalls/2){
                totalWays += ways;
                if(distinct1 == distinct2)
                    validWays += ways;
            }
            return;
        }

        int count = balls[indx];
        for(int take = 0; take <= count; take++){
            int putInBox1 = take;
            int putInBox2 = count - take;

            int newDistinct1 = distinct1 + ((putInBox1 > 0) ? 1 : 0);
            int newDistinct2 = distinct2 + ((putInBox2 > 0) ? 1 : 0);

            double newWays = ways * (comb[count][take]);

            dfs(indx+1, box1Balls + putInBox1, box2Balls + putInBox2, newDistinct1, newDistinct2, newWays, balls);
        }
    }

    //memoization
    private double[] getProbabilityMemo(int indx, int box1Balls, int diff, int[] balls){

        int diffIndx = diff + 8;
        if(seen[indx][box1Balls][diffIndx]){
            return dp[indx][box1Balls][diffIndx];
        }

        //all colors processed
        if(indx == balls.length){
            if(box1Balls != totalBalls/2){
                return new double[]{0.0, 0.0};
            }
            double valid = (diff == 0) ? 1.0 : 0.0;
            return new double[]{valid, 1.0};
        }

        double validWays = 0;
        double totalWays = 0;

        int count = balls[indx];

        for(int take = 0; take <= count; take++){

            int newBox1Balls = box1Balls + take;
            if(newBox1Balls > totalBalls/2) continue;

            int delta = (take > 0 ? 1 : 0) - ((count - take) > 0 ? 1 : 0);
            double[] child = getProbabilityMemo(indx+1, newBox1Balls, diff + delta, balls);

            double ways = comb[count][take];
            validWays += ways * child[0];
            totalWays += ways * child[1];
        }

        seen[indx][box1Balls][diffIndx] = true;

        dp[indx][box1Balls][diffIndx] = new double[]{validWays, totalWays};
        return dp[indx][box1Balls][diffIndx];
    }

    //tabulation
    private double getProbabilityTab(int[] balls){

        int totalBalls = 0;
        for (int b : balls) {
            totalBalls += b;
        }

        int target = totalBalls / 2;
        int k = balls.length;

        double[][] comb = new double[7][7];

        for (int i = 0; i <= 6; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] =
                        comb[i - 1][j - 1] +
                                comb[i - 1][j];
            }
        }

        // After processing first i colors,
        // there are dp ways to obtain:

        // box1Balls = b
        // distinctDiff = d-8

        // (+8 is the offset)
        double[][][] dp =
                new double[k + 1][target + 1][17];

        dp[0][0][8] = 1.0;

        for (int idx = 0; idx < k; idx++) {

            int count = balls[idx];

            for (int box1 = 0; box1 <= target; box1++) {

                for (int diffIdx = 0; diffIdx < 17; diffIdx++) {

                    double cur =
                            dp[idx][box1][diffIdx];

                    if (cur == 0) continue;

                    for (int take = 0;
                         take <= count;
                         take++) {

                        int newBox1 =
                                box1 + take;

                        if (newBox1 > target)
                            continue;

                        int delta =
                                (take > 0 ? 1 : 0)
                                        - ((count - take) > 0 ? 1 : 0);

                        int newDiff =
                                diffIdx + delta;

                        dp[idx + 1]
                                [newBox1]
                                [newDiff]
                                += cur * comb[count][take];
                    }
                }
            }
        }

        double totalWays = 0;

        for (int diff = 0;
             diff < 17;
             diff++) {

            totalWays +=
                    dp[k][target][diff];
        }

        double goodWays =
                dp[k][target][8];

        return goodWays / totalWays;
    }
}
