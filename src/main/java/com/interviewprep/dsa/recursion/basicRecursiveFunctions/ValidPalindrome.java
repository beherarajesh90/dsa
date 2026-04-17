package com.interviewprep.dsa.recursion.basicRecursiveFunctions;

public class ValidPalindrome {
    public boolean isPalindromeRecursive(String s) {
        return checkPalindrome(s, 0, s.length()-1);
    }

    private boolean checkPalindrome(String s, int start, int end){
        if(start>=end) return true;
        char startCh = s.charAt(start);
        char endCh = s.charAt(end);
        if(!Character.isLetterOrDigit(startCh) && !Character.isLetterOrDigit(endCh)){
            return checkPalindrome(s, start+1, end-1);
        } else if(!Character.isLetterOrDigit(startCh)){
            return checkPalindrome(s, start+1, end);
        } else if(!Character.isLetterOrDigit(endCh)){
            return checkPalindrome(s, start, end-1);
        } else{
            return (Character.toLowerCase(startCh) == Character.toLowerCase(endCh)) && checkPalindrome(s, start+1, end-1);
        }
    }

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length()-1;
        while(left < right){
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
