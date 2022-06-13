package com.xiaoming.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-12-11
 */
public class DetectCycle {

    private int pos = -1;

    private Map<ListNode, Integer> map = new HashMap<>();

    public ListNode detectCycle(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        map.put(head, 0);

        for (int i = 1; ; i++) {
            head = head.next;
            if (Objects.isNull(head)) {
                return null;
            }
            if (map.containsKey(head)) {
                return head;
            }
            map.put(head, i);
        }
    }
}
