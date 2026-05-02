package com.interviewprep.dsa.stacksAndQueues.expressionEvaluation;

import java.util.Stack;

//https://leetcode.com/problems/basic-calculator/description/
public class BasicCalculator {
    public int calculateIterative(String s) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, k = 0, res = 0, sign = 1;
        while(i<s.length()){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                k = 0;
                while(i<s.length() && Character.isDigit(s.charAt(i))){
                    k = k * 10 + (s.charAt(i) - '0');
                    i++;
                }
                res+=sign * k;
                i--;
            } else if(ch == '+'){
                sign = 1;
            } else if(ch == '-'){
                sign = -1;
            } else if(ch == '('){
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if(ch == ')'){
                int prevSign = stack.pop();
                int prevRes = stack.pop();
                res = prevRes + prevSign * res;
            }
            i++;
        }
        return res;
    }

    //recursive and efficient
    public int calculateRecursive(String s) {
        return dfs(s.toCharArray(), 0)[0];
    }

    private int[] dfs(char[] chars, int index){
        int res = 0;
        int sign = 1;
        int num = 0;
        while(index < chars.length){
            if(chars[index]>='0' && chars[index]<=9){
                num = num * 10 + chars[index] - '0';
            } else if(chars[index] == '+'){
                res += sign * num;
                num = 0;
                sign = 1;
            } else if(chars[index] == '-'){
                res += sign * num;
                num = 0;
                sign = -1;
            } else if(chars[index] == '('){
                int[] subRes = dfs(chars, index+1);
                num = subRes[0];
                index = subRes[1];
            } else if(chars[index] == ')'){
                res += sign * num;
                return new int[]{res, index};
            }
            index++;
        }
        res += sign * num;
        return new int[]{res, index};
    }
}
