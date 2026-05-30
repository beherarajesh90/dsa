package com.interviewprep.dsa.priorityQueues.topKFrequentElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/sort-array-by-increasing-frequency/
public class SortArrayByIncreasingFrequency {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num: nums){
            freq.merge(num, 1, Integer::sum);
        }

        Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> {
            if(e1.getValue().equals(e2.getValue())){
                return e2.getKey() - e1.getKey();
            }
            return e1.getValue() - e2.getValue();
        });
        for(Map.Entry<Integer, Integer> entry: freq.entrySet()){
            minHeap.offer(entry);
        }

        int[] res = new int[nums.length];
        int indx = 0;
        while(!minHeap.isEmpty()){
            Map.Entry<Integer, Integer> entry = minHeap.poll();
            int num = entry.getKey();
            int count = entry.getValue();
            for(int i=0; i<count; i++){
                res[indx++] = num;
            }
        }
        return res;
    }
}
