package com.interviewprep.dsa.binarySearch.basicBinarySearch;

public class MinimumLimitOfBallsInABag {
    public int minimumSize(int[] nums, int maxOperations) {
        int left=1, right=0;
        //check for max num
        for(int num:nums){
            right = Math.max(right, num);
        }
        int result=right; //initially result is max penalty bag
        while(left<=right){
            int mid = left + (right-left)/2;
            if(canSplit(nums, mid, maxOperations)){
                result = mid;
                right = mid-1;
            } else{
                left = mid+1;
            }
        }
        return result;
    }

    private boolean canSplit(int[] nums, int limit, int maxOp){
        long ops = 0;
        for(int balls:nums){
            ops+=(balls-1)/limit;
        }
        return ops<=maxOp;
    }

    public static void main(String[] args) {
        MinimumLimitOfBallsInABag obj = new MinimumLimitOfBallsInABag();
        int[] nums = {1000000000,1000000000,1000000000};
        System.out.println(obj.minimumSize(nums, 1000000000));
    }
}
