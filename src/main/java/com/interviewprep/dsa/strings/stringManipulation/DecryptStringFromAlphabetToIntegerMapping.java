package com.interviewprep.dsa.strings.stringManipulation;

//https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/description/
public class DecryptStringFromAlphabetToIntegerMapping {
    public String freqAlphabets(String s) {
        StringBuilder result = new StringBuilder();
        int i = s.length()-1;
        while(i>=0){
            if(s.charAt(i) == '#'){
                result.append((char)('a'+Integer.valueOf(s.substring(i-2,i))-1));
                i-=3;
            } else{
                result.append((char)('a'+Integer.valueOf(s.substring(i,i+1))-1));
                i--;
            }
        }
        return result.reverse().toString();
    }
}
