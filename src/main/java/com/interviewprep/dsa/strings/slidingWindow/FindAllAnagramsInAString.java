package com.interviewprep.dsa.strings.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        int pLen = p.length();
        int sLen = s.length();
        for (char c: p.toCharArray()){
            pCount[c - 'a']++;
        }
        int left=0;
        List<Integer> result = new ArrayList<>();
        for (int right=0; right < sLen; right++){
            sCount[s.charAt(right)-'a']++;
            if (right - left + 1 > pLen){
                sCount[s.charAt(left)-'a']--;
                left++;
            }
            if (Arrays.equals(sCount, pCount)) result.add(left);
        }
        return result;
    }
}
