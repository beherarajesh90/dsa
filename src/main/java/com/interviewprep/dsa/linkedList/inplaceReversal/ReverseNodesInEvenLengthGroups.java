package com.interviewprep.dsa.linkedList.inplaceReversal;

import com.interviewprep.dsa.linkedList.ListNode;

public class ReverseNodesInEvenLengthGroups {
    public ListNode reverseEvenLengthGroups(ListNode head) {
        if (head == null) return head;

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = head;
        int groupSize = 1;

        while (curr != null) {
            int count = 0;
            ListNode groupHead = curr;
            ListNode temp = curr;
            // Count nodes in the current group
            while (temp != null && count < groupSize) {
                count++;
                temp = temp.next;
            }

            if (count % 2 == 0) {
                // Reverse the group
                ListNode prevNode = null;
                ListNode node = curr;
                for (int i = 0; i < count; i++) {
                    ListNode nextNode = node.next;
                    node.next = prevNode;
                    prevNode = node;
                    node = nextNode;
                }
                // Connect the previous part with the reversed group
                prev.next = prevNode;
                // Connect end of reversed group with the next section
                groupHead.next = temp;
                // Update prev to the tail of the reversed group
                prev = groupHead;
                curr = temp;
            } else {
                // No reversal: move prev pointer to the end of group
                for (int i = 0; i < count; i++) {
                    prev = curr;
                    curr = curr.next;
                }
            }
            groupSize++;
        }
        return dummy.next;
    }
}
