package com.interviewprep.dsa.binaryTreesAndBST.construction;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
public class SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        serializeHelper(root, result);
        return result.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder result){
        if(root == null){
            result.append("#,");
            return;
        }
        result.append(root.val).append(",");
        serializeHelper(root.left, result);
        serializeHelper(root.right, result);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Index index = new Index();
        String[] arr = data.split(",");
        return deserializeHelper(arr, index);
    }

    private TreeNode deserializeHelper(String[] arr, Index index){
        if(index.val >= arr.length || arr[index.val].equals("#")){
            index.val++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(arr[index.val++]));
        root.left = deserializeHelper(arr, index);
        root.right = deserializeHelper(arr, index);
        return root;
    }

    private static class Index{
        int val = 0;
    }
}
