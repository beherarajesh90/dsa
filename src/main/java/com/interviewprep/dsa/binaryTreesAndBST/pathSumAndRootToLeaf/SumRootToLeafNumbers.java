package com.interviewprep.dsa.binaryTreesAndBST.pathSumAndRootToLeaf;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

public class SumRootToLeafNumbers {
    private int result = 0;

    public int sumNumbers(TreeNode root) {
        sumNumbers(root, 0);
        return result;
    }

    private void sumNumbers(TreeNode root, int curNum){
        if(root == null) return;

        curNum = (curNum*10) + root.val;
        sumNumbers(root.left, curNum);
        sumNumbers(root.right, curNum);
        if(root.left == null && root.right == null) result+=curNum;
        curNum/=10;
    }

    //without member variable
    public int sumNumbers2(TreeNode root) {
        return sumNumbers2(root, 0);
    }

    private int sumNumbers2(TreeNode root, int curNum){
        if(root == null) return 0;

        curNum = (curNum*10) + root.val;
        if(root.left == null && root.right == null) return curNum;

        return sumNumbers2(root.left, curNum) + sumNumbers2(root.right, curNum);
    }
}
