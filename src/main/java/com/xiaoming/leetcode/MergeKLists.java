package com.xiaoming.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-11-03
 */
public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (Objects.isNull(lists) || lists.length < 1) {
            return null;
        }
        return merge(Arrays.asList(lists));
    }

    public ListNode merge(List<ListNode> listNodes) {
        if (listNodes.size() == 1) {
            return listNodes.get(0);
        }
        List<ListNode> listNodeList = new ArrayList<>();
        for (int i = 0; i < listNodes.size();) {
            if (i < listNodes.size()-1){
                listNodeList.add(mergeTwoLists(listNodes.get(i), listNodes.get(i+1)));
            }else {
                listNodeList.add(listNodes.get(i));
            }
            i = i + 2;
        }
        return merge(listNodeList);
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        while (null != l1 || null != l2) {
            if (null == l1) {
                current.next = l2;
                return head.next;
            }
            if (null == l2) {
                current.next = l1;
                return head.next;
            }
            if (l1.val > l2.val) {
                current.next = new ListNode(l2.val);
                current = current.next;
                l2 = l2.next;
            } else {
                current.next = new ListNode(l1.val);
                current = current.next;
                l1 = l1.next;
            }
        }
        return head.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
