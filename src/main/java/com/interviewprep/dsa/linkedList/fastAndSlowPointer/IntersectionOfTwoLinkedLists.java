package com.interviewprep.dsa.linkedList.fastAndSlowPointer;

import com.interviewprep.dsa.linkedList.ListNode;

public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNodeOptimized(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode curA = headA;
        ListNode curB = headB;

        //if one of the pointer reached null, point to other list head
        //if two lists do not intersect then both will become null there by breaking the while loop
        while(curA!=curB){
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }

        return curA;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        //find the lengths of both lists
        int len1 = findLength(headA);
        int len2 = findLength(headB);

        //move the larger list ahead len2-len1 times
        if(len1 > len2){
            for(int i=1; i<=len1-len2; i++){
                headA = headA.next;
            }
        } else if(len2 > len1){
            for(int i=1; i<=len2-len1; i++){
                headB = headB.next;
            }
        }

        //now compare
        while(headA != headB){
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    private int findLength(ListNode head){
        ListNode temp = head;
        int count = 0;
        while(temp!=null){
            count++;
            temp = temp.next;
        }
        return count;
    }
}
