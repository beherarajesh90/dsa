package com.interviewprep.dsa.stacksAndQueues.slidingWindowAndMonotonicQueue.readme;

import java.util.Deque;

//https://leetcode.com/problems/design-circular-deque/
public class CircularImplementationOfDeque {
}
//rear->[  ,   ,   ,   ,   ,  ]<-front
class CircularDeque {
    private int[] arr;
    private final int maxSize;
    private int front;
    private int rear;

    public CircularDeque(int maxSize){
        arr = new int[maxSize];
        this.maxSize = maxSize;
        front = -1;
        rear = 0;
    }

    public boolean isFull(){
        return (front == 0 && rear == maxSize-1) || front == rear + 1;
    }

    public boolean isEmpty(){
        return front == -1;
    }

    public boolean insertFront(int value) {
        if(isFull()){
            return false;
        }
        if (front == -1){
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = maxSize-1;
        } else {
            front = front - 1;
        }
        arr[front] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if(isFull()){
            return false;
        }
        if (front == -1){
            front = 0;
            rear = 0;
        } else if (rear == maxSize - 1) {
            rear = 0;
        } else {
            rear+=1;
        }
        arr[rear] = value;
        return true;
    }

    public boolean deleteFront() {
        if(isEmpty()){
            return false;
        }
        if(front == rear){
            front = -1;
            rear = -1;
        } else if (front == maxSize - 1) {
            front = 0;
        } else {
            front = front + 1;
        }
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()){
            return false;
        }
        if(rear == front){
            rear = -1;
            front = -1;
        } else if (rear == 0){
            rear = maxSize - 1;
        } else {
            rear = rear - 1;
        }
        return true;
    }

    public int getFront() {
        return front == -1 ? front : arr[front];
    }

    public int getRear() {
        return rear == -1 ? rear : arr[rear];
    }

}
