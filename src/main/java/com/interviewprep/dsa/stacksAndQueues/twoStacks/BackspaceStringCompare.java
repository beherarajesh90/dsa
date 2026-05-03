package com.interviewprep.dsa.stacksAndQueues.twoStacks;

//https://leetcode.com/problems/backspace-string-compare/
public class BackspaceStringCompare {
    public boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    private String build(String str){
        StringBuilder sb = new StringBuilder();
        for(char ch: str.toCharArray()){
            if(ch != '#'){
                sb.append(ch);
            } else if(!sb.isEmpty()){
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }

    //efficient approach
    public boolean backspaceCompareUsingTwoPointers(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        while(i>=0 || j>=0){

            i = getValidIndex(s, i);
            j = getValidIndex(t, j);

            if(i<0 && j<0) return true;
            if(i<0 || j<0) return false;
            if(s.charAt(i) != t.charAt(j)) return false;

            i--;
            j--;
        }
        return true;
    }

    private int getValidIndex(String s, int index){
        int backSpaces = 0;
        while(index >= 0){
            if(s.charAt(index) == '#'){
                backSpaces++;
                index--;
            } else if(backSpaces > 0){
                backSpaces--;
                index--;
            } else{
                break;
            }
        }
        return index;
    }
}
