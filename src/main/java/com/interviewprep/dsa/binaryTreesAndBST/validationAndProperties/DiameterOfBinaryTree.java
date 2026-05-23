package com.interviewprep.dsa.binaryTreesAndBST.validationAndProperties;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

//https://leetcode.com/problems/diameter-of-binary-tree/
public class DiameterOfBinaryTree {
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return maxDiameter;
    }

    private int height(TreeNode root){
        if(root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        if(left + right > maxDiameter){
            maxDiameter = left + right;
        }

        return 1 + Math.max(left, right);
    }
}
