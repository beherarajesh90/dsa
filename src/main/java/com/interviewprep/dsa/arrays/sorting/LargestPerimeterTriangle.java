package com.interviewprep.dsa.arrays.sorting;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class LargestPerimeterTriangle {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i=nums.length-1; i>=2; i--){
            if(nums[i-1]+nums[i-2] > nums[i]){
                return nums[i] + nums[i-1] + nums[i-2];
            }
        }
        return 0;
    }
}
