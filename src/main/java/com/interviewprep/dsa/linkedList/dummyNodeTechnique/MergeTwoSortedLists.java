package com.interviewprep.dsa.linkedList.dummyNodeTechnique;

import com.interviewprep.dsa.linkedList.ListNode;

//https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(list1!=null && list2!=null){
            if(list1.val <= list2.val){
                tail.next = new ListNode(list1.val);
                list1 = list1.next;
            } else{
                tail.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            tail = tail.next;
        }
        tail.next = (list1!=null ? list1 : (list2));
        return dummy.next;
    }

    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        else if(list2 == null) return list1;
        if(list1.val < list2.val){
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else{
            list2.next = mergeTwoListsRecursive(list1, list2.next);
            return list2;
        }
    }
}
