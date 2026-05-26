package com.interviewprep.dsa.binaryTreesAndBST.pathSumAndRootToLeaf;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

//https://leetcode.com/problems/path-sum/description/
public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasPathSum(root, 0, targetSum);
    }

    private boolean hasPathSum(TreeNode root, int curSum, int targetSum){
        if(root == null) return false;

        curSum += root.val;
        if(targetSum == curSum && root.left == null && root.right == null) return true;

        // optimized way to write the code
        // if(root.left == null && root.right == null) return targetSum == curSum;

        return hasPathSum(root.left, curSum, targetSum) || hasPathSum(root.right, curSum, targetSum);
    }
}
