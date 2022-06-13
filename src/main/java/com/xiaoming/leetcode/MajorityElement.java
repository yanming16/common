package com.xiaoming.leetcode;

import java.util.Arrays;

/**
 * @author xiaoming
 * @date 2019-12-17
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
