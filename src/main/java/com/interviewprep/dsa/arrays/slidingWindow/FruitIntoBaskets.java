package com.interviewprep.dsa.arrays.slidingWindow;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/fruit-into-baskets/description/
public class FruitIntoBaskets {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> fruitCount = new HashMap<>();
        int left = 0, maxFruits = 0, n = fruits.length;
        for (int right=0; right<n; right++){
            fruitCount.put(fruits[right],fruitCount.getOrDefault(fruits[right],0)+1);
            while (fruitCount.size()>2){
                fruitCount.put(fruits[left],fruitCount.get(fruits[left])-1);
                if(fruitCount.get(fruits[left]) == 0){
                    fruitCount.remove(fruits[left]);
                }
                left++;
            }
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        return maxFruits;
    }

    public int totalFruitOptimized(int[] fruits) {
        int[] freq = new int[100001];
        int left=0, maxFruits = 0, unique=0;
        for(int right=0; right<fruits.length; right++){
            if(freq[fruits[right]] == 0) unique++;
            freq[fruits[right]]++;
            while(unique>2){
                freq[fruits[left]]--;
                if(freq[fruits[left]] == 0) unique--;
                left++;
            }
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        return maxFruits;
    }
}
