package com.interviewprep.dsa.stacksAndQueues.slidingWindowAndMonotonicQueue.readme;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] res = new int[n-k+1];
        for(int i=0; i<n; i++) {
            // Remove indices out of window
            if(!dq.isEmpty() && dq.getFirst() < i+1-k){
                dq.removeFirst();
            }
            // Remove indices whose values are less than nums[i]
            while (!dq.isEmpty() && nums[dq.getLast()] < nums[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
            if (i >= k-1){
                res[i-k+1] = nums[dq.getFirst()];
            }
        }
        return res;
    }
}
