package com.interviewprep.dsa.priorityQueues.topKFrequentElements;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/task-scheduler/
public class TaskScheduler {

    //Mathematical approach (optimal)
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char task: tasks){
            freq[task - 65]++;
        }

        int maxFreq = 0;
        int count = 0;
        for(int f: freq){
            if(f > maxFreq){
                maxFreq = f;
                count = 1;
            } else if(f == maxFreq){
                count++;
            }
        }

        int intervals = (maxFreq - 1) * (n+1) + count;         //no of gaps * size of gap + last cycle size(maxFreq count)
        return Math.max(tasks.length, intervals);
    }

    //heap and queue
    public int leastInterval2(char[] tasks, int n) {
        // Step 1: Count task frequencies using an array (A-Z)
        int[] freq = new int[26];
        for (int task: tasks){
            freq[task - 65]++;
        }

        // Step 2: Build the Max-Heap
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f: freq){
            if(f > 0) maxHeap.offer(f);
        }

        // Step 3: Initialize time and the waiting room (Queue)
        int time = 0;

        // The queue will store arrays of size 2: {remaining_count, unlock_time}
        Queue<int[]> q = new LinkedList<>();

        // Step 4: Simulate the clock
        while (!maxHeap.isEmpty() || !q.isEmpty()){
            time++;

            // Put the task on the schedule (Pop from Heap)
            if(!maxHeap.isEmpty()){
                int remaining = maxHeap.poll() - 1;

                // If there are still tasks left, put it in the waiting room
                if(remaining > 0) q.offer(new int[]{remaining, time + n});
            }

            // Check if the task at the front of the waiting room is ready
            if(!q.isEmpty() && q.peek()[1] == time){
                // Pop from queue, push back to heap
                maxHeap.offer(q.poll()[1]);
            }
        }

        return time;
    }
}
