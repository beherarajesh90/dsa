package com.interviewprep.dsa.strings.regularExpressions;

//https://leetcode.com/problems/detect-capital/description/
public class DetectCapital {

    public boolean detectCapitalUse(String word) {
        if(word.equals(word.toUpperCase())) return true;
        if(word.equals(word.toLowerCase())) return true;
        if (Character.isUpperCase(word.charAt(0)) && word.substring(1).equals(word.substring(1).toLowerCase())) return true;
        return false;
    }

    public boolean detectCapitalUseOptimized(String word) {
        int capsCount = 0;
        for(char ch: word.toCharArray()){
            if (ch < 96) capsCount++;
        }

        return (capsCount == 1 && word.charAt(0) < 96) || capsCount == 0 || (capsCount == word.length());
    }

    public static void main(String[] args) {
        System.out.println((int)'a');
    }
}
