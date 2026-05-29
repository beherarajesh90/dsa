package com.interviewprep.dsa.priorityQueues.topKFrequentElements;

import java.util.*;

//https://leetcode.com/problems/top-k-frequent-elements/
public class TopKFrequentElements {
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for(int num: nums){
            count.put(num, count.getOrDefault(num, 0)+1);
        }

        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        for (int num: count.keySet()){
            int freq = count.get(num);
            if(minHeap.size() < k) minHeap.add(new int[]{num, freq});
            else if(freq >= minHeap.peek()[1]) {
                minHeap.poll();
                minHeap.add(new int[]{num, freq});
            }
        }

        int[] res = new int[k];
        for(int i=0; i<k; i++){
            res[i] = minHeap.poll()[0];
        }
        return res;
    }

    public int[] topKFrequent(int[] nums, int k) {
        //freq
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }

        // sort based on freq
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (Map.Entry<Integer, Integer> entry: count.entrySet()){
            int num = entry.getKey();
            int freq = entry.getValue();
            minHeap.offer(new int[]{num, freq});
            if(minHeap.size() > k) minHeap.poll();
        }

        //res
        int[] res = new int[k];
        for(int i=0; i<k; i++){
            res[i] = minHeap.poll()[0];
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequentElements obj = new TopKFrequentElements();
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        int k = 2;
        int[] res = obj.topKFrequent(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
