package com.interviewprep.dsa.binaryTreesAndBST.pathSumAndRootToLeaf;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/path-sum-ii/
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum(root, targetSum, result, new ArrayList<>());
        return result;
    }

    private void pathSum(TreeNode root, int remainingSum, List<List<Integer>> result, List<Integer> curPath){
        if(root == null) return;

        curPath.add(root.val);

        pathSum(root.left, remainingSum - root.val, result, curPath);
        pathSum(root.right, remainingSum - root.val, result, curPath);

        //if leaf node check  if remaining sum is equal to leaf node
        if(root.val == remainingSum && root.left == null && root.right == null){
            result.add(new ArrayList<>(curPath));
        }
        curPath.removeLast();
    }
}
