package com.interviewprep.dsa.binaryTreesAndBST.traversal;

import java.util.*;

//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        boolean leftToRight = true;

        while(!q.isEmpty()){
            List<Integer> levels = new ArrayList<>();
            int n = q.size();
            for(int i=1; i<=n; i++){
                TreeNode node = q.poll();
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
                levels.add(node.val);
            }

            if(!leftToRight){
                Collections.reverse(levels);
            }

            result.add(levels);
            leftToRight = !leftToRight;
        }
        return result;
    }
}
