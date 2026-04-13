package com.interviewprep.dsa.strings.stringManipulation;

//https://leetcode.com/problems/string-to-integer-atoi/description/
public class StringToInteger {
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        while(i<n && s.charAt(i) == ' ') i++;

        int sign = 1;
        if(i<n && (s.charAt(i)=='-' || s.charAt(i)=='+')){
            sign = (s.charAt(i)=='-') ? -1 : 1;
            i++;
        }

        int result = 0, maxVal = Integer.MAX_VALUE;
        while (i < n && Character.isDigit(s.charAt(i))){
            int digit = s.charAt(i) - '0';
            //check for overflow of integers. since we are going to set result = result * 10 + digit,before that we check with (max - digit)/10
            if(result > (maxVal - digit)/10) return (sign == 1) ? maxVal : Integer.MIN_VALUE;
            result = result * 10 + digit;
            i++;
        }

        return sign * result;
    }
}
