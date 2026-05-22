package com.interviewprep.dsa.binaryTreesAndBST.traversalAndSearch;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

public class SearchInABinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode cur = root;
        while(cur!=null){
            if(val == cur.val) return cur;
            else if(val < cur.val) cur = cur.left;
            else cur = cur.right;
        }
        return null;
    }

    public TreeNode searchBSTRecursive(TreeNode root, int val) {
        if(root == null) return root;
        TreeNode result;
        if(val == root.val){
            result = root;
        } else if(val < root.val){
            result = searchBST(root.left, val);
        } else{
            result = searchBST(root.right, val);
        }
        return result;
    }
}
