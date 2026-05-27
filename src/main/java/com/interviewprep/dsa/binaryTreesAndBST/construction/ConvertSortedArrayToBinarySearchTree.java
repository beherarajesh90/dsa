package com.interviewprep.dsa.binaryTreesAndBST.construction;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length-1);
    }

    private TreeNode buildBST(int[] nums, int left, int right){
        if(left>right) return null;

        int mid = (left + right)/2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = buildBST(nums, left, mid-1);
        root.right = buildBST(nums, mid+1, right);

        return root;
    }
}
