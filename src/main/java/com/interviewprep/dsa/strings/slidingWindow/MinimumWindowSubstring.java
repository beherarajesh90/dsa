package com.interviewprep.dsa.strings.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if( sLen==0 || tLen == 0) return "";
        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> windowMap = new HashMap<>();

        for(char c: t.toCharArray()){
            tMap.put(c, tMap.getOrDefault(c, 0)+1);
        }

        int left = 0, right = 0, required = tMap.size(), formed = 0, minLen = Integer.MAX_VALUE, minLeftIndex=0;
        while(right < sLen){
            char ch = s.charAt(right);
            windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);

            if(tMap.containsKey(ch) && tMap.get(ch).intValue() == windowMap.get(ch).intValue()){
                formed++;
            }

            while(left<=right && formed == required){
                if(right - left + 1 < minLen){
                    minLen = right - left + 1;
                    minLeftIndex = left;
                }
                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar)-1);
                if(tMap.containsKey(leftChar) && windowMap.get(leftChar) < tMap.get(leftChar)) formed--;
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeftIndex, minLeftIndex + minLen);
    }

    public String minWindowOptimizedUsingArray(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        int[] tMap = new int[128];
        int[] windowMap = new int[128];

        // Build tMap
        for (char c : t.toCharArray()) {
            tMap[c]++;
        }

        int left = 0, right = 0;
        int required = 0;

        // Count unique chars in t
        for (int i = 0; i < 128; i++) {
            if (tMap[i] > 0) required++;
        }

        int formed = 0;
        int minLen = Integer.MAX_VALUE, minLeft = 0;

        while (right < s.length()) {
            char ch = s.charAt(right);
            windowMap[ch]++;

            // Check if requirement satisfied
            if (tMap[ch] > 0 && windowMap[ch] == tMap[ch]) {
                formed++;
            }

            // Try shrinking
            while (left <= right && formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);
                windowMap[leftChar]--;

                if (tMap[leftChar] > 0 && windowMap[leftChar] < tMap[leftChar]) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindowOptimizedUsingArray("ADOBECODEBANC", "ABC"));
    }
}
