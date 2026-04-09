package com.interviewprep.dsa.strings.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxSubStr = 0;
        int left=0;
        for(int right = 0; right<s.length(); right++){
            char c = s.charAt(right);
            if (map.containsKey(c) && map.get(c)>=left){
                left = map.get(c)+1;
            }
            map.put(c, right);
            maxSubStr = Math.max(maxSubStr, right-left+1);
        }
        return maxSubStr;
    }

    public int lengthOfLongestSubstring(String s) {
        int[] prevInd = new int[128];
        int left=0, maxSubStr = 0;
        for(int right=0; right<s.length(); right++){
            char c = s.charAt(right);
            left = Math.max(left, prevInd[c]);
            maxSubStr = Math.max(maxSubStr, right - left + 1);
            prevInd[c] = right + 1;
        }
        return maxSubStr;
    }
}
