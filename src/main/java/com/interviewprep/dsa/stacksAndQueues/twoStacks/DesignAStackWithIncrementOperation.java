package com.interviewprep.dsa.stacksAndQueues.twoStacks;

class DesignAStackWithIncrementOperation {

}

class CustomStack {

    int[] stack;
    int[] inc;
    int top;
    int maxSize;
    public CustomStack(int maxSize) {
        stack = new int[maxSize];
        inc = new int[maxSize];
        top = -1;
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if(top+1 < maxSize){
            stack[++top] = x;
        }
    }

    public int pop() {
        if(top == -1) return -1;
        int res = stack[top] + inc[top];
        if(top>0) inc[top - 1] += inc[top];
        inc[top] = 0;
        top--;
        return res;
    }

    public void increment(int k, int val) {
        int ind = Math.min(top, k - 1);
        if(ind>=0) inc[ind] += val;
    }
}