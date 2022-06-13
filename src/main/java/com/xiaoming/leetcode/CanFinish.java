package com.xiaoming.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

/**
 * @author xiaoming
 * @date 2019-12-22
 */
public class CanFinish {

    private Map<Integer, Set<Integer>> map = new HashMap<>();

    private Stack<Integer> stack = new Stack<>();

    private Set<Integer> set = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        collectInMap(prerequisites);
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            if (!check(key)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(int numCourses) {
        Set<Integer> requests = map.get(numCourses);
        if (Objects.isNull(requests) || requests.size() <= 0) {
            return true;
        }
        for (Integer request : requests) {
            if (set.contains(request)) {
                return false;
            }
            stack.push(request);
            set.add(request);
            Integer pop = stack.pop();
            if (!check(pop)) {
                return false;
            }
            set.remove(request);
        }
        return true;
    }

    private void collectInMap(int[][] prerequisites) {
        for (int[] entry : prerequisites) {
            int key = entry[0];
            int value = entry[1];
            Set<Integer> set = map.get(key);
            if (Objects.isNull(set)) {
                set = new HashSet<>();
            }
            set.add(value);
            map.put(key, set);
        }
    }
}
