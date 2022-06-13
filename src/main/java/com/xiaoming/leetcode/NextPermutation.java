package com.xiaoming.leetcode;

import com.xiaoming.utils.GsonUtils;

/**
 * @author xiaoming
 * @date 2019-11-04
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {

        if (nums.length <= 1) {
            return;
        }

        int index = -1;

        for (int i = nums.length - 1; i >= 1 ; i--) {
            if (nums[i] > nums[i-1]) {
                index = i - 1;
                break;
            }
        }

        if (index >= 0) {
            for (int i = nums.length - 1; i > index ; i--) {
                if (nums[i] > nums[index]) {
                    int tmp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = tmp;
                    break;
                }
            }

            for (int j = 0; nums.length - 1 - j > index + 1 + j; j++) {
                int tmp = nums[nums.length - 1 - j];
                nums[nums.length - 1 - j] = nums[index + 1 + j];
                nums[index + 1 + j] = tmp;
            }
        }else {
            for (int j = 0; nums.length - 1 - j > j; j++) {
                int tmp = nums[nums.length - 1 - j];
                nums[nums.length - 1 - j] = nums[j];
                nums[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();
        int[] ints = {1,3,2};
        nextPermutation.nextPermutation(ints);
        System.out.println(GsonUtils.getGson().toJson(ints));
    }
}
