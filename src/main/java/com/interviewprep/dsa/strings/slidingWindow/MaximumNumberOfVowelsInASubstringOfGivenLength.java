package com.interviewprep.dsa.strings.slidingWindow;

//https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/
public class MaximumNumberOfVowelsInASubstringOfGivenLength {
    public int maxVowelsOptimized(String s, int k) {
        if(s.length()<k) return 0;
        int[] alpha = new int[26];
        alpha['a'-'a']++;
        alpha['e'-'a']++;
        alpha['i'-'a']++;
        alpha['o'-'a']++;
        alpha['u'-'a']++;
        int vowels=0;
        for(int i=0; i<k; i++){
            vowels+=alpha[s.charAt(i)-'a'];
        }
        int maxCount = vowels;
        for(int i=1; i<=s.length()-k; i++){
            vowels-=alpha[s.charAt(i-1)-'a'];
            vowels+=alpha[s.charAt(i+k-1)-'a'];
            maxCount = Math.max(maxCount, vowels);
        }
        return maxCount;
    }

    public int maxVowels(String s, int k) {
        if(s.length()<k) return 0;
        int vowels=0;
        for(int i=0; i<k; i++){
            if(isVowel(s.charAt(i))) vowels++;
        }
        int maxCount = vowels;
        for(int i=1; i<=s.length()-k; i++){
            //check if previous character is vowel
            if(isVowel(s.charAt(i-1))) vowels--;
            //check if next character in the window is vowel
            if(isVowel(s.charAt(i+k-1))) vowels++;
            maxCount = Math.max(maxCount, vowels);
        }

        return maxCount;
    }

    private boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch =='u';
    }
}
