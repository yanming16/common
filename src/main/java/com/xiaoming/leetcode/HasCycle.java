package com.xiaoming.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-12-10
 */
public class HasCycle {

    private int pos = -1;

    private Map<ListNode, Integer> map = new HashMap<>();

    public boolean hasCycle(ListNode head) {
        if (Objects.isNull(head)) {
            return false;
        }
        map.put(head, 0);

        for (int i = 1; ; i++) {
            head = head.next;
            if (Objects.isNull(head)) {
                return false;
            }
            if (map.containsKey(head)) {
                pos = map.get(head);
                return true;
            }
            map.put(head, i);
        }
    }
}
