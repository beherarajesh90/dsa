package com.interviewprep.dsa.stacksAndQueues.monotonicStack;

import java.util.Stack;

//https://leetcode.com/problems/daily-temperatures/
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temp) {
        Stack<int[]> stack = new Stack<>();
        int[] res = new int[temp.length];
        for(int i=0; i<temp.length; i++){
            while(!stack.isEmpty() && temp[i] > stack.peek()[0]){
                int[] top = stack.pop();
                int t = top[0];
                int prevDay = top[1];
                int days = i - prevDay;
                res[prevDay] = days;
            }
            stack.push(new int[]{temp[i], i});  //temp, index
        }
        return res;
    }
}
