package com.interviewprep.dsa.strings.slidingWindow;

import java.util.Arrays;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        if(s1.isEmpty()) return true;
        if(s1Len > s2Len) return false;

        int[] s1Freq = new int[26];
        int[] s2Freq = new int[26];

        for(char c: s1.toCharArray()){
            s1Freq[c - 'a']++;
        }

        for(int i=0; i<s1.length();i++){
            s2Freq[s2.charAt(i) - 'a']++;
        }
        if(Arrays.equals(s1Freq, s2Freq)) return true;

        for(int i=s1.length(); i<s2.length(); i++){
            s2Freq[s2.charAt(i) - 'a']++;
            s2Freq[s2.charAt(i - s1.length()) - 'a']--;
            if(Arrays.equals(s1Freq, s2Freq)) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        PermutationInString permutationInString = new PermutationInString();
        System.out.println(permutationInString.checkInclusion("adc", "dcda"));
    }
}
