package com.interviewprep.dsa.stacksAndQueues.designProblems;

import java.util.Stack;

public class BaseballGame {
    public int calPoints(String[] operations) {
        Stack<Integer> score = new Stack<>();
        for(String s: operations){
            if(s.equals("+")){
                int top = score.pop();
                int newTop = score.peek();
                score.push(top);
                score.push(top + newTop);
            } else if(s.equals("D")){
                score.push(score.peek()*2);
            } else if(s.equals("C")){
                score.pop();
            } else{
                score.push(Integer.valueOf(s));
            }
        }
        return score.stream().mapToInt(n-> n).sum();
    }
}
