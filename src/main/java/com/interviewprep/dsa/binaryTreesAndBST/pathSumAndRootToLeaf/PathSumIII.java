package com.interviewprep.dsa.binaryTreesAndBST.pathSumAndRootToLeaf;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/path-sum-iii/description/
public class PathSumIII {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L , 1);
        return pathSum(root, 0L, targetSum, prefix);
    }

    private int pathSum(TreeNode root, long curSum, int targetSum, Map<Long, Integer> prefixSum){
        if(root == null) return 0;

        curSum += root.val;
        int res = prefixSum.getOrDefault(curSum - targetSum, 0);

        prefixSum.put(curSum, prefixSum.getOrDefault(curSum, 0) + 1);
        res+=pathSum(root.left, curSum, targetSum, prefixSum);
        res+=pathSum(root.right, curSum, targetSum, prefixSum);
        prefixSum.put(curSum, prefixSum.get(curSum) - 1);

        return res;
    }
}
