package com.interviewprep.dsa.dp.counting;

//https://leetcode.com/problems/decode-ways-ii/description/
public class DecodeWaysII {
    private static final int mod = 1_000_000_007;

    private long[] dp;
    public int numDecodings(String s) {
        // return (int)dfs(0,s);

        // dp = new long[s.length()];
        // Arrays.fill(dp, -1);
        // return (int)numDecodingsMemo(0, s);

        // return (int)numDecodingsTab(s);

        return (int)numDecodingsTabOptimized(s);
    }

    //recursive
    private long dfs(int i, String s){
        if(i == s.length()) return 1;

        if(s.charAt(i) == '0') return 0;

        long ways = 0;
        //one digit
        if(s.charAt(i) == '*'){
            ways += 9*dfs(i+1, s);
        } else{
            ways += dfs(i+1, s);
        }

        //two digits
        if(i+1 < s.length()){
            ways += countTwoDigit(s.charAt(i), s.charAt(i+1)) * dfs(i+2, s);
        }

        return ways%mod;
    }

    private int countTwoDigit(char a, char b){
        if(a == '*' && b == '*'){
            return 15;  //11 - 26
        }

        if(a == '*'){
            if(b <= '6'){
                return 2;   //1b or 2b
            }
            return 1;   //[17,18,19]
        }

        if(b == '*'){
            if(a == '1'){
                return 9;
            }

            if(a == '2'){
                return 6;
            }

            return 0;
        }

        //if no asterics involved
        int num = (a - '0')*10 + (b-'0');
        return num>=10 && num<=26 ? 1 : 0;
    }

    //memoization
    private long numDecodingsMemo(int i, String s){
        if(i == s.length()) return 1;

        if(s.charAt(i) == '0') return 0;

        if(dp[i] != -1) return dp[i];

        long ways = 0;
        //one digit
        if(s.charAt(i) == '*'){
            ways = (ways + 9L * numDecodingsMemo(i+1, s)) % mod;
        } else{
            ways = (ways + numDecodingsMemo(i+1, s)) % mod;
        }

        //two digits
        if(i+1 < s.length()){
            ways = (ways + countTwoDigit(s.charAt(i), s.charAt(i+1)) * numDecodingsMemo(i+2, s)) % mod;
        }

        return dp[i] = ways;
    }

    //tabulation
    private long numDecodingsTab(String s){
        int n = s.length();
        long[] dp = new long[n+1];
        dp[n] = 1L;

        for(int i=n-1; i>=0; i--){

            if(s.charAt(i) == '0')
                continue;

            long ways = 0;
            //one digit
            if(s.charAt(i) == '*'){
                ways = (ways + 9L * dp[i+1]) % mod;
            } else{
                ways = (ways + dp[i+1]) % mod;
            }

            //two digits
            if(i+1 < n){
                ways = (ways + countTwoDigit(s.charAt(i), s.charAt(i+1)) * dp[i+2]) % mod;
            }

            dp[i] = ways;
        }

        return dp[0];
    }

    //tabulation space optimized
    private long numDecodingsTabOptimized(String s){
        int n = s.length();
        long next1 = 1L;
        long next2 = 0L;
        for(int i=n-1; i>=0; i--){

            long ways = 0;

            if(s.charAt(i) != '0'){
                //one digit
                if(s.charAt(i) == '*'){
                    ways = (ways + 9L * next1) % mod;
                } else{
                    ways = (ways + next1) % mod;
                }

                //two digits
                if(i+1 < n){
                    ways = (ways + countTwoDigit(s.charAt(i), s.charAt(i+1)) * next2) % mod;
                }
            }

            next2 = next1;
            next1 = ways;
        }

        return next1;
    }
}
