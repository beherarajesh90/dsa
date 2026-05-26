package com.interviewprep.dsa.binaryTreesAndBST.pathSumAndRootToLeaf;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

//https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
public class BinaryTreeMaximumPathSum {
    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // recursively get the maximum gain from left and right subtrees, ignoring negatives
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // current path price
        int curSum = node.val + leftGain + rightGain;

        // update global max sum if the new path is better
        maxSum = Math.max(maxSum, curSum);

        // return the maximum gain including the current node to the parent's computation
        return node.val + Math.max(leftGain, rightGain);
    }
}
