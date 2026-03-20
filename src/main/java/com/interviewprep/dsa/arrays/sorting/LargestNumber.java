package com.interviewprep.dsa.arrays.sorting;

import java.util.Arrays;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        // 1. Convert integers to Strings for easier concatenation/comparison
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // 2. Sort using custom comparator
        Arrays.sort(asStrs, (a, b) -> {
            String order1 = a + b;
            String order2 = b + a;
            // We want descending order, so compare order2 to order1
            return order2.compareTo(order1);
        });

        // 3. Handle edge case: if the largest number is "0", the result is "0"
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // 4. Build the final string
        StringBuilder sb = new StringBuilder();
        for (String s : asStrs) {
            sb.append(s);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
//        int[] nums = {3,30,34,5,9};
        int[] nums = {0,0};
        LargestNumber largestNumber = new LargestNumber();
        System.out.println(largestNumber.largestNumber(nums));
    }
}
