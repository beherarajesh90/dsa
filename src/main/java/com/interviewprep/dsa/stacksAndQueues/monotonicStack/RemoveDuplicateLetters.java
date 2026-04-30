package com.interviewprep.dsa.stacksAndQueues.monotonicStack;

//https://leetcode.com/problems/remove-duplicate-letters/description/
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        boolean[] seen = new boolean[26];
        int[] lastIndex = new int[26];
        StringBuilder stack = new StringBuilder();

        //note the last seen index
        for(int i=0; i<s.length(); i++)
            lastIndex[s.charAt(i) - 'a'] = i;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(seen[ch - 'a']) continue;
            while(!stack.isEmpty() && ch < stack.charAt(stack.length()-1) && i<lastIndex[stack.charAt(stack.length()-1) - 'a']){
                seen[stack.charAt(stack.length()-1) - 'a'] = false;
                stack.deleteCharAt(stack.length()-1);
            }
            stack.append(ch);
            seen[ch - 'a'] = true;
        }
        return stack.toString();
    }
}
