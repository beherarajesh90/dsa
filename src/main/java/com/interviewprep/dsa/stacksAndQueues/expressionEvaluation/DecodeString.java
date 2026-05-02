package com.interviewprep.dsa.stacksAndQueues.expressionEvaluation;

//https://leetcode.com/problems/decode-string/
public class DecodeString {

    private int i = 0;

    public String decodeString(String s) {
        int k = 0;
        StringBuilder res = new StringBuilder();
        while(i < s.length()){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                k = k * 10 + (ch - '0');
            }
            else if(ch == '['){
                i++;
                String subRes = decodeString(s);
                while(k-- > 0) res.append(subRes);
                k = 0;
            } else if(ch == ']'){
                return res.toString();
            }
            else{
                res.append(ch);
            }
            i++;
        }
        return res.toString();
    }
}
