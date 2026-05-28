package com.interviewprep.dsa.priorityQueues.findingKthLargestOrSmallest;

import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/find-median-from-data-stream/description/
public class FindMedianFromDataStream {
    private Queue<Integer> lower;
    private Queue<Integer> higher;

    public FindMedianFromDataStream() {
        lower = new PriorityQueue<>((a, b) -> b-a);
        higher = new PriorityQueue<>();
    }

    public void addNum(int num) {
        //lower will contain first half and higher will contain the other half
        if(lower.isEmpty() || num <= lower.peek()){
            lower.offer(num);
        } else{
            higher.offer(num);
        }

        //rebalance the heaps. any of the heaps can contain 1 more than the other heap
        if(lower.size() > higher.size() + 1){
            higher.offer(lower.poll());
        } else if(higher.size() > lower.size() + 1){
            lower.offer(higher.poll());
        }
    }

    public double findMedian() {
        if(lower.size() == higher.size()){
            return (lower.peek() + higher.peek())/2.0;
        } else if(lower.size() > higher.size()){
            return lower.peek();
        } else{
            return higher.peek();
        }
    }

    // less code but not optimal
    // public void addNum(int num) {
    //     lower.offer(num);
    //     higher.offer(lower.poll());
    //     if(higher.size() > lower.size()){
    //         lower.offer(higher.poll());
    //     }
    // }

    // public double findMedian() {
    //     if(lower.size() > higher.size()){
    //         return lower.peek();
    //     } else{
    //         return (lower.peek()+higher.peek())/2.0;
    //     }
    // }
}
