package com.xiaoming.leetcode;

import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-12-18
 */
public class Rob {

    public int rob(int[] nums) {

        if (Objects.isNull(nums) || nums.length < 1) {
            return 0;
        }
        int[] maxArr = new int[nums.length + 1];
        maxArr[0] = 0;
        maxArr[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxArr[i+1] = Math.max(maxArr[i], maxArr[i-1] + nums[i]);
        }
        return maxArr[nums.length];
    }
}
