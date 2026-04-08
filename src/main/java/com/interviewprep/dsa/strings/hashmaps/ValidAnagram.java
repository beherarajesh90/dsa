package com.interviewprep.dsa.strings.hashmaps;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        int[] freq = new int[26];
        //count the characters of string s
        for(char ch: s.toCharArray()){
            freq[ch-'a']++;
        }

        //decrement the count
        for(char ch: t.toCharArray()){
            freq[ch-'a']--;
        }

        // check if all the count zero for anagram
        for(int i=0; i<26; i++){
            if(freq[i] != 0) return false;
        }
        return true;
    }
}
