package com.interviewprep.dsa.arrays.slidingWindow;

public class MaximumPointsYouCanObtainFromCards {
    public int maxScore(int[] cardPoints, int k) {
        //find total points
        int totalPoints = 0, n=cardPoints.length, curPoints=0;
        for(int i=0; i<n; i++){
            totalPoints+=cardPoints[i];
        }

        //return if length of k is same as arr length
        if(k == cardPoints.length) return totalPoints;

        //add initial points
        int window = n-k;
        for(int i=0;i<window; i++){
            curPoints+=cardPoints[i];
        }
        int minPoints=curPoints;
        // find minimum sum of contiguous subarray of length n-k
        for(int i=window; i<n; i++){
            curPoints+=cardPoints[i]-cardPoints[i-window];
            minPoints = Math.min(minPoints, curPoints);
        }

        return totalPoints-minPoints;

    }
}
