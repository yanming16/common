package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2019-11-18
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return n;
        }
        int[] maxCount = new int[n];
        maxCount[0] = 1;
        maxCount[1] = 2;
        for (int i = 2; i < n; i++) {
            maxCount[i] = maxCount[i-1] + maxCount[i-2];
        }
        return maxCount[n-1];
    }
}
