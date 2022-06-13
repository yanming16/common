package com.xiaoming.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author xiaoming
 * @date 2019-12-15
 */
public class MinStack {

    private Stack<Integer> stack = new Stack<>();

    private List<Integer> list = new ArrayList<>();

    private boolean needSort = false;

    private int min = Integer.MAX_VALUE;

    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        list.add(x);
        min = Math.min(x, min);
    }

    public void pop() {
        int x = stack.pop();
        int i = list.indexOf(x);
        list.remove(i);
        if (x <= min){
            needSort = true;
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        if (needSort) {
            list.sort(Comparator.comparingInt(Integer::valueOf));
            if (list.size() > 0) {
                min = list.get(0);
            }else {
                min = Integer.MAX_VALUE;
            }
        }

        return min;
    }
}
