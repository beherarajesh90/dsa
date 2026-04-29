package com.interviewprep.dsa.stacksAndQueues.monotonicStack;

import java.util.Stack;

//https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Stack<int[]> stack = new Stack<>();
        int maxArea = 0;
        for(int i=0; i<heights.length; i++){
            int start = i;
            while(!stack.isEmpty() && heights[i] < stack.peek()[0]){
                int[] top = stack.pop();
                int h = top[0];
                int j = top[1];
                int w = i - j;
                maxArea = Math.max(maxArea, h * w);
                start = j;
            }
            stack.push(new int[]{heights[i], start});    //height, index
        }
        int n = heights.length;
        while(!stack.isEmpty()){
            int[] top = stack.pop();
            int h = top[0];
            int j = top[1];
            int w = n - j;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }
}
