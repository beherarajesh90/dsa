package com.interviewprep.dsa.binarySearch.rangeSearch;

public class FindTheSmallestDivisorGivenAThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = 0;
        for(int num: nums){
            right = Math.max(right, num);
        }
        int result = right;
        while(left<=right){
            int mid = left + (right - left)/2;
            if(canDivide(nums, threshold, mid)){
                result = mid;
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return result;
    }

    private boolean canDivide(int[] nums, int threshold, int divisor){
        int sum = 0;
        for(int num: nums){
            // sum+=Math.ceil((float)num/divisor);
            sum+=(num-1+divisor)/divisor;
        }
        // System.out.println(divisor+" "+sum);
        return sum <= threshold;
    }
}
