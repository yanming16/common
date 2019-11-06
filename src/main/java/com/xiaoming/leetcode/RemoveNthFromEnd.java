package com.xiaoming.leetcode;

import com.xiaoming.leetcode.common.ListNode;
import com.xiaoming.utils.GsonUtils;

import java.util.Objects;

/**
 * @author liangyi
 * @Date 2019/10/30
 */
public class RemoveNthFromEnd {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode right = head;
        ListNode left = head;
        ListNode preLeft = head;
        for (int i = 1; i < n; i++) {
            right = right.next;
        }

        while (Objects.nonNull(right.next)){
            preLeft = left;
            left = left.next;
            right = right.next;
        }

        if (preLeft == left) {
            return head.next;
        }
        preLeft.next = left.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        System.out.println(GsonUtils.getGson().toJson(removeNthFromEnd(head, 1)));
    }
}
