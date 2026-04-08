package com.interviewprep.dsa.strings.hashmaps;

//https://leetcode.com/problems/ransom-note/
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

    public boolean canConstructUsingOneLoop(String ransomNote, String magazine) {
        if(magazine.length() < ransomNote.length()) return false;
        int[] alphabet = new int[26];

        for (char c : ransomNote.toCharArray()) {
            int i = magazine.indexOf(c, alphabet[c % 26]);
            if (i == -1) return false;
            alphabet[c % 26] = i + 1;
        }
        return true;
    }
}
