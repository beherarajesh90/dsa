package com.interviewprep.dsa.strings.hashmaps;

//https://leetcode.com/problems/ransom-note/
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] freq = new int[26];
        for(char ch: magazine.toCharArray()){
            freq[ch - 'a']++;
        }

        for(char ch: ransomNote.toCharArray()){
            if(freq[ch - 'a']>0) freq[ch-'a']--;
            else return false;
        }

        return true;
    }
}
