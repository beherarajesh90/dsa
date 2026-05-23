package com.interviewprep.dsa.binaryTreesAndBST.validationAndProperties;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

//https://leetcode.com/problems/binary-tree-tilt/
public class BinaryTreeTilt {
    private int totalTilt = 0;

    public int findTilt(TreeNode root) {
        postorder(root);
        return totalTilt;
    }

    private int postorder(TreeNode root){
        if(root == null) return 0;

        int left = postorder(root.left);
        int right = postorder(root.right);
        totalTilt += Math.abs(left - right);

        return root.val + left + right;
    }
}
