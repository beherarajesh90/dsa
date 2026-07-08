package com.interviewprep.dsa.trie.trieStringOperations;

//https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/description/
public class FindTheLengthOfTheLongestCommonPrefix {
    class TrieNode {
        TrieNode[] children = new TrieNode[10];
    }

    private TrieNode root = new TrieNode();

    public int longestCommonPrefix(int[] arr1, int[] arr2) {

        for (int num : arr1) {
            insert(String.valueOf(num));
        }

        int ans = 0;

        for (int num : arr2) {
            ans = Math.max(ans, search(String.valueOf(num)));
        }

        return ans;
    }

    private void insert(String s) {

        TrieNode curr = root;

        for (char c : s.toCharArray()) {
            int idx = c - '0';

            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }

            curr = curr.children[idx];
        }
    }

    private int search(String s) {

        TrieNode curr = root;
        int length = 0;

        for (char c : s.toCharArray()) {

            int idx = c - '0';

            if (curr.children[idx] == null) {
                break;
            }

            curr = curr.children[idx];
            length++;
        }

        return length;
    }
}
