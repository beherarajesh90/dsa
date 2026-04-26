package com.interviewprep.dsa.linkedList.inplaceReversal;

import com.interviewprep.dsa.linkedList.ListNode;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null || left == right) return head;
        //need dummy node if whole array needs to be reversed
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode leftPrev = dummy;

        // Move prev to the node before 'left'
        for(int pos=0; pos < left-1; pos++){
            leftPrev = leftPrev.next;
        }
        ListNode prev = null;
        ListNode cur = leftPrev.next;
        for(int i=0; i<=right-left; i++){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        leftPrev.next.next = cur;
        leftPrev.next = prev;
        return dummy.next;
    }
}
