package com.interviewprep.dsa.binarySearch.rangeSearch;

public class MinimumNumberOfDaysToMakeMBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        if(m*k>bloomDay.length) return -1;
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for(int bloom: bloomDay){
            left = Math.min(left,bloom);
            right = Math.max(right, bloom);
        }
        int result = -1;
        while(left<=right){
            int mid = left + (right - left)/2;
            if(canMake(bloomDay, m, k, mid)){
                result = mid;
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return result;
    }

    private boolean canMake(int[] bloomDay, int m, int k, int days){
        int bouquets = 0, flowers = 0;
        for(int bloom: bloomDay){
            if(bloom<=days){
                flowers++;
                if(flowers == k){
                    bouquets++;
                    flowers = 0;
                }
            } else{
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}
