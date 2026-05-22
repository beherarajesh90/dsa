package com.interviewprep.dsa.binaryTreesAndBST.traversalAndSearch;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

//https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
public class LowestCommonAncestorOfDeepestLeaves {

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode root){
        if(root == null) return new Result(null, 0);
        Result leftResult = dfs(root.left);
        Result rightResult = dfs(root.right);
        if(leftResult.depth > rightResult.depth){
            return new Result(leftResult.node, leftResult.depth + 1);
        } else if(rightResult.depth > leftResult.depth){
            return new Result(rightResult.node, rightResult.depth + 1);
        } else{
            return new Result(root, leftResult.depth+1);
        }
    }
}

class Result{
    TreeNode node;
    int depth;

    public Result(TreeNode node, int depth){
        this.node = node;
        this.depth = depth;
    }
}
