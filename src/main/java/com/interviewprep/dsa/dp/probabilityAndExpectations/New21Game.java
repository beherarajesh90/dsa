package com.interviewprep.dsa.dp.probabilityAndExpectations;

//https://leetcode.com/problems/new-21-game/description/
public class New21Game {
    private double[] dp;
    public double new21Game(int n, int k, int maxPts) {
        // return dfs(n, k, 0, maxPts);

        // dp = new double[n+1];
        // Arrays.fill(dp, -1);
        // return new21GameMemo(n, k, 0, maxPts);

        // return new21GameTab(n, k, maxPts);

        return new21GameSlidingWindow(n, k, maxPts);
    }

    //recursive
    private double dfs(int n, int k, int points, int maxPts){

        if(points >= k) return points <= n ? 1.0 : 0.0;

        double probability = 0.0;
        for(int p=1; p<=maxPts; p++){
            probability += (1.0/maxPts * dfs(n, k, points + p, maxPts));
        }

        return probability;
    }

    //memoization
    private double new21GameMemo(int n, int k, int points, int maxPts){
        if(points >= k) return points <= n ? 1.0 : 0.0;

        if(dp[points] != -1) return dp[points];

        double probability = 0.0;
        for(int p=1; p<=maxPts; p++){
            probability += (1.0/maxPts * new21GameMemo(n, k, points + p, maxPts));
        }

        return dp[points] = probability;
    }

    //tabulation
    private double new21GameTab(int n, int k, int maxPoints){
        int size = k + maxPoints;
        // Probability of eventually ending with a score ≤ n when current score is i.
        double[] dp = new double[size];
        // base cases
        for(int i=k; i<size && i<=n; i++){
            dp[i] = 1.0;
        }

        for(int i=k-1; i>=0; i--){
            double sum = 0.0;
            for(int p=1; p<=maxPoints; p++){
                sum += dp[i + p];
            }
            dp[i] = sum/maxPoints;
        }

        return dp[0];
    }

    //sliding window
    private double new21GameSlidingWindow(int n, int k, int maxPts) {

        if(k == 0 || n >= k - 1 + maxPts) return 1.0;

        double[] dp = new double[n+1];
        dp[0]=1.0;

        double windowSum = 1.0;
        for(int i=1; i<=n; i++){

            //calculate current score probability
            dp[i] = windowSum / maxPts;

            //expand window
            if(i < k){
                windowSum += dp[i];
            }

            //shrink window
            if(i >= maxPts){
                windowSum -= dp[i - maxPts];
            }
        }

        double result = 0.0;
        for(int i=k; i<=n; i++){
            result += dp[i];
        }
        return result;
    }
}
