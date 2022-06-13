package com.xiaoming.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaoming
 * @date 2019-12-16
 */
public class GetIntersectionNode {

    private Set<ListNode> set = new HashSet<>();

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        while (null != headA) {
            set.add(headA);
            headA = headA.next;
        }

        while (null != headB) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}
