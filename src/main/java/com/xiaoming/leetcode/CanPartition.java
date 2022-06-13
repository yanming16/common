package com.xiaoming.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaoming
 * @date 2020-01-16
 */
public class CanPartition {

    private Set<Integer> selectedSet = new HashSet<>();

    public boolean canPartition(int[] nums) {

        if (nums.length == 1) {
            return false;
        }

        int sum = 0;
        for (int node : nums) {
            sum = sum + node;
        }

        if (sum % 2 == 1) {
            return false;
        }

        int half = sum / 2;

        for (int node : nums) {
            if (node > half) {
                return false;
            }
        }
        return canPartition(half, nums);

    }

    public boolean canPartition(int sum, int[] nums) {
        if (sum == 0) {
            return true;
        }
        if (sum < 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            if (selectedSet.contains(i)) {
                continue;
            }
            selectedSet.add(i);
            if (canPartition(sum - nums[i], nums)) {
                return true;
            }
            selectedSet.remove(i);
        }
        return false;
    }

    public static void main(String[] args) {
        CanPartition canPartition = new CanPartition();
        System.out.println(canPartition.canPartition(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,97,95}));
    }
}
