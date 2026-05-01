package com.interviewprep.dsa.stacksAndQueues.expressionEvaluation;

import java.util.Stack;

//https://leetcode.com/problems/simplify-path/
public class SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] tokens = path.split("/");
        for (String token : tokens) {
            if(token.isEmpty() || token.equals(".")) continue;
            if (token.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(token);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String token : stack) {
            sb.append("/").append(token);
        }
        return !sb.isEmpty() ? sb.toString() : "/";
    }

    public static void main(String[] args) {
        SimplifyPath sp = new SimplifyPath();
        System.out.println(sp.simplifyPath("/a/../../b/"));;
    }
}
