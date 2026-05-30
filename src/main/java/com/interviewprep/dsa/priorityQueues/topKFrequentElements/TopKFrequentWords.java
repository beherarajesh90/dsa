package com.interviewprep.dsa.priorityQueues.topKFrequentElements;

import java.util.*;

//https://leetcode.com/problems/top-k-frequent-words/
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {

        Map<String,Integer> freq = new HashMap<>();
        for(String word: words){
            freq.merge(word,1, Integer::sum);
        }

        Queue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>((e1, e2)->{
            if(e1.getValue().equals(e2.getValue())) return e1.getKey().compareTo(e2.getKey());
            return e2.getValue() - e1.getValue();
        });

        for(Map.Entry<String, Integer> entry: freq.entrySet()){
            maxHeap.offer(entry);
        }

        List<String> result = new ArrayList<>();
        for(int i=0; i<k; i++){
            result.add(maxHeap.poll().getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        TopKFrequentWords obj = new TopKFrequentWords();
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        int k = 4;
        List<String> res = obj.topKFrequent(words, k);
        System.out.println(res);
    }
}
