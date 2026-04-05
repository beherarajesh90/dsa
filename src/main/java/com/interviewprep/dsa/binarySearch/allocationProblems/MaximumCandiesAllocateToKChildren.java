package com.interviewprep.dsa.binarySearch.allocationProblems;

public class MaximumCandiesAllocateToKChildren {

    public int maximumCandiesOptimized(int[] candies, long k) {
        long sum = 0;
        int maxCandy = 0;

        // 1. Find the maximum value and calculate the sum.
        for (int candy : candies) {
            sum += candy;
            if (candy > maxCandy) {
                maxCandy = candy;
            }
        }

        // 2. Early Exit: If the total number of candies is insufficient to distribute among k children, return 0 immediately.
        if (sum < k) {
            return 0;
        }

        // 3. Dynamically Shrink the Right Boundary
        int left = 1;
        int right = (int) Math.min((long) maxCandy, sum / k);

        while (left <= right) {
            // Using an unsigned right shift to prevent overflow is equivalent to (left + right) / 2.
            int mid = (left + right) >>> 1;
            if (check(candies, k, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    private boolean check(int[] candies, long k, int max) {
        long num = 0;
        for (int candy : candies) {
            num += candy / max;
            if (num >= k) {
                return true;
            }
        }
        return false;
    }

    public int maximumCandies(int[] candies, long k) {
        long left = 1, right = 0, result = 0;
        for(int candyPile: candies){
            right = Math.max(right, candyPile);
        }
        while(left <= right){
            long mid = left + (right - left)/2;
            if(canAllocate(candies, k, mid)){
                result = mid;
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return (int)result;
    }

    private boolean canAllocate(int[] candies, long k, long maxCandies){
        long children = 0;
        for(int candyPile: candies){
            children+=candyPile/maxCandies;
        }
        return children >= k;
    }

    public static void main(String[] args) {
        MaximumCandiesAllocateToKChildren obj = new MaximumCandiesAllocateToKChildren();
        int[] candies = {5, 8, 6};
        long k = 4;
        System.out.println(obj.maximumCandies(candies, k));
    }
}
