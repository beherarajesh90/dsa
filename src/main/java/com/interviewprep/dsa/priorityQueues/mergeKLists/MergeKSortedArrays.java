package com.interviewprep.dsa.priorityQueues.mergeKLists;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

//https://www.geeksforgeeks.org/problems/merge-k-sorted-arrays/1
public class MergeKSortedArrays {
    public ArrayList<Integer> mergeArrays(int[][] mat) {
        // int[]{ value, row, index }
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int i=0; i<mat.length; i++){
            minHeap.offer(new int[]{mat[i][0], i, 0});
        }

        ArrayList<Integer> result = new ArrayList<>();
        while(!minHeap.isEmpty()){
            int[] num = minHeap.poll();
            int val = num[0];
            int row = num[1];
            int indx = num[2];
            result.add(val);
            indx++;
            if( indx <  mat[row].length){
                minHeap.offer(new int[]{mat[row][indx], row, indx});
            }
        }

        return result;

    }
}
