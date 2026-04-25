package com.interviewprep.dsa.linkedList.fastAndSlowPointer;

import com.interviewprep.dsa.linkedList.ListNode;

//https://leetcode.com/problems/linked-list-cycle-ii/
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        //iterate the pointers until they meet to detect cycle
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }

        //no cycle detected so return null
        if(fast==null || fast.next==null) return null;

        //reset slow to head and move both pointers ahead until they meet
        slow = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }
}
