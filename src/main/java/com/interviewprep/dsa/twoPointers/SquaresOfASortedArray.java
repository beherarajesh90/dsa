package com.interviewprep.dsa.twoPointers;

public class SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] squares = new int[n];
        int left = 0, right = n-1, highestSqIndx = n-1;
        while(left<=right){
            int leftSqSum = nums[left] * nums[left];
            int rightSqSum = nums[right] * nums[right];
            if(leftSqSum > rightSqSum){
                squares[highestSqIndx--] = leftSqSum;
                left++;
            } else{
                squares[highestSqIndx--] = rightSqSum;
                right--;
            }
        }
        return squares;
    }
}
