package com.interviewprep.dsa.binarySearch.rangeSearch;

public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int days) {
        int left=0, right=0;
        for(int weight: weights){
            left = Math.max(left, weight);
            right+=weight;
        }
        while(left<=right){
            int mid = left + (right-left)/2;
            if(canShip(weights, mid, days)){
                right = mid-1;
            } else{
                left = mid+1;
            }
        }
        return left;
    }

    private boolean canShip(int[] weights, int capacity, int days){
        int shippingDays = 1, total=0;
        for(int weight: weights){
            if(total+weight > capacity){
                shippingDays++;
                total=0;
            }
            total+=weight;
        }
        return shippingDays <= days;
    }
}
