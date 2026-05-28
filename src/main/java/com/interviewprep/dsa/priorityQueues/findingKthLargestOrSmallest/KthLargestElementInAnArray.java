package com.interviewprep.dsa.priorityQueues.findingKthLargestOrSmallest;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/kth-largest-element-in-an-array/
public class KthLargestElementInAnArray {
    //initial intuition
    public int findKthLargest1(int[] nums, int k) {
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<nums.length; i++){
            maxHeap.add(nums[i]);
        }

        for(int i=1; i<k; i++){
            maxHeap.poll();
        }

        return maxHeap.peek();
    }

    //better approach (use this in interviews)
    public int findKthLargest2(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>();

        for(int num:nums){
            if(minHeap.size() < k){
                minHeap.add(num);
            } else{
                if(num>minHeap.peek()){
                    minHeap.poll();
                    minHeap.add(num);
                }
            }
        }

        return minHeap.peek();
    }

    //optimized approach
    public int findKthLargest(int[] nums, int k) {
        //since 10^4 <= nums[i] <= 10^4
        int[] count = new int[20001];

        for(int num:nums)
            count[num + 10000]++;

        for(int i=count.length-1; i>=0; i--){
            if(count[i] > 0){
                //considers duplicate numbers
                k-=count[i];
                if(k <= 0) return i - 10000;
            }
        }
        return -1;
    }

    //another optimized approach using quick select
}
