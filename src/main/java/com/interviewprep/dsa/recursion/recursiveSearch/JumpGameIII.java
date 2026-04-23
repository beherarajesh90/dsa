package com.interviewprep.dsa.recursion.recursiveSearch;

//https://leetcode.com/problems/jump-game-iii/description/
public class JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return backtrack(arr, visited, start);
    }

    private boolean backtrack(int[] arr, boolean[] visited, int start) {
        if( start<0 || start>=arr.length || visited[start]) return false;
        if (arr[start] == 0) return true;
        visited[start] = true;
        boolean canReachZero = backtrack(arr, visited, start + arr[start]) || backtrack(arr, visited, start - arr[start]);
        visited[start] = false;
        return canReachZero;
    }
}
