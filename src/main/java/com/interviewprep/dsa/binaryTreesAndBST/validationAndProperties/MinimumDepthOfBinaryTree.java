package com.interviewprep.dsa.binaryTreesAndBST.validationAndProperties;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/minimum-depth-of-binary-tree/
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {

        if(root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;

        while(!q.isEmpty()){
            int n = q.size();
            for(int i=1; i<=n; i++){
                TreeNode node = q.poll();
                if(node.left == null && node.right == null){
                    return depth;
                }
                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null){
                    q.offer(node.right);
                }
            }
            depth++;
        }

        return 0;
    }
}
