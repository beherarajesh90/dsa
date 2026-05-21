package com.interviewprep.dsa.binaryTreesAndBST.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrderIterative(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int levelSize = q.size();

            for(int i=1; i<=levelSize; i++){
                TreeNode cur = q.poll();
                level.add(cur.val);

                if(cur.left != null) q.offer(cur.left);

                if(cur.right != null) q.offer(cur.right);
            }

            result.add(level);
        }
        return result;
    }

    //recursive
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        levelOrder(root, 0, result);
        return result;
    }

    private void levelOrder(TreeNode root, int depth, List<List<Integer>> result){
        if(root == null) return;

        if(result.size() == depth){
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            result.add(list);
        } else{
            result.get(depth).add(root.val);
        }

        levelOrder(root.left, depth+1, result);
        levelOrder(root.right, depth+1, result);
    }
}
