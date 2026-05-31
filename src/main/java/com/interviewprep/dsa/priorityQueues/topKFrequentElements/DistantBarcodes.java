package com.interviewprep.dsa.priorityQueues.topKFrequentElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/distant-barcodes/
public class DistantBarcodes {
    //optimized approach
    public int[] rearrangeBarcodes(int[] barcodes) {

        //populate freq array
        //find out the max count num
        int[] freq = new int[10001];
        int maxCount = 0;
        int maxNum = 0;
        for(int barcode: barcodes){
            freq[barcode]++;
            if(maxCount < freq[barcode]){
                maxCount = freq[barcode];
                maxNum = barcode;
            }
        }

        int indx = 0;
        int n = barcodes.length;
        int[] res = new int[n];
        while(freq[maxNum]-- > 0){
            res[indx] = maxNum;
            indx+=2;
        }

        for(int i=0; i<freq.length; i++){
            while(freq[i]-- > 0){
                if(indx >= n) indx = 1;
                res[indx] = i;
                indx+=2;
            }
        }

        return res;

    }

    //heap approach
    public int[] rearrangeBarcodes2(int[] barcodes) {

        Map<Integer, Integer> freq = new HashMap<>();
        for(int barcode: barcodes){
            freq.merge(barcode, 1, Integer::sum);
        }

        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for(Map.Entry<Integer, Integer> entry: freq.entrySet()){
            maxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        int[] res = new int[barcodes.length];
        int indx = 0;
        int[] prev = new int[]{0, 0};
        while(!maxHeap.isEmpty()){
            int[] cur = maxHeap.poll();
            res[indx++] = cur[0];
            if(prev[1] > 0) maxHeap.offer(prev);
            cur[1]--;
            prev = cur;
        }

        return res;

    }
}
