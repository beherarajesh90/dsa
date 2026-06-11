package com.interviewprep.dsa.dp.counting;

//https://leetcode.com/problems/decode-ways/description/
public class DecodeWays {
    private int[] dp;
    public int numDecodings(String s) {
        // return dfs(0, s);

        // dp = new int[s.length()];
        // Arrays.fill(dp, -1);
        // return numDecodingsMemo(0, s);

        // return numDecodingsTab(s);

        return numDecodingsTabOptimized(s);
    }

    //recursive
    private int dfs(int index, String s){
        if(index == s.length()) return 1;

        //if the digit starts with zero, its invalid
        if(s.charAt(index) == '0') return 0;

        //one digit
        int ways = dfs(index+1, s);

        //two digits
        if(index+1 < s.length()){
            int num = (s.charAt(index) - '0')*10 + (s.charAt(index+1) - '0');
            if(num >= 10 && num <= 26){
                ways += dfs(index+2, s);
            }
        }

        return ways;
    }

    //memoization
    private int numDecodingsMemo(int index, String s){
        if(index == s.length()) return 1;

        //if the digit starts with zero, its invalid
        if(s.charAt(index) == '0') return 0;

        if(dp[index]!=-1) return dp[index];

        //one digit
        dp[index] = numDecodingsMemo(index+1, s);

        //two digits
        if(index+1 < s.length()){
            int num = (s.charAt(index) - '0')*10 + (s.charAt(index+1) - '0');
            if(num >= 10 && num <= 26){
                dp[index] += numDecodingsMemo(index+2, s);
            }
        }

        return dp[index];
    }

    //tabulation
    private int numDecodingsTab(String s){
        int n = s.length();

        // define dp[i] as the number of ways to decode the first i characters.
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;

        for(int i=2; i<=n; i++){
            //one digit
            if(s.charAt(i-1)!='0') dp[i] += dp[i-1];

            //two digit
            int num = (s.charAt(i-2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if(num>=10 && num<=26){
                dp[i] += dp[i-2];
            }
        }

        return dp[n];
    }

    //tabulation space optimized
    private int numDecodingsTabOptimized(String s){
        int n = s.length();

        int prev2 = 1;
        int prev1 = s.charAt(0) != '0' ? 1 : 0;

        for(int i=2; i<=n; i++){
            int ways = 0;
            //one digit
            if(s.charAt(i-1)!='0') ways += prev1;

            //two digit
            int num = (s.charAt(i-2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if(num>=10 && num<=26){
                ways += prev2;
            }

            int temp = prev1;
            prev1 = ways;
            prev2 = temp;
        }

        return prev1;
    }
}
