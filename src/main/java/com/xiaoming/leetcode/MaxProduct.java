package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2019-12-15
 */
public class MaxProduct {

    private int maxValue = Integer.MIN_VALUE;

    private boolean hasZero = false;

    public int maxProduct(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }

        doMaxProduct(nums, 0, nums.length - 1);
        doMaxProduct2(nums, 0, nums.length - 1);
        return maxValue;

    }

    private void doMaxProduct2(int[] nums, int left, int right) {

        if (right < left) {
            return;
        }


        if (right == left) {
            maxValue = Math.max(maxValue, nums[left]);
        }

        int preNum = 1;

        for (int i = right; i >= left; i--) {
            if (nums[i] == 0) {
                doMaxProduct2(nums, left, i - 1);
            }
            preNum = preNum * nums[i];
            maxValue = Math.max(maxValue, preNum);
        }
    }

    private void doMaxProduct(int[] nums, int left, int right) {
        if (right < left) {
            return;
        }

        if (right == left) {
            maxValue = Math.max(maxValue, nums[left]);
        }

        int preNum = 1;

        for (int i = left; i <= right; i++) {
            if (nums[i] == 0) {
                doMaxProduct(nums, i+1, right);
            }
            preNum = preNum * nums[i];
            maxValue = Math.max(maxValue, preNum);
        }
    }
}
