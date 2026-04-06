package com.interviewprep.dsa.binarySearch.allocationProblems;

import java.util.Arrays;

//https://www.propeers.in/roadmaps/69692150442404dc257236a1/aggressive-cows?todoItemId=69b4767d8789fde24cfdc7f0
public class AggressiveCows {
    public int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        int left = 0, right = stalls[stalls.length - 1] - stalls[0], result = 0;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(canPlace(stalls, k, mid)){
                result = mid;
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return result;
    }

    private boolean canPlace(int[] stalls, int k, int dist) {
        int cows = 1, lastStall = stalls[0];
        for(int i=1; i < stalls.length; i++){
            if(stalls[i] - lastStall >= dist){
                cows++;
                lastStall = stalls[i];
                if(cows >= k) return true;
            }
        }
        return false;
    }
}
