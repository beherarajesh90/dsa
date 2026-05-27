package com.interviewprep.dsa.binaryTreesAndBST.construction;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    int preIndex = 0;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {

        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }

        return build(preorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] preorder, int postStart, int postEnd) {

        if (postStart > postEnd)
            return null;

        // current root
        TreeNode root = new TreeNode(preorder[preIndex++]);

        // leaf node
        if (postStart == postEnd)
            return root;

        // next preorder element is left child
        int leftRoot = preorder[preIndex];

        // find left child in postorder
        int index = map.get(leftRoot);

        // build left subtree
        root.left = build(preorder, postStart, index);

        // build right subtree
        root.right = build(preorder, index + 1, postEnd - 1);

        return root;
    }
}
