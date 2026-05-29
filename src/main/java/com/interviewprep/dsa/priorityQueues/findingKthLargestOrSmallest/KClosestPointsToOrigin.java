package com.interviewprep.dsa.priorityQueues.findingKthLargestOrSmallest;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/k-closest-points-to-origin/
public class KClosestPointsToOrigin {

    //little optimized
    public int[][] kClosest(int[][] points, int k) {
        Queue<int[]> maxHeap = new PriorityQueue<>((a,b)-> Integer.compare(b[0],a[0]));

        for(int i=0; i<points.length; i++){
            int x = points[i][0];
            int y = points[i][1];
            int dist = x * x + y * y;
            if(maxHeap.size() < k) maxHeap.offer(new int[]{dist, i});
            else if(dist < maxHeap.peek()[0]){
                maxHeap.poll();
                maxHeap.offer(new int[]{dist, i});
            }
        }

        int[][] res = new int[k][2];
        for(int i=0; i<k; i++){
            res[i] = points[maxHeap.poll()[1]];
        }
        return res;
    }

    //simple approach
    public int[][] kClosest2(int[][] points, int k) {
        Queue<int[]> minHeap = new PriorityQueue<>((a,b)->{
            int distA = a[0]*a[0]+a[1]*a[1];
            int distB = b[0]*b[0]+b[1]*b[1];
            return Integer.compare(distB, distA);
        });
        for(int[] point: points){
            minHeap.offer(point);
            if(minHeap.size() > k) minHeap.poll();
        }

        int[][] res = new int[k][2];
        for(int i=0; i<k; i++){
            res[i] = minHeap.poll();
        }
        return res;
    }

}
