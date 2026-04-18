package com.interviewprep.dsa.recursion.divideAndConquer;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
public class CountOfSmallerNumbersAfterSelf {
    class Pair{
        int value;
        int index;
        Pair(int value, int index){
            this.value = value;
            this.index = index;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Integer[] result = new Integer[n];
        Arrays.fill(result, 0);
        Pair[] pairs = new Pair[n];
        for(int i=0; i<nums.length; i++){
            pairs[i] = new Pair(nums[i], i);
        }
        mergeSort(pairs, 0, nums.length, result);
        return Arrays.asList(result);
    }

    private void mergeSort(Pair[] pairs, int left, int right, Integer[] result){
        if(right - left <= 1) return;
        int mid = left + (right - left)/2;
        mergeSort(pairs, left, mid, result);
        mergeSort(pairs, mid, right, result);
        merge(pairs, left, mid, right, result);
    }

    private void merge(Pair[] pairs, int left, int mid, int right, Integer[] result){
        List<Pair> temp = new ArrayList<>();
        int i = left, j = mid, rightCount = 0;
        while(i < mid && j < right){
            if(pairs[j].value < pairs[i].value){
                rightCount++;
                temp.add(pairs[j]);
                j++;
            } else{
                result[pairs[i].index]+=rightCount;
                temp.add(pairs[i]);
                i++;
            }
        }

        while(i<mid){
            result[pairs[i].index]+=rightCount;
            temp.add(pairs[i]);
            i++;
        }

        while(j<right){
            temp.add(pairs[j]);
            j++;
        }

        for(int k = left, t = 0; k < right; k++, t++){
            pairs[k] = temp.get(t);
        }
    }

    public List<Integer> countSmallerUsingFenwickTree(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n: nums){
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        int delta = -min + 1;               //It is used to shift all numbers so they become positive and start from 1 (index ≥ 1)
        int[] tree = new int[max + 1 + delta];
        int[] res = new int[nums.length];
        for(int i=nums.length-1; i>=0; i--){
            int value = nums[i] + delta;
            res[i] = query(tree, value -1);
            add(tree, value);
        }
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return res[index];
            }

            @Override
            public int size() {
                return res.length;
            }
        };
    }

    private void add(int[] tree, int value){
        while (value < tree.length){
            tree[value]++;
            value+= value & -value;
        }
    }

    private int query(int[] tree, int value){
        int sum = 0;
        while (value > 0){
            sum+=tree[value];
            value-= value & -value;
        }
        return sum;
    }



    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf solution = new CountOfSmallerNumbersAfterSelf();
        int[] nums = {5, 2, 6, 1};
        List<Integer> result = solution.countSmaller(nums);
        System.out.println(result);
    }
}
