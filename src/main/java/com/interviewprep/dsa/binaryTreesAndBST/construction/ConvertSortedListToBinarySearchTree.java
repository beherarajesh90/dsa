package com.interviewprep.dsa.binaryTreesAndBST.construction;

import com.interviewprep.dsa.binaryTreesAndBST.traversal.TreeNode;
import com.interviewprep.dsa.linkedList.ListNode;

//https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/
public class ConvertSortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        return convert(head, null);
    }

    private TreeNode convert(ListNode start, ListNode end){
        if(start == end) return null;
        ListNode mid = findMiddle(start, end);
        TreeNode root = new TreeNode(mid.val);
        root.left = convert(start, mid);
        root.right = convert(mid.next, end);
        return root;
    }

    private ListNode findMiddle(ListNode start, ListNode end){
        ListNode slow = start;
        ListNode fast = start;
        while(fast!=end && fast.next!=end){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
