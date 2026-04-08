package com.interviewprep.dsa.strings.twoPointers;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right){
            if (!isAlphaNumeric(s.charAt(left))){
                left++;
                continue;
            }
            if (!isAlphaNumeric(s.charAt(right))){
                right--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
            left++;
            right--;
        }
        return true;
    }

    private boolean isAlphaNumeric(char ch){
        return (ch >= 65 && ch<= 90) || (ch >= 97 && ch<= 122) || (ch>=48 && ch<=57);
    }
}
