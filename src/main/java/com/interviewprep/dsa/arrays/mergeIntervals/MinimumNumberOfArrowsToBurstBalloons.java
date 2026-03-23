package com.interviewprep.dsa.arrays.mergeIntervals;

import java.util.Arrays;

public class MinimumNumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        if(points.length==0) return 0;
        //array contains negative values. hence using (a,b)->Integer.compare else (a,b)->a[1]-b[1]
        Arrays.sort(points, (a, b)->Integer.compare(a[1],b[1]));
        int arrows = 1;
        int end = points[0][1];
        for(int i=1; i<points.length; i++){
            if(points[i][0]>end){
                arrows++;
                end=points[i][1];
            }
        }
        return arrows;
    }
}
