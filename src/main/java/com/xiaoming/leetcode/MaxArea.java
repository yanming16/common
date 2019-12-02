package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2019-10-27
 */
public class MaxArea {
    public static int solution(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; j > i; ) {
            if (height[i] > height[j]) {
                max = Math.max(height[j] * (j - i), max);
                j--;
            }else {
                max = Math.max(height[i] * (j - i), max);
                i++;
            }
        }
        return max;
    }
}
