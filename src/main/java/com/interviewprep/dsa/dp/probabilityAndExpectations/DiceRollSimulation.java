package com.interviewprep.dsa.dp.probabilityAndExpectations;

//https://leetcode.com/problems/dice-roll-simulation/description/
public class DiceRollSimulation {
    private static final int MOD = 1_000_000_007;
    private Integer[][][] dp;
    public int dieSimulator(int n, int[] rollMax) {
        // return dfs(0, -1, 0, n, rollMax);

        // pos      -> n
        // lastFace -> 6 faces + dummy
        // count    -> max given 1<=rollMax <= 15 + dummy
        dp = new Integer[n][7][16];

        // No previous face has been rolled yet.If its -1 then we need to handle it using some check
        // return dieSimulatorMemo(0, 6, 0, n, rollMax);

        // return dieSimulatorTab(n, rollMax);

        return dieSimulatorTabOptimized(n, rollMax);
    }

    //recursive
    private int dfs(int pos, int lastFace, int curStreak, int n, int[] rollMax){

        if(pos == n) return 1;

        int res = 0;
        for(int face=1; face<=6; face++){
            if(lastFace != face){
                res += dfs(pos+1, face, 1, n, rollMax);
            } else{
                if(curStreak <= rollMax[lastFace-1]){
                    res += dfs(pos+1, face, curStreak+1, n, rollMax);
                }
            }
        }

        return res;
    }

    //memoization
    private int dieSimulatorMemo(int pos, int lastFace, int curStreak, int n, int[] rollMax){

        if(pos == n) return 1;

        if(dp[pos][lastFace][curStreak] != null) return dp[pos][lastFace][curStreak];

        long res = 0;
        for(int face=1; face<=6; face++){
            if(lastFace != face){
                res += dieSimulatorMemo(pos+1, face, 1, n, rollMax);
            } else{
                if(curStreak < rollMax[lastFace-1]){
                    res += dieSimulatorMemo(pos+1, face, curStreak+1, n, rollMax);
                }
            }
        }

        return dp[pos][lastFace][curStreak] = (int)(res % MOD);
    }

    //tabulation
    private int dieSimulatorTab(int n, int[] rollMax){
        long[][][] dp = new long[n+1][7][16];
        dp[0][6][0] = 1;

        for(int pos = 0; pos<n; pos++){
            for(int last=1; last<=6; last++){
                for(int streak=0; streak<=rollMax[last-1]; streak++){

                    long ways = dp[pos][last][streak];
                    if(ways == 0) continue;

                    for(int face=1; face<=6; face++){
                        if(last != face){
                            dp[pos+1][face][1] = (ways + dp[pos+1][face][1]) % MOD;
                        } else{
                            int newStreak = streak + 1;
                            if(newStreak <= rollMax[last-1]){
                                dp[pos+1][face][newStreak] = (ways + dp[pos+1][face][newStreak]) % MOD;
                            }
                        }
                    }
                }
            }
        }

        long res = 0;
        for(int face = 1; face<=6; face++){
            for(int streak=1; streak<=rollMax[face-1]; streak++){
                res = (res + dp[n][face][streak]) % MOD;
            }
        }
        return (int)res;
    }

    //tabulation space optimized
    private int dieSimulatorTabOptimized(int n, int[] rollMax){
        long[][] dp = new long[7][16];
        dp[6][0] = 1;

        for(int pos = 0; pos<n; pos++){
            long[][] next = new long[7][16];
            for(int last=1; last<=6; last++){
                for(int streak=0; streak<=rollMax[last-1]; streak++){

                    long ways = dp[last][streak];
                    if(ways == 0) continue;

                    for(int face=1; face<=6; face++){
                        if(last != face){
                            next[face][1] = (ways + next[face][1]) % MOD;
                        } else{
                            int newStreak = streak + 1;
                            if(newStreak <= rollMax[last-1]){
                                next[face][newStreak] = (ways + next[face][newStreak]) % MOD;
                            }
                        }
                    }
                }
            }
            dp = next;
        }

        long res = 0;
        for(int face = 1; face<=6; face++){
            for(int streak=1; streak<=rollMax[face-1]; streak++){
                res = (res + dp[face][streak]) % MOD;
            }
        }
        return (int)res;
    }
}
