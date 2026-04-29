package com.interviewprep.dsa.linkedList.recursion;

import com.interviewprep.dsa.linkedList.Node;

public class FlattenAMultilevelDoublyLinkedList {
    public Node flatten(Node head) {
        if(head == null) return head;
        Node cur = head;
        while(cur!=null){

            //if node has child, iterate throught the child
            if(cur.child!=null){
                //find child head
                Node next = cur.next;
                Node childHead = cur.child;
                cur.next = childHead;
                cur.child = null;
                childHead.prev = cur;

                //find child tail
                Node childTail = cur;
                while(childTail.next!=null){
                    childTail = childTail.next;
                }

                if(next!=null){
                    childTail.next = next;
                    next.prev = childTail;
                }
            } else{
                cur = cur.next;
            }
        }
        return head;
    }

    public Node flattenRecursive(Node head) {

        if(head == null) return head;

        if(head.child!=null){
            Node temp = head.next;
            Node childHead = flattenRecursive(head.child);
            head.next = childHead;
            head.child = null;
            childHead.prev = head;

            Node cur = childHead;
            while(cur.next!=null){
                cur = cur.next;
            }

            if(temp!=null){
                cur.next = temp;
                temp.prev = cur;
            }

        } else{
            head.next = flattenRecursive(head.next);
        }

        return head;
    }
}
