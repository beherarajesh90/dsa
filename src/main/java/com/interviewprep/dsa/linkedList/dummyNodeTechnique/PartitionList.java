package com.interviewprep.dsa.linkedList.dummyNodeTechnique;

import com.interviewprep.dsa.linkedList.ListNode;

//https://leetcode.com/problems/partition-list/
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode cur1 = dummy1;
        ListNode cur2 = dummy2;

        while(head!=null){
            if(head.val < x){
                cur1.next = new ListNode(head.val);
                cur1 = cur1.next;
            } else{
                cur2.next = new ListNode(head.val);
                cur2 = cur2.next;
            }
            head = head.next;
        }

        cur1.next = dummy2.next;
        return dummy1.next;
    }
}
