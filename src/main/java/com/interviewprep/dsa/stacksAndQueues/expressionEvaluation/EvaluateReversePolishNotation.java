package com.interviewprep.dsa.stacksAndQueues.expressionEvaluation;

import java.util.Stack;

//https://leetcode.com/problems/evaluate-reverse-polish-notation/
public class EvaluateReversePolishNotation {
    public int evalRPNIterative(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String s: tokens){
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                Integer op1 = stack.pop();
                Integer op2 = stack.pop();
                if(s.equals("+")) stack.push(op1 + op2);
                else if(s.equals("-")) stack.push(op2 - op1);
                else if(s.equals("*")) stack.push(op1 * op2);
                else if(s.equals("/")) stack.push(op2 / op1);
            } else{
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }

    //recursive approach and efficient
    private int indx;
    public int evalRPN(String[] tokens) {
        indx = tokens.length-1;
        return eval(tokens);
    }

    private int eval(String[] tokens){
        String token = tokens[indx--];
        switch(token){
            case "+":
                return eval(tokens) + eval(tokens);
            case "-":
                int b = eval(tokens);
                int a = eval(tokens);
                return a - b;
            case "*":
                return eval(tokens) * eval(tokens);
            case "/":
                int d = eval(tokens);
                int c = eval(tokens);
                return c / d;
            default:
                return Integer.parseInt(token);
        }
    }
}
