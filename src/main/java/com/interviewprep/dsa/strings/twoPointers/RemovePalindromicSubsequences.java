package com.interviewprep.dsa.strings.twoPointers;

public class RemovePalindromicSubsequences {
    public int removePalindromeSub(String s) {
        if(s.isEmpty()) return 0;
        StringBuilder sb = new StringBuilder(s);
        if(s.equals(sb.reverse().toString())) return 1;
        return 2;
    }
}
