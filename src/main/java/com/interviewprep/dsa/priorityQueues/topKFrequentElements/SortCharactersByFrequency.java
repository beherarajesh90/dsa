package com.interviewprep.dsa.priorityQueues.topKFrequentElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/sort-characters-by-frequency/
public class SortCharactersByFrequency {
    //heap approach
    public String frequencySort2(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char ch: s.toCharArray()){
            freqMap.merge(ch, 1,Integer::sum);
        }

        Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
        for(Map.Entry<Character, Integer> entry: freqMap.entrySet()){
            maxHeap.offer(entry);
        }

        StringBuilder result = new StringBuilder();
        while(!maxHeap.isEmpty()){
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char ch = entry.getKey();
            int freq = entry.getValue();
            for(int i=0; i< freq; i++){
                result.append(ch);
            }
        }

        return result.toString();
    }

    //optimized approach
    public String frequencySort(String s) {
        char[] freq = new char[128];
        for(char c: s.toCharArray()){
            freq[c]++;
        }

        int idx = 0;
        char[] result = new char[s.length()];
        while(idx < s.length()){
            int maxFreq = 0;
            int maxFreqChar = 0;
            for(int i=0; i<128; i++){
                if(freq[i]>maxFreq){
                    maxFreq = freq[i];
                    maxFreqChar = i;
                }
            }
            while(maxFreq-- > 0){
                result[idx++] = (char) maxFreqChar;
            }
            freq[maxFreqChar] = 0;
        }
        return new String(result);
    }
}
