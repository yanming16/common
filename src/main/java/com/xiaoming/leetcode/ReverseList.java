package com.xiaoming.leetcode;

import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-12-20
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {

        return rever(null, head);
    }

    private ListNode rever(ListNode preNode, ListNode head) {
        if (Objects.isNull(head)) {
            return preNode;
        }
        ListNode currentNode = head;
        head = head.next;
        currentNode.next = preNode;
        return rever(currentNode, head);
    }
}
