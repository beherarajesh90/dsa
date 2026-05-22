package com.interviewprep.dsa.binaryTreesAndBST.traversalAndSearch;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

//https://leetcode.com/problems/kth-smallest-element-in-a-bst/
public class KthSmallestElementInABST {
    private int count , result;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        result = 0;
        inorder(root);
        return result;
    }

    private void inorder(TreeNode root){
        if(root == null) return;

        inorder(root.left);

        if(count == 1) result = root.val;
        count--;

        if(count>0) inorder(root.right);
    }
}
