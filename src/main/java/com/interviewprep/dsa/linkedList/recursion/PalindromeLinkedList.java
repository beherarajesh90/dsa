package com.interviewprep.dsa.linkedList.recursion;

import com.interviewprep.dsa.linkedList.ListNode;

public class PalindromeLinkedList {
    ListNode left = null;
    public boolean isPalindromeRecursive(ListNode head) {
        left = head;
        return check(head.next);
    }

    private boolean check(ListNode right){
        if(right == null) return true;

        boolean isPalindrome = check(right.next);

        if(!isPalindrome) return false;

        if(left.val != right.val) return false;

        left = left.next;
        return true;
    }

    public boolean isPalindromeIterative(ListNode head) {
        if (head == null || head.next == null) return true;

        // Find the end of first half
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        ListNode prev = null, curr = slow.next;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // Compare halves
        ListNode p1 = head, p2 = prev;
        boolean result = true;
        while (p2 != null) {
            if (p1.val != p2.val) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // Optional: Restore the list
        curr = prev;
        prev = null;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        slow.next = prev;

        return result;
    }
}
