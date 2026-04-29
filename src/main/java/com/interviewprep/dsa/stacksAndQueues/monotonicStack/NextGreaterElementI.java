package com.interviewprep.dsa.stacksAndQueues.monotonicStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//https://leetcode.com/problems/next-greater-element-i/
public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> nextGreater = new HashMap<>();
        for(int num: nums2){
            while (!stack.isEmpty() && num>stack.peek()){
                nextGreater.put(stack.pop(), num);
            }
            stack.add(num);
        }
        int[] result = new int[nums1.length];
        for(int i=0; i<nums1.length; i++){
            result[i] = nextGreater.getOrDefault(nums1[i], -1);
        }
        return result;
    }

    //Using Array as Stack - refer above solution for interview prep
    public int[] nextGreaterElementUsingArrayAsStack(int[] nums1, int[] nums2) {
        int[] stack = new int[nums2.length];
        int[] checklist = new int[10001];
        int top = -1;
        for (int num: nums2){
            while(top>=0 && num > stack[top]){
                checklist[stack[top--]] = num;
            }
            stack[++top] = num;
        }
        while (top>=0){
            checklist[stack[top--]] = -1;
        }
        int[] result = new int[nums1.length];
        for (int i=0; i<nums1.length; i++){
            result[i] = checklist[nums1[i]];
        }
        return result;
    }


}
