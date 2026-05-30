package com.interviewprep.dsa.priorityQueues.topKFrequentElements;

//https://leetcode.com/problems/task-scheduler/
public class TaskScheduler {
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
}
