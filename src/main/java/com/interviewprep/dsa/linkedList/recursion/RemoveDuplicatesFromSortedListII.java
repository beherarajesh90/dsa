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
}
