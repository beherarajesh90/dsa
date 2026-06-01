package com.interviewprep.dsa.priorityQueues.mergeKLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();
        if(nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // num1,num2, num1-indx
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0]+a[1]) - (b[0]+b[1]));
        for(int i=0; i<Math.max(k, nums2.length); i++){
            minHeap.offer(new int[]{nums1[0], nums2[i], 0});
        }

        while(!minHeap.isEmpty() && result.size() < k){
            int[] cur = minHeap.poll();
            result.add(Arrays.asList(cur[0], cur[1]));
            int indx = cur[2];
            if(indx+1 < nums1.length){
                minHeap.offer(new int[]{nums1[indx+1], cur[1], indx + 1});
            }
        }

        return result;
    }
}
