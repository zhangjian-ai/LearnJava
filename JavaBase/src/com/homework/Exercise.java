package com.homework;


import java.util.*;


class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode fast, slow, virtual;
        virtual = null;
        slow = pHead;
        fast = pHead.next;

        while (fast != null && fast.next != null){
            if (virtual != null){
                if (virtual == slow){
                    return virtual;
                }
                virtual = virtual.next;
            }else {
                if (fast == slow){
                    virtual = pHead;
                }
                fast = fast.next.next;
            }

            slow = slow.next;
        }

        return null;
    }
}
