package com.interviewprep.dsa.binaryTreesAndBST.construction;


import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private Map<Integer,Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for(int i=0; i<preorder.length; i++){
            inorderMap.put(inorder[i], i);
        }
        return build(preorder, inorder, 0, preorder.length-1, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight){
        if(preLeft > preRight || inLeft > inRight){
            return null;
        }

        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int inInd = inorderMap.get(rootVal);
        int leftSize = inInd - inLeft;
        root.left = build(preorder, inorder, preLeft+1, preLeft+leftSize, inLeft, inInd - 1);
        root.right = build(preorder, inorder, preLeft+leftSize+1, preRight, inInd+1, inRight);
        return root;
    }
}
