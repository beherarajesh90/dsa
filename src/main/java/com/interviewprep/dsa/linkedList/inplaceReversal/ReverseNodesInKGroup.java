package com.interviewprep.dsa.linkedList.inplaceReversal;

import com.interviewprep.dsa.linkedList.ListNode;

//https://leetcode.com/problems/reverse-nodes-in-k-group/description/
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;
        while(true){
            ListNode kth = getKth(groupPrev, k);
            if(kth == null) break;

            ListNode groupNext = kth.next;
            ListNode prev = kth.next;
            ListNode cur = groupPrev.next;
            while(cur!=groupNext){
                ListNode temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
            }
            ListNode temp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = temp;
        }
        return dummy.next;
    }

    private ListNode getKth(ListNode cur, int k){
        while(cur!=null && k>0){
            cur = cur.next;
            k--;
        }
        return cur;
    }
}
