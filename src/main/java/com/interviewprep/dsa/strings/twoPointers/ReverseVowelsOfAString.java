package com.interviewprep.dsa.strings.twoPointers;

public class ReverseVowelsOfAString {
    public String reverseVowels(String s) {
        int left = 0, right = s.length() - 1;
        char[] chars = s.toCharArray();
        while(left <= right){
            if(isVowel(chars[left]) && isVowel(chars[right])){
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            } else if(!isVowel(chars[left])){
                left++;
            } else{
                right--;
            }
        }
        return new String(chars);
    }

    private boolean isVowel(char ch){
        return ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u'
                || ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U';
    }
}
