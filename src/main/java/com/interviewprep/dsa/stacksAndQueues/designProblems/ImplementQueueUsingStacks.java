package com.interviewprep.dsa.stacksAndQueues.designProblems;

import java.util.Stack;

public class ImplementQueueUsingStacks {

    public static void main(String[] args) {

        MyQueue obj = new MyQueue();
        obj.push(2);
        obj.push(3);
        int param_2 = obj.pop();
        int param_3 = obj.peek();
        obj.push(5);
        int param_4 = obj.pop();
        boolean param_5 = obj.empty();
        obj.pop();
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
        System.out.println(param_5);
        System.out.println(obj.empty());

    }
}

class MyQueue {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;
    private int size;

    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
        size = 0;
    }

    public void push(int x) {
        inStack.push(x);
        size++;
    }

    public int pop() {
        peek();
        size--;
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    public boolean empty() {
        return size == 0;
    }
}