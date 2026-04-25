package com.interviewprep.dsa.arrays.twoPointers;

//https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWaterHard {
    public int trap(int[] height) {
        int left = 0, right = height.length-1, total = 0;
        int leftMax = height[left], rightMax = height[right];
        while(left<right){
            if(leftMax < rightMax){
                left++;
                leftMax = Math.max(leftMax, height[left]);
                total+=leftMax - height[left];
            } else{
                right--;
                rightMax = Math.max(rightMax, height[right]);
                total+=rightMax - height[right];
            }
        }
        return total;
    }

    public int trap2(int[] height) {
        int left = 0, right = height.length - 1, water = 0;
        int leftMax = 0, rightMax = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }
}
