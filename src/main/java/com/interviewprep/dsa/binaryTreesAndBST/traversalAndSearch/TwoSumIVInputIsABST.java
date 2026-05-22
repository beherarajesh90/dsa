package com.interviewprep.dsa.binaryTreesAndBST.traversalAndSearch;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class TwoSumIVInputIsABST {

    public boolean findTarget(TreeNode root, int k) {
        return find(root, k, root);
    }

    private boolean find(TreeNode node, int k, TreeNode root) {
        if(node == null) return false;
        int target = k - node.val;
        if(node.val!=target && findK(root, target)){
            return true;
        }
        return find(node.left, k, root) || find(node.right, k, root);
    }

    private boolean findK(TreeNode root, int k){
        if(root == null) return false;
        if(root.val == k) return true;
        return k<root.val ? findK(root.left, k) : findK(root.right, k);
    }

    private Set<Integer> complements = new HashSet<>();

    //initial intuition
    public boolean findTarget2(TreeNode root, int k) {

        if(root == null) return false;

        boolean leftRes = findTarget(root.left, k);
        if(complements.contains(k - root.val)){
            return true;
        } else{
            complements.add(root.val);
        }
        boolean rightRes = findTarget(root.right, k);
        return leftRes || rightRes;
    }
}
