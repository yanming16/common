package com.xiaoming.leetcode;

import com.xiaoming.utils.GsonUtils;

import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-11-21
 */
public class SortColors {
    private int b = -1;
    private int c = -1;
    public void sortColors(int[] nums) {
        if (Objects.isNull(nums) || nums.length <= 1) {
            return;
        }
        if (nums[0] == 1) {
            b = 0;
        }else if (nums[0] == 2){
            c = 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (b == -1) {
                    if (c != -1) {
                        swap(nums, i);
                    }
                }else {
                    int tmp = nums[b];
                    nums[b] = nums[i];
                    nums[i] = tmp;
                    b++;
                    if (c != -1) {
                        swap(nums, i);
                    }
                }
            }else if (nums[i] == 1) {
                if (c != -1) {
                    if (b == -1) {
                        b = c;
                    }
                    swap(nums, i);
                }else if (b == -1) {
                    b = i;
                }
            }else {
                if (c == -1) {
                    c = i;
                }
            }
        }

    }

    private void swap(int[] nums, int i) {
        int tmp = nums[c];
        nums[c] = nums[i];
        nums[i] = tmp;
        c++;
    }

    public static void main(String[] args) {
        SortColors sortColors = new SortColors();
        int[] nums = new int[]{0,2,1};
        sortColors.sortColors(nums);
        System.out.println(GsonUtils.getGson().toJson(nums));
    }


}
