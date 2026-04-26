package com.interviewprep.dsa.linkedList.recursion;

import com.interviewprep.dsa.linkedList.ListNode;

public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next == null) return head;

        ListNode newHead = head.next;
        head.next = head.next.next;
        newHead.next = head;
        head.next = swapPairs(head.next);
        return newHead;
    }
}
