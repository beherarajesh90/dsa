package com.interviewprep.dsa.binarySearch.allocationProblems;

import java.util.Arrays;

//https://leetcode.com/problems/magnetic-force-between-two-balls/
public class MagneticForceBetweenTwoBalls {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int left = 0;
        int right = (position[n-1]-position[0])/(m-1); //maximum possible force
        int result = 0;
        while(left<=right){
            int mid = left + (right - left) / 2;
            if(canDistribute(position, m, mid)){
                result = mid;
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return result;
    }

    private boolean canDistribute(int[] position, int m, int force){
        int count = 1, lastPosition = position[0];
        for(int i=1; i<position.length; i++){
            if(position[i] - lastPosition >= force){
                count++;
                lastPosition = position[i];
                if(count == m) return true;
            }
        }
        return false;
    }
}
