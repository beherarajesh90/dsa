package com.interviewprep.dsa.arrays.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumAbsoluteDifference {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int minDif = Integer.MAX_VALUE;
        for(int i=1; i<arr.length; i++){
            int curDif = arr[i] - arr[i-1];
            if(curDif < minDif){
                minDif = curDif;
                // result = new ArrayList<>();
                result.clear();
                result.add(Arrays.asList(arr[i-1],arr[i]));
            } else if(curDif == minDif){
                result.add(Arrays.asList(arr[i-1],arr[i]));
            }
        }
        return result;
    }
}
