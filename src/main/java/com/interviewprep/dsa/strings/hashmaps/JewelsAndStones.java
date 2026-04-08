package com.interviewprep.dsa.strings.hashmaps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/jewels-and-stones/
public class JewelsAndStones {
    public int numJewelsInStones(String jewels, String stones) {
        Map<Character, Integer> map = new HashMap<>();

        for(char ch: stones.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        int count = 0;
        for(char ch: jewels.toCharArray()){
            count+=map.getOrDefault(ch,0);
        }

        return count;
    }

    public int numJewelsInStonesUsingSetFasterThanMap(String jewels, String stones) {
        Set<Character> set = new HashSet<>();
        for(char ch: jewels.toCharArray()){
            set.add(ch);
        }
        int count = 0;
        for(char ch: stones.toCharArray()){
            if(set.contains(ch)) count++;
        }
        return count;
    }
}
