package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2020-01-07
 */
public class MaxProfit2 {

    public int maxProfit(int[] prices) {
        int sold = 0, rest = 0, hold = Integer.MIN_VALUE;
        for (int p : prices) {
            int pre_sold = sold;
            sold = hold + p;
            hold = Math.max(hold, rest - p);
            rest = Math.max(rest, pre_sold);
        }
        return Math.max(sold, rest);
    }
}
