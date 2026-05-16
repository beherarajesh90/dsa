package com.interviewprep.dsa.stacksAndQueues.slidingWindowAndMonotonicQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class ShortestSubarrayWithSumAtLeastK {
    public int shortestSubarray(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int n = nums.length, res = n+1;
        long[] prefixSum = new long[n+1];
        for(int i=0; i<n; i++)
            prefixSum[i+1] = prefixSum[i] + nums[i];
        for (int i=0; i<=n; i++){
            while (!dq.isEmpty() && prefixSum[i] - prefixSum[dq.peekFirst()]>=k){
                res = Math.min(res, i - dq.removeFirst());
            }
            while (!dq.isEmpty() && prefixSum[i] <= prefixSum[dq.peekLast()]){
                dq.removeLast();
            }
            dq.addLast(i);
        }
        return res == n+1 ? -1 : res;
    }
}
