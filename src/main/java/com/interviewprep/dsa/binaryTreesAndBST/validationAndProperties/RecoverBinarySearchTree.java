package com.interviewprep.dsa.binaryTreesAndBST.validationAndProperties;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

import java.util.Stack;

//https://leetcode.com/problems/recover-binary-search-tree/
public class RecoverBinarySearchTree {
    public void recoverTreeIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode first = null, second = null, prev = null;

        while(!stack.isEmpty() || cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(prev!=null && prev.val > cur.val){
                if(first == null) first = prev;
                second = cur;
            }
            prev = cur;
            cur = cur.right;
        }

        if(first!=null && second!=null){
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    //recursive
    private TreeNode prev = null, first = null, second = null;

    public void recoverTree(TreeNode root) {
        inorder(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void inorder(TreeNode root){
        if(root == null) return;

        inorder(root.left);

        if(first == null && prev!=null && prev.val > root.val){
            first = prev;
        }

        if(first!=null && prev.val > root.val){
            second = root;
        }

        prev = root;

        inorder(root.right);
    }
}
