package com.interviewprep.dsa.stacksAndQueues.designProblems;

public class DesignCircularQueue {
    //  Your MyCircularQueue object will be instantiated and called as such:
    MyCircularQueue obj = new MyCircularQueue(4);
    boolean param_1 = obj.enQueue(3);
    boolean param_2 = obj.deQueue();
    int param_3 = obj.Front();
    int param_4 = obj.Rear();
    boolean param_5 = obj.isEmpty();
    boolean param_6 = obj.isFull();
}

class MyCircularQueue {
    int[] q;
    int maxSize;
    int size;
    int front;
    int rear;
    public MyCircularQueue(int k) {
        q = new int[k];
        maxSize = k;
        size = 0;
        front=k-1;
        rear=k-1;
    }

    public boolean enQueue(int value) {
        if(isFull()) return false;
        q[rear] = value;
        rear = (rear+1)%maxSize;
        size++;
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()) return false;
        size--;
        front = (front+1)%maxSize;
        return true;
    }

//    public boolean enQueue(int value) {
//        if (isFull()) return false;
//        q[rear]=value;
//        if(rear==q.length-1){
//            rear=0;
//        }else {
//            rear++;
//        }
//        size++;
//        return true;
//    }
//
//    public boolean deQueue() {
//        if (isEmpty()) return false;
//        if(front==q.length-1){
//            front=0;
//        }else {
//            front++;
//        }
//        size--;
//        return true;
//    }

    public int Front() {
        return isEmpty() ? -1: q[front];
    }

    public int Rear() {
        return isEmpty() ? -1: q[(rear-1+maxSize)%maxSize];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }
}
 