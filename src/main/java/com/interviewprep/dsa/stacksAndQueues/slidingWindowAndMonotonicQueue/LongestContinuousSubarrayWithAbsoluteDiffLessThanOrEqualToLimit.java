package com.interviewprep.dsa.stacksAndQueues.slidingWindowAndMonotonicQueue;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description/
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> minDq = new ArrayDeque<>();
        Deque<Integer> maxDq = new ArrayDeque<>();
        int res = 0, left=0;
        for (int right=0; right<nums.length; right++){
            while (!minDq.isEmpty() && nums[right]<minDq.getLast()){
                minDq.removeLast();
            }
            minDq.addLast(nums[right]);

            while (!maxDq.isEmpty() && nums[right]>maxDq.getLast()){
                maxDq.removeLast();
            }
            maxDq.addLast(nums[right]);

            while (maxDq.getFirst() - minDq.getFirst() > limit){
                if (nums[left] == minDq.getFirst()){
                    minDq.removeFirst();
                }
                if (nums[left] == maxDq.getFirst()){
                    maxDq.removeFirst();
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit obj = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit();
        int[] nums = {8,2,4,7};
        int limit = 4;
        System.out.println(obj.longestSubarray(nums, limit));
    }
}
