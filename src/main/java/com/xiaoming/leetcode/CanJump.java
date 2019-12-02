package com.xiaoming.leetcode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author xiaoming
 * @date 2019-11-16
 */
public class CanJump {

    private Set<Integer> hasDone = new HashSet<>();

    public boolean canJump(int[] nums) {
        if (Objects.isNull(nums) || nums.length <= 0) {
            return false;
        }
        return canDoJump(0, nums);
    }

    private boolean canDoJump(int start, int[] nums) {
        if (hasDone.contains(start)) {
            return false;
        }
        hasDone.add(start);
        int maxJump = Math.min(nums.length - 1 - start, nums[start]);
        if (start + maxJump >= nums.length - 1) {
            return true;
        }
        for (int i = 1; i <= maxJump; i++) {
            if (canDoJump(start + i, nums)) {
                return true;
            }
        }
        return false;
    }
}
