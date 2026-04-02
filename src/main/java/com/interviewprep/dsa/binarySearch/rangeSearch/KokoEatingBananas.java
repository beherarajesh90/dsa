package com.interviewprep.dsa.binarySearch.rangeSearch;

public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int left=1, right=0;
        for(int bananas: piles){
            right = Math.max(right, bananas);
        }
        int result=right;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(canFinish(piles,mid,h)){
                result=mid;
                right = mid-1;
            } else{
                left = mid+1;
            }
        }
        return result;
    }

    private boolean canFinish(int[] piles, long kPerHour, int hours){
        long noOfHrs = 0;
        for(int pile:piles){
            noOfHrs+=((pile-1)/kPerHour)+1;
        }
        return noOfHrs<=hours;
    }
}
