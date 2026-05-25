package com.interviewprep.dsa.binaryTreesAndBST.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode root, List<Integer> result){
        if(root == null) return;
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    //iterative
    public List<Integer> postOrderIterative(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root, lastVisited = null;

        while (!stack.isEmpty() || current!=null){
            while (current!=null){
                stack.push(current);
                current = current.left;
            }

            TreeNode peekNode = stack.peek();

            //if peek node has right and not processed
            if(peekNode.right!=null && lastVisited != peekNode.right){
                current = peekNode.right;
            } else {
                result.add(peekNode.val);
                lastVisited = stack.pop();
            }
        }

        return result;
    }
}
