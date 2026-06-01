package com.interviewprep.dsa.priorityQueues.mergeKLists;

import com.interviewprep.dsa.linkedList.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/merge-k-sorted-lists/description/
public class MergeKSortedLists {
    //heap approach
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> minHeap = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        for(ListNode list: lists){
            if(list!=null) minHeap.offer(list);
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(!minHeap.isEmpty()){
            ListNode node = minHeap.poll();
            cur.next = node;
            cur = cur.next;
            if(cur.next != null) minHeap.offer(cur.next);
        }

        return dummy.next;
    }

    //using recursive approach( little optimized )
    public ListNode mergeKLists2(ListNode[] lists) {
        //if list is null or empty return null
        if(lists == null || lists.length == 0) return null;

        return mergeHelper(lists, 0, lists.length-1);
    }

    private ListNode mergeHelper(ListNode[] lists, int start, int end){
        //one list then return list
        if(start == end) return lists[start];

        // two lists then merge
        //below is optional
        // if(start+1 == end) return mergeTwoLists(lists[start], lists[end]);

        int mid = start+(end-start)/2;
        ListNode list1 = mergeHelper(lists, start, mid);
        ListNode list2 = mergeHelper(lists, mid+1, end);

        return mergeTwoLists(list1, list2);
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2){
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while(list1!=null && list2!=null){
            if(list1.val < list2.val){
                tail.next = list1;
                list1 = list1.next;
            } else{
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        if(list1!=null){
            tail.next = list1;
        } else if(list2!=null){
            tail.next = list2;
        }

        return dummy.next;
    }
}
