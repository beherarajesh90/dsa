package com.interviewprep.dsa.priorityQueues.findingKthLargestOrSmallest;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
public class KthSmallestElementInASortedMatrix {

    //interview approach
    public int kthSmallest(int[][] matrix, int k) {
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
        int n = matrix.length;

        //add first elements of each row into the heap
        for(int row=0; row<n; row++){
            minHeap.add(new int[]{matrix[row][0], row, 0});
        }

        for(int i=1; i<=k-1; i++){
            int[] top = minHeap.poll();
            int num = top[0], row = top[1], col=top[2];
            col++;
            if(col < n) minHeap.add(new int[]{matrix[row][col], row, col});
        }

        return minHeap.peek()[0];
    }

    //optimized approach
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length, res = -1;
        int low = matrix[0][0];
        int high = matrix[n-1][n-1];
        while (low <= high){
            int guess = (low+high)/2;
            if(noOfElements(matrix, k, guess, n) >= k){
                res = guess;
                high = guess-1;
            } else{
                low = guess + 1;
            }
        }
        return res;
    }

    private int noOfElements(int[][] matrix, int k, int guess, int n) {
        int count = 0;
        int row = n-1;
        int col = 0;
        while (row >= 0 && col<=n-1){
            if(matrix[row][col] <= guess){
                //comparing the elements vertically i.e column wise
                count += row + 1;
            } else{
                row--;
            }
        }
        return count;
    }
}
