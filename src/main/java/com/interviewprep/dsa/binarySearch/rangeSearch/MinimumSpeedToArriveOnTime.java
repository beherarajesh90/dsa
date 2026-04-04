package com.interviewprep.dsa.binarySearch.rangeSearch;

//https://leetcode.com/problems/minimum-speed-to-arrive-on-time/description/
public class MinimumSpeedToArriveOnTime {

    public int minSpeedOnTimeOptimized(int[] dist, double hour) {
        int minSpeed=1, maxSpeed=0, n=dist.length, result = -1;
        for (int d:dist){
            maxSpeed = Math.max(maxSpeed, d);
        }
        int lastTrainSpeed = 0;
        if(hour>n-1){
            double lastTrainTime = hour - n + 1;
            lastTrainSpeed = (int)Math.ceil(dist[n-1]/lastTrainTime);
        } else{
            return -1;
        }
        maxSpeed = Math.max(maxSpeed, lastTrainSpeed);
        while (minSpeed<=maxSpeed){
            int mid = minSpeed + (maxSpeed - minSpeed)/2;
            if (canReach(dist, hour,mid)){
                result = mid;
                maxSpeed = mid - 1;
            } else {
                minSpeed = mid + 1;
            }
        }
        return result;
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        if(Math.ceil(hour) < dist.length) return -1;
        int left = 1, right = 10000000, result = -1;
        while(left<=right){
            int mid = left + (right - left)/2;
            if(canReach(dist, hour, mid)){
                result = mid;
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return result;
    }

    private boolean canReach(int[] dist, double hour, int speed){
        double curHrs = 0.0;
        int n = dist.length;
        for(int i=0; i<n; i++){
            if(i!=n-1){
                curHrs+=(dist[i]-1+speed)/speed;
            }else{
                curHrs+=dist[i]/(1.0 * speed);
            }
        }
        return curHrs<=hour;
    }

    public static void main(String[] args) {
        MinimumSpeedToArriveOnTime obj = new MinimumSpeedToArriveOnTime();
//        int[] dist = {1,3,2};
//        double hour = 6;
        int[] dist = {1,9};
        double hour = 1.18;
        System.out.println(obj.minSpeedOnTimeOptimized(dist, hour));
    }
}
