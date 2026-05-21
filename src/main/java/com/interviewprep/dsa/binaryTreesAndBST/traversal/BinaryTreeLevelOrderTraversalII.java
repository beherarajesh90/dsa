package com.interviewprep.dsa.binaryTreesAndBST.traversal;

import java.util.*;

public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            List<Integer> levels = new ArrayList<>();
            int n = q.size();

            for(int i=1; i<=n; i++){
                TreeNode node = q.poll();
                if(node.left != null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
                levels.add(node.val);
            }

            result.add(levels);
        }

        Collections.reverse(result);
        return result;
    }
}
