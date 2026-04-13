package com.interviewprep.dsa.strings.stringManipulation;

public class ReverseString {
    public void reverseString(char[] s) {
        int n = s.length;
        for(int i=0; i<n/2; i++){
            char temp = s[i];
            s[i] = s[n - i - 1];
            s[n - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        char[] s = new char[]{'h','e','l','l','o'};
        reverseString.reverseString(s);
        System.out.println(s);
    }
}
