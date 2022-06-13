package com.xiaoming.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaoming
 * @date 2019-12-08
 */
public class SingleNumber {

    private Set<Integer> set = new HashSet<>();

    public int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (set.contains(num)) {
                set.remove(num);
            }else {
                set.add(num);
            }
        }
        return set.iterator().next();
    }
}
