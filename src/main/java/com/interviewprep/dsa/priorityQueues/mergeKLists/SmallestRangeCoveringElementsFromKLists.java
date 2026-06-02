package com.interviewprep.dsa.priorityQueues.mergeKLists;

import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeCoveringElementsFromKLists {
    public int[] smallestRange1(List<List<Integer>> nums) {

        // val, listIndx, eleIndx
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int curMax = Integer.MIN_VALUE;

        for(int i=0; i<nums.size(); i++){
            int curVal = nums.get(i).get(0);
            minHeap.offer(new int[]{curVal, i, 0});
            curMax = Math.max(curMax, curVal);
        }

        int rangeStart = -100000, rangeEnd = 100000;
        while(true){
            int[] curMin = minHeap.poll();
            int curVal = curMin[0];
            int listIndx = curMin[1];
            int eleIndx = curMin[2];

            if(curMax - curVal < rangeEnd - rangeStart){
                rangeStart = curVal;
                rangeEnd = curMax;
            }

            if(eleIndx+1 >= nums.get(listIndx).size()) break;

            int nextElement = nums.get(listIndx).get(eleIndx+1);
            minHeap.offer(new int[]{nextElement, listIndx, eleIndx + 1});
            curMax = Math.max(curMax, nextElement);
        }

        return new int[]{rangeStart, rangeEnd};
    }
}
