package com.interviewprep.dsa.strings.hashmaps;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagramsOptimized(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key,k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            String key = getFrequencyKey(s);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private String getFrequencyKey(String s) {
        int[] freq = new int[26];
        for(char ch: s.toCharArray()){
            freq[ch - 'a']++;
        }
        StringBuilder key = new StringBuilder();
        for (int i=0; i<26; i++){
            if (freq[i]>0) key.append((char)'a'+i).append(freq[i]);
        }
        return key.toString();
    }
}
