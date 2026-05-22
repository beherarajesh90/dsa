package com.interviewprep.dsa.binaryTreesAndBST.traversalAndSearch;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LowestCommonAncestorOfABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Case 1: both nodes are smaller
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // Case 2: both nodes are greater
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // Split point => current node is LCA
        return root;
    }

    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while(cur!=null){
            if(p.val < cur.val && q.val < cur.val){
                cur = cur.left;
            } else if(p.val > cur.val && q.val > cur.val){
                cur = cur.right;
            } else{
                return cur;
            }
        }
        return null;
    }
}
