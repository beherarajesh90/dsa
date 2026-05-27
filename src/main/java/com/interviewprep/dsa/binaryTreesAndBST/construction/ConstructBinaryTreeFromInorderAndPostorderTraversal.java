package com.interviewprep.dsa.binaryTreesAndBST.construction;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    private Map<Integer, Integer> inorderMap;
    private int postInd;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        inorderMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }
        postInd = postorder.length-1;
        return build(0, inorder.length-1, postorder);
    }

    private TreeNode build(int inLeft, int inRight, int[] postorder){
        if(inLeft > inRight) return null;

        int rootVal = postorder[postInd--];
        TreeNode root = new TreeNode(rootVal);
        int index = inorderMap.get(rootVal);
        root.right = build(index+1, inRight, postorder);
        root.left = build(inLeft, index-1, postorder);
        return root;
    }
}
