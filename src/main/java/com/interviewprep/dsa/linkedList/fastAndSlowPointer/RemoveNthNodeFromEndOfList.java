package com.interviewprep.dsa.linkedList.fastAndSlowPointer;

//https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0, head);
        ListNode behind = dummy;
        ListNode ahead = dummy;

        //move the fast pointer to n+1 times
        for(int i=1; i<=n+1; i++){
            ahead = ahead.next;
        }

        //now move both the pointer at a time until fast is null
        while(ahead!=null){
            behind = behind.next;
            ahead = ahead.next;
        }

        ListNode node = behind.next;
        behind.next = node.next;
        node.next = null;
        //head = [1], n = 1, return dummy.next(null)
        return dummy.next;
    }
}
