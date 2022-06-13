package com.xiaoming.leetcode;

import com.xiaoming.utils.JacksonUtils;

/**
 * @author xiaoming
 * @date 2020-01-01
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                move(nums, i);
            }
        }
    }

    private void move(int[] nums, int i) {
        for (int j = i; j < nums.length - 1; j++) {
            swap(nums, j, j+1);
        }
    }

    private void swap(int[] nums, int j, int i) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    public static void main(String[] args) {
        MoveZeroes moveZeroes = new MoveZeroes();
        int[] ints = {0, 1, 0, 3, 12};
        moveZeroes.moveZeroes(ints);
        System.out.println(JacksonUtils.encode2String(ints));
    }
}
