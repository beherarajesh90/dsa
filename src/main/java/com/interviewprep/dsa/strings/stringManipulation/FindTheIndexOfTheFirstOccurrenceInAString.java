package com.interviewprep.dsa.strings.stringManipulation;

//https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
public class FindTheIndexOfTheFirstOccurrenceInAString {
    public int strStr(String haystack, String needle) {
        if(needle.length() > haystack.length() || needle.isEmpty()) return -1;
        for(int i=0; i<=haystack.length()-needle.length(); i++){
            String curStr = haystack.substring(i, i+needle.length());
            if(needle.equals(curStr)) return i;
        }
        return -1;
    }
}
