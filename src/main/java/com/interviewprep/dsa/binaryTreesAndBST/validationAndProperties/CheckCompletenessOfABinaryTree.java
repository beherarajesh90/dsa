package com.interviewprep.dsa.binaryTreesAndBST.validationAndProperties;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/check-completeness-of-a-binary-tree/
public class CheckCompletenessOfABinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean end = false;

        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node == null){
                end = true;
            } else{
                if(end) return false;
                q.offer(node.left);
                q.offer(node.right);
            }
        }

        return true;
    }
}
