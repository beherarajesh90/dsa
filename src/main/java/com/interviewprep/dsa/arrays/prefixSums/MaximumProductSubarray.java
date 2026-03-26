package com.interviewprep.dsa.arrays.prefixSums;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int minProduct=nums[0], maxProduct=nums[0],res = nums[0];
        for(int i=1;i<nums.length; i++){
            int curNumWithMinProd = nums[i] * minProduct;
            int curNumWithMaxProd = nums[i] * maxProduct;
            minProduct = Math.min(nums[i], Math.min(curNumWithMinProd, curNumWithMaxProd));
            maxProduct = Math.max(nums[i], Math.max(curNumWithMinProd, curNumWithMaxProd));
            res = Math.max(res, maxProduct);
        }
        return res;
    }
}
