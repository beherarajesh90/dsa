package com.interviewprep.dsa.stacksAndQueues.slidingWindowAndMonotonicQueue.readme;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/design-front-middle-back-queue/
class FrontMiddleBackQueue {

    private Deque<Integer> front;
    private Deque<Integer> rear;

    public FrontMiddleBackQueue() {
        front = new ArrayDeque<>();
        rear = new ArrayDeque<>();
    }
    
    public void pushFront(int val) {
        front.addFirst(val);
        rebalance();
    }
    
    public void pushMiddle(int val) {
        if(front.size() > rear.size()){
            rear.addFirst(front.removeLast());
        }
        front.addLast(val);
    }
    
    public void pushBack(int val) {
        rear.addLast(val);
        rebalance();
    }
    
    public int popFront() {
        if(isEmpty()) return -1;

        int val;
        if(!front.isEmpty()){
            val = front.removeFirst();
        } else{
            val = rear.removeFirst();
        }
        rebalance();
        return val;
    }
    
    public int popMiddle() {
        if(isEmpty()) return -1;

        int val = front.removeLast();
        rebalance();
        return val;
    }
    
    public int popBack() {
        if(isEmpty()) return -1;

        int val;
        if(!rear.isEmpty()){
            val = rear.removeLast();
        } else{
            val = front.removeLast();
        }
        rebalance();
        return val;
    }

    private boolean isEmpty(){
        return front.isEmpty() && rear.isEmpty();
    }

    private void rebalance(){

        // rebalance front and back queues
        // front can have atmost one extra element

        if(front.size() > rear.size() + 1){
            rear.addFirst(front.removeLast());
        } else if(front.size() < rear.size()){
            front.addLast(rear.removeFirst());
        }

    }
}