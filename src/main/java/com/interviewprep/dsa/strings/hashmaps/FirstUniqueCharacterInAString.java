package com.interviewprep.dsa.strings.hashmaps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/first-unique-character-in-a-string/description/
public class FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch: s.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        for(int i=0; i<s.length(); i++){
            if(map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }

    public int firstUniqCharOptimized(String s) {
        int[] count = new int[26];
        for (char c: s.toCharArray()){
            count[c - 'a']++;
        }
        for (int i=0; i<s.length(); i++){
            if (count[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }

    public static int firstUniqCharUsingTwoArrays(String s) {
        int[] index = new int[26];
        Arrays.fill(index, -1);

        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            freq[ch - 'a']++;

            if (index[ch - 'a'] < 0) {
                index[ch - 'a'] = i;
            }
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < 26; i++) {
            if (freq[i] == 1) {
                result = Math.min(result, index[i]);
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;

    }
}
