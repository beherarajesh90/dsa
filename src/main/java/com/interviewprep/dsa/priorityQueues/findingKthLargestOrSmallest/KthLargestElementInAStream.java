package com.interviewprep.dsa.priorityQueues.findingKthLargestOrSmallest;

import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/kth-largest-element-in-a-stream/description/
public class KthLargestElementInAStream {
    private Queue<Integer> minHeap;
    private int k;

    public KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();
        for(int num: nums){
            minHeap.offer(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
    }

    public int add(int val) {
        minHeap.add(val);
        if(minHeap.size() > k) minHeap.poll();
        return minHeap.peek();
    }

    // public KthLargest(int k, int[] nums) {
    //     this.k = k;
    //     minHeap = new PriorityQueue<>();
    //     for(int num: nums){
    //         if(minHeap.size() < k) minHeap.offer(num);
    //         else{
    //             if(num>minHeap.peek()){
    //                 minHeap.poll();
    //                 minHeap.offer(num);
    //             }
    //         }
    //     }
    // }

    // public int add(int val) {
    //     if(minHeap.size() < k) minHeap.offer(val);
    //         else{
    //             if(val>minHeap.peek()){
    //                 minHeap.poll();
    //                 minHeap.offer(val);
    //             }
    //         }
    //     return minHeap.peek();
    // }
}
