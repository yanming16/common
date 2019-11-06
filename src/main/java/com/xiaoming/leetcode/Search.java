package com.xiaoming.leetcode;

import java.util.Objects;

/**
 * @author liangyi
 * @Date 2019/11/6
 */
public class Search {
    public int search(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length <= 0) {
            return -1;
        }
        return search(nums, target, 0, nums.length - 1);
    }

    private int search(final int[] nums, final int target, final int start, final int end) {

        if (start == end) {
            return nums[start] == target ? start : -1;
        }

        int cutCount = (end - start + 1) / 2;

        int leftResult = -1;
        int rightResult = -1;
        int leftEnd = start + cutCount - 1;
        int rightStart = start + cutCount;

        if (nums[leftEnd] > nums[start]) {
            if (target >= nums[start] && target <= nums[leftEnd]) {
                leftResult = search(nums, target, start, leftEnd);
            }
        }else {
            if (target >= nums[start] || target <= nums[leftEnd]) {
                leftResult = search(nums, target, start, leftEnd);
            }
        }

        if (nums[end] > nums[rightStart]) {
            if (target >= nums[rightStart] && target <= nums[end]) {
                rightResult = search(nums, target, rightStart, end);
            }
        }else {
            if (target >= nums[rightStart] || target <= nums[end]) {
                rightResult = search(nums, target, rightStart, end);
            }
        }
        return Math.max(leftResult, rightResult);

    }

    public static void main(String[] args) {
        Search search = new Search();
        System.out.println(search.search(new int[]{ 4, 5, 6, 7, 0, 1, 2 }, 6));
    }


}
