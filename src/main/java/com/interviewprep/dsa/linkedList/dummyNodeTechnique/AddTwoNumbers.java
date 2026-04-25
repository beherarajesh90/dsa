package com.interviewprep.dsa.linkedList.dummyNodeTechnique;

import com.interviewprep.dsa.linkedList.ListNode;

//https://leetcode.com/problems/add-two-numbers/description/
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;

        while(l1!=null || l2!=null || carry!=0){
            int v1 = l1!=null ? l1.val : 0;
            int v2 = l2!=null ? l2.val : 0;
            int sum = v1 + v2 + carry;
            carry = sum / 10;
            tail.next = new ListNode(sum%10);
            tail = tail.next;
            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;
        }

        return dummy.next;
    }
}
