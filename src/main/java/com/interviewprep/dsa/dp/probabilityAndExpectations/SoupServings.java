package com.interviewprep.dsa.dp.probabilityAndExpectations;

//https://leetcode.com/problems/soup-servings/
public class SoupServings {
    private double[][] dp;
    public double soupServings(int n) {

        if(n >= 4800) return 1.0;

        // all the quantities are divisible by 25. divide by 25 to decrease the dp state
        int m = (n+24)/25;          //equal to Math.ceil(n/25);

        // return dfs(m, m);

        // dp = new double[m+1][m+1];
        // for(double[] arr: dp) Arrays.fill(arr, -1);
        // return soupServingsMemo(m ,m);

        return soupServingsTab(n);
    }

    //recursive
    private double dfs(int a, int b){
        //both soups are empty: consider half the probability as per requirement
        if(a<=0 && b<=0) return 0.5;
        //only a is empty
        if(a<=0) return 1.0;
        //only b soup is empty
        if(a>0 && b<=0) return 0.0;

        //a: 100, b: 0  divide by 25 a: 4, b: 0
        //a: 75, b: 25               a: 3, b: 1
        //a: 50, b: 50               a: 2, b: 2
        //a: 25, b: 75               a: 1, b: 3
        return 0.25 * (dfs(a-4, b - 0) + dfs(a-3, b-1) + dfs(a-2, b-2) + dfs(a-1, b-3));
    }

    //memoization
    private double soupServingsMemo(int a, int b){
        //both soups are empty: consider half the probability as per requirement
        if(a<=0 && b<=0) return 0.5;
        //only a is empty
        if(a<=0) return 1.0;
        //only b soup is empty
        if(a>0 && b<=0) return 0.0;

        if(dp[a][b] != -1) return dp[a][b];

        //a: 100, b: 0  divide by 25 a: 4, b: 0
        //a: 75, b: 25               a: 3, b: 1
        //a: 50, b: 50               a: 2, b: 2
        //a: 25, b: 75               a: 1, b: 3
        dp[a][b] = 0.25 * (soupServingsMemo(a-4, b - 0) + soupServingsMemo(a-3, b-1) + soupServingsMemo(a-2, b-2) + soupServingsMemo(a-1, b-3));

        return dp[a][b];
    }

    //tabulation
    private double soupServingsTab(int n){
        if(n >= 4800) return 1.0;

        // all the quantities are divisible by 25. divide by 25 to decrease the dp state
        int m = (n+24)/25;          //equal to Math.ceil(n/25);

        double[][] dp = new double[m+1][m+1];
        //both soups empty
        dp[0][0] = 0.5;
        //only a soup empty
        for(int i=1; i<=m; i++){
            dp[0][i] = 1.0;
        }

        //only b soup empty: dp[i][0]=0 which is zero by default so no change

        for(int a=1; a<=m; a++){
            for(int b=1; b<=m; b++){
                dp[a][b] = 0.25 * (getValue(dp, a-4,b) + getValue(dp, a-3, b-1) + getValue(dp, a-2, b-2) + getValue(dp, a-1,b-3));
            }
        }

        return dp[m][m];
    }

    private double getValue(double[][] dp, int a, int b){
        //both soups are empty: consider half the probability as per requirement
        if(a<=0 && b<=0) return 0.5;

        //only a is empty
        if(a<=0) return 1.0;

        //only b soup is empty
        if (b<=0) return 0.0;

        return dp[a][b];
    }
}
