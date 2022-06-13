package com.xiaoming.leetcode;

import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-12-29
 */
public class ProductExceptSelf {

    private int total = 1;

    private int zeroNum = 0;

    public int[] productExceptSelf(int[] nums) {
        if (Objects.isNull(nums) || nums.length <= 0) {
            return null;
        }

        for (int num : nums) {
            if (num != 0) {
                total = total * num;
            }else {
                zeroNum++;
            }
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (zeroNum > 1) {
                nums[i] = 0;
                continue;
            }

            if (zeroNum == 1 && nums[i] == 0) {
                result[i] = total;
                continue;
            }

            if (zeroNum == 1) {
                result[i] = 0;
                continue;
            }

            result[i] = total / nums[i];

        }
        return result;
    }
}
