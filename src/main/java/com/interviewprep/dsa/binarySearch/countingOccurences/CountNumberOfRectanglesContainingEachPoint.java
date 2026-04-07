package com.interviewprep.dsa.binarySearch.countingOccurences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/count-number-of-rectangles-containing-each-point/submissions/1971292405/
public class CountNumberOfRectanglesContainingEachPoint {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        List<Integer>[] buckets = new ArrayList[101];
        for(int i=0; i<buckets.length; i++){
            buckets[i] = new ArrayList<>();
        }

        // fill the bucket
        for(int[] rect: rectangles){
            int l = rect[0], h = rect[1];
            buckets[h].add(l);
        }

        //sort each bucket
        for(int i=1; i<=100; i++){
            Collections.sort(buckets[i]);
        }

        int[] result = new int[points.length];
        //process points
        for(int i=0; i < points.length; i++){
            int x = points[i][0];
            int y = points[i][1];
            int count = 0;
            //looping through the heights greater than or equal to y since we need to find the number of rectangles containing the point
            for(int h=y; h<=100; h++){
                List<Integer> list = buckets[h];
                if(list.isEmpty()) continue;
                //find lower bound of the length
                int indx = lowerBound(list, x);
                count+=(list.size() - indx);
            }
            result[i] = count;
        }

        return result;
    }

    private int lowerBound(List<Integer> lengths, int x){
        int left = 0, right = lengths.size();
        while(left < right){
            int mid = left + (right-left)/2;
            if(lengths.get(mid) >= x){
                right = mid;
            } else{
                left = mid + 1;
            }
        }
        return left;
    }
}
