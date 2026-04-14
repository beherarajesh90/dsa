package com.interviewprep.dsa.strings.stringManipulation;

//https://leetcode.com/problems/string-compression/description/
public class StringCompression {
    public int compress(char[] chars) {
        int n = chars.length;
        int i = 0, j = 0;
        while (i < n) {
            char current = chars[i];
            int count = 0;
            // Count the number of same consecutive chars
            while (i < n && chars[i] == current) {
                i++;
                count++;
            }
            // Write the character
            chars[j++] = current;
            // If count is greater than 1, add count in char form
            if (count > 1) {
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[j++] = c;
                }
            }
        }
        return j;
    }
}
