package com.interviewprep.dsa.stacksAndQueues.expressionEvaluation;

import java.util.Stack;

//https://leetcode.com/problems/basic-calculator-ii/
public class BasicCalculatorII {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int n = s.length();
        int num = 0;
        char sign = '+';
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            //if character is digit build the number
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }
            //If character is an operator or it's the last character:
            if((!Character.isDigit(ch) && ch != ' ') || i == n-1){

                if(sign == '+') stack.push(num);
                else if(sign == '-') stack.push(-num);
                else if(sign == '*') stack.push(stack.pop() * num);
                else if(sign == '/') stack.push(stack.pop() / num);

                sign = ch;
                num = 0;
            }
        }
        int res = 0;
        for(int i: stack) res+=i;
        return res;
    }
}
