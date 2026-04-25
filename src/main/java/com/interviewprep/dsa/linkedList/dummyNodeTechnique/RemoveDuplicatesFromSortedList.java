package com.interviewprep.dsa.linkedList.dummyNodeTechnique;

import com.interviewprep.dsa.linkedList.ListNode;

//https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicatesOptimized(ListNode head) {
        ListNode cur = head;

        while(cur!=null && cur.next!=null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next;
            } else{
                cur = cur.next;
            }
        }

        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(head!=null && head.next!=null){
            if(head.val != head.next.val){
                tail.next = new ListNode(head.val);
                tail = tail.next;
            }
            head = head.next;
        }
        tail.next = head;
        return dummy.next;
    }
}
