package com.interviewprep.dsa.trie.bitOperations;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
public class MaximumXOROfTwoNumbersInAnArray {
    private TrieNode root = new TrieNode();

    class TrieNode{
        TrieNode[] children = new TrieNode[2];
    }

    public int findMaximumXOR(int[] nums) {
        // return findMaximumXORUsingHashSet(nums);

        // return findMaximumXORUsingHashSetOptimized(nums);

        return findMaximumXORUsingTrie(nums);

    }

    // using trie
    private int findMaximumXORUsingTrie(int[] nums) {
        for(int num: nums){
            insert(num);
        }

        int maxXor = 0;
        for(int num: nums){
            maxXor = Math.max(maxXor, search(num));
        }
        return maxXor;
    }

    private int search(int num){
        TrieNode node = root;
        int xor = 0;
        for(int i=30; i>=0; i--){
            int bit = (num >> i) & 1;
            int opposite = 1 - bit;
            if(node.children[opposite] != null){
                xor = xor | (1 << i);   // this bit of xor becomes 1
                node = node.children[opposite];
            } else{
                node = node.children[bit];
            }
        }
        return xor;
    }

    private void insert(int num){
        TrieNode node = root;
        for(int i=30; i>=0; i--){
            int bit = (num >> i) & 1;
            if(node.children[bit] == null){
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
        }
    }

    // optimal
    private int findMaximumXORUsingHashSetOptimized(int[] nums){
        int maxNum = nums[0];
        for(int i=1; i<nums.length; i++){
            maxNum = Math.max(maxNum, nums[i]);
        }
        int mask = Integer.highestOneBit(maxNum);
        int maxXor = 0, prefixMask = 0;
        while (mask > 0){
            prefixMask |= mask;
            int candidate = maxXor | mask;
            if(canXor(nums, prefixMask, candidate)){
                maxXor = candidate;
            }
            mask = mask >> 1;
        }
        return maxXor;
    }

    private boolean canXor(int[] nums, int prefixMask, int candidate){

        Set<Integer> seen = new HashSet<>();
        for(int num: nums){
            int prefix = prefixMask & num;
            if(seen.contains(prefix ^ candidate)) return true;
            seen.add(prefix);
        }
        return false;
    }

    private int findMaximumXORUsingHashSet(int[] nums){

        int maxNum = nums[0];
        for(int i=1; i<nums.length; i++){
            maxNum = Math.max(maxNum, nums[i]);
        }

        int maxXor = 0;
        for(int k=30; k>=0; k--){

            int candidate = maxXor | (1 << k);
            int mask = candidate;

            Set<Integer> prefixes = new HashSet<>();
            for(int num: nums){
                prefixes.add(mask & num);
            }


            for(int prefix: prefixes){
                if(prefixes.contains( prefix ^ candidate)){
                    maxXor = candidate;
                    break;
                }
            }
        }
        return maxXor;
    }
}
