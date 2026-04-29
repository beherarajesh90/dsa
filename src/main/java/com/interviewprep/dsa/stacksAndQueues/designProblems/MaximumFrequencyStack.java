package com.interviewprep.dsa.stacksAndQueues.designProblems;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//https://leetcode.com/problems/maximum-frequency-stack/description/
class FreqStack {

    Map<Integer, Integer> freq;
    Map<Integer, Stack<Integer>> group;
    int maxFreq;
    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {

        //get current val freq and add to the freq map if not present
        int valFreq = freq.getOrDefault(val, 0)+1;
        freq.put(val, valFreq);

        // push the value to stack, if not present create one
        //k here is valFreq
        group.computeIfAbsent(valFreq, k-> new Stack<>()).push(val);

        // update maxFreq
        if(valFreq>maxFreq) maxFreq = valFreq;
    }

    public int pop() {
        //pop from group stack
        Stack<Integer> grpStack = group.get(maxFreq);
        int val = grpStack.pop();

        //decrease the frequency of the popped value
        freq.put(val, freq.get(val)-1);

        //if maxFreq group is empty, decrement the maxFreq
        if(grpStack.isEmpty()) maxFreq--;

        return val;

    }
}

public class MaximumFrequencyStack {
    public static void main(String[] args) {


//          Your FreqStack object will be instantiated and called as such:

        FreqStack obj = new FreqStack();
        obj.push(4);
        int param_2 = obj.pop();

    }
}