package com.interviewprep.dsa.recursion.backtracking;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/description/
public class SplitAStringIntoTheMaxNumberOfUniqueSubstrings {
    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        return backtrack(set, s, 0);
    }

    private int backtrack(Set<String> set, String s, int start){
        if(start == s.length()) return 0;

        int maxSubStr = 0;
        for(int end = start+1; end<=s.length(); end++){
            if(set.size() + (s.length() - 1) <= maxSubStr) return maxSubStr;
            String subStr = s.substring(start, end);
            if(!set.contains(subStr)){
                set.add(subStr);
                int count = 1 + backtrack(set, s, end);
                maxSubStr = Math.max(maxSubStr, count);
                set.remove(subStr);
            }
        }

        return maxSubStr;
    }

    public static void main(String[] args) {
        SplitAStringIntoTheMaxNumberOfUniqueSubstrings solution = new SplitAStringIntoTheMaxNumberOfUniqueSubstrings();
        System.out.println(solution.maxUniqueSplit("abab"));
    }
}
