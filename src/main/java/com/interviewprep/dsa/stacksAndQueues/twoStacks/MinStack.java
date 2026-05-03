package com.interviewprep.dsa.stacksAndQueues.twoStacks;

import java.util.Stack;

public class MinStack {

     private Stack<Integer> stack;
     private Stack<Integer> minStack;

     public MinStack() {
         stack = new Stack<>();
         minStack = new Stack<>();
     }

     public void push(int val) {
         stack.push(val);
         if(minStack.isEmpty() || val <= minStack.peek()){
             minStack.push(val);
         } else{
             minStack.push(minStack.peek());
         }
     }

     public void pop() {
         stack.pop();
         minStack.pop();
     }

     public int top() {
         return stack.peek();
     }

     public int getMin() {
         return minStack.peek();
     }
}

// alternative implementation
//class MinStack {
//    static ListNode head;
//    static int min;
//    public MinStack() {
//        head = null;
//        min = Integer.MAX_VALUE;
//    }
//
//    public void push(int val) {
//
//        if(min > val){
//            min = val;
//        }
//        head = new ListNode(val, min, head);
//    }
//
//    public void pop() {
//        head = head.next;
//        if(head == null){min = Integer.MAX_VALUE;}
//        else {min = head.currentMin;}
//    }
//
//    public int top() {
//        return head.val;
//    }
//
//    public int getMin() {
//        return head.currentMin;
//    }
//}
//class ListNode {
//    int      val;
//    int      currentMin;
//    ListNode next;
//
//    ListNode() {}
//
//    ListNode(int val) {
//        this.val = val;
//    }
//
//    ListNode(int val, int currentMin, ListNode next) {
//        this.currentMin = currentMin;
//        this.val = val;
//        this.next = next;
//    }
//}
