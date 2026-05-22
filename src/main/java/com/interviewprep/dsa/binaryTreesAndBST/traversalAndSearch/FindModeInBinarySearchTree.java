package com.interviewprep.dsa.binaryTreesAndBST.traversalAndSearch;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/find-mode-in-binary-search-tree/
public class FindModeInBinarySearchTree {

    private Integer prev = null;
    private int count = 0;
    private int maxCount = 0;
    private List<Integer> modes = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        inorder(root);
        int[] result = new int[modes.size()];
        for(int i=0; i<result.length; i++){
            result[i] = modes.get(i);
        }
        return result;
    }

    private void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);

        if(prev!=null && prev == root.val){
            count++;
        } else{
            count = 1;
        }

        if(maxCount < count){
            maxCount = count;
            modes.clear();
            modes.add(root.val);
        } else if(maxCount == count){
            modes.add(root.val);
        }

        prev = root.val;

        inorder(root.right);
    }
}
