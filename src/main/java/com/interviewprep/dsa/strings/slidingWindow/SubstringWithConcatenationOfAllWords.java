package com.interviewprep.dsa.strings.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/substring-with-concatenation-of-all-words/
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if(words.length == 0) return result;
        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;
        Map<String, Integer> wordMap = new HashMap<>();

        for(String word: words){
            wordMap.put(word, wordMap.getOrDefault(word, 0)+1);
        }

        for(int i=0; i<wordLen; i++){
            int left = i, count = 0;
            Map<String, Integer> windowMap = new HashMap<>();
            for(int right=i; right+wordLen <= s.length(); right+=wordLen){
                String word = s.substring(right, right + wordLen);
                if(wordMap.containsKey(word)){
                    windowMap.put(word, windowMap.getOrDefault(word, 0)+1);
                    count++;
                    while(windowMap.get(word) > wordMap.get(word)){
                        String leftWord = s.substring(left, left+wordLen);
                        windowMap.put(leftWord, windowMap.get(leftWord)-1);
                        left+=wordLen;
                        count--;
                    }
                    if(count == numWords) result.add(left);
                } else{
                    left = right + wordLen;
                    windowMap.clear();
                    count = 0;
                }
            }
        }

        return result;
    }
}
