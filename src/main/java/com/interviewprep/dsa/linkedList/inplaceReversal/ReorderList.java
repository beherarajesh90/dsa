package com.interviewprep.dsa.linkedList.inplaceReversal;

import com.interviewprep.dsa.linkedList.ListNode;

public class ReorderList {
    public void reorderList(ListNode head) {
        if(head == null|| head.next == null) return;
        // find the middle of the list
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second half [eg: 1,2,3,4,5  first: 1,2,3 second:4,5]
        ListNode cur = slow.next;
        ListNode prev = slow.next = null;
        while(cur!=null){
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        // merge first and second half
        ListNode cur1 = head, cur2 = prev;
        while(cur2!=null){
            ListNode next1 = cur1.next, next2 = cur2.next;

            cur1.next = cur2;
            cur2.next = next1;

            cur1 = next1;
            cur2 = next2;
        }
    }
}
