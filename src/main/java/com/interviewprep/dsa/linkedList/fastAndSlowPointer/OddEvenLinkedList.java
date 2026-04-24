package com.interviewprep.dsa.linkedList.fastAndSlowPointer;

//https://leetcode.com/problems/odd-even-linked-list/description/
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode oddPointer = head;
        ListNode evenHead = head.next;
        ListNode evenPointer = evenHead;

        while(evenPointer!=null && evenPointer.next!=null){
            oddPointer.next = evenPointer.next;
            oddPointer = oddPointer.next;

            evenPointer.next = oddPointer.next;
            evenPointer = evenPointer.next;
        }

        oddPointer.next = evenHead;
        return head;
    }
}
