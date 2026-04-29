package com.interviewprep.dsa.stacksAndQueues.monotonicStack;

import java.util.Stack;


class StockSpanner {
    Stack<int[]> stack;
    public StockSpanner() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1;
        while(!stack.isEmpty() && price >= stack.peek()[0]){
            span += stack.pop()[1];
        }
        stack.add(new int[]{price, span});
        return span;
    }
}

class StockSpannerUsingArray {
    int[] stack;
    int[] spans;
    int top = -1;
    public StockSpannerUsingArray() {
        stack = new int[10000];
        spans = new int[10000];
    }

    public int next(int price) {
        int span = 1;
        while(top>=0 && price >= stack[top]){
            span += spans[top--];
        }
        stack[++top] = price;
        spans[top] = span;
        return span;
    }
}

public class OnlineStockSpan {

}


