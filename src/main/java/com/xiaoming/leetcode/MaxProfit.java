package com.xiaoming.leetcode;

import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-12-05
 */
public class MaxProfit {

    private int result = 0;

    private int preMin = 0;

    public int maxProfit(int[] prices) {

        if (Objects.isNull(prices) || prices.length <= 1) {
            return result;
        }

        preMin = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= preMin) {
                preMin = prices[i];
            }else {
                result = Math.max(result, prices[i] - preMin);
            }
        }
        return result;
    }
}
