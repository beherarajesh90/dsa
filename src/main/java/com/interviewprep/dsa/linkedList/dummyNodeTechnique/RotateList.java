package com.interviewprep.dsa.linkedList.dummyNodeTechnique;

import com.interviewprep.dsa.linkedList.ListNode;

//https://leetcode.com/problems/rotate-list/
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {

        if(head == null || head.next == null || k==0) return head;

        ListNode cur = head;
        int len = 1;
        while(cur.next!=null){
            cur = cur.next;
            len++;
        }
        cur.next = head;
        k = k % len;
        int rotate = len - k;
        for(int i=1; i<=rotate; i++){
            cur = cur.next;
        }
        ListNode newHead = cur.next;
        cur.next = null;
        return newHead;
    }

    public ListNode rotateRight2(ListNode head, int k) {
        if(head == null || head.next == null || k==0) return head;
        ListNode slow = head;
        ListNode fast = head;

        int len = 0;
        while(fast!=null){
            len++;
            fast = fast.next;
        }

        fast = slow;
        int rotate = k%len;
        if(rotate == 0) return head;

        for(int i=1; i<=rotate; i++){
            fast = fast.next;
        }

        while(rotate!=0 && fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }
}
