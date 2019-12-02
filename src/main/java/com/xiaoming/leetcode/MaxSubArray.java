package com.xiaoming.leetcode;

import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-11-14
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {

        if (Objects.isNull(nums) || nums.length <= 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int max = nums[0];
        int lastSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (lastSum > max) {
                max = lastSum;
            }

            if (lastSum > 0) {
                lastSum = lastSum + nums[i];
            }else {
                lastSum = nums[i];
            }

        }
        if (lastSum > max) {
            max = lastSum;
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        System.out.println(maxSubArray.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
