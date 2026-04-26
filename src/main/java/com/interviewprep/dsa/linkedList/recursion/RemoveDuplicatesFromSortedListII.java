package com.interviewprep.dsa.linkedList.recursion;

import com.interviewprep.dsa.linkedList.ListNode;

//https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        if(head.val == head.next.val){
            int dup = head.val;
            while(head!=null && head.val==dup){
                head = head.next;
            }
            return deleteDuplicates(head);
        }
        head.next = deleteDuplicates(head.next);
        return head;
    }

    public ListNode deleteDuplicatesIterative(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                // Skip nodes whose values are duplicated
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // Connect prev with the node after the duplicates
                prev.next = head.next;
            } else {
                prev = prev.next;
            }
            head = head.next;
        }

        return dummy.next;
    }
}
