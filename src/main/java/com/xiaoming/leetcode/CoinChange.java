package com.xiaoming.leetcode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author xiaoming
 * @date 2020-01-09
 */
public class CoinChange {

    private int number = 0;

    private Set<Integer> set = new HashSet<>();

    public int coinChange(int[] coins, int amount) {
        if (amount <= 0 || Objects.isNull(coins) || coins.length <= 0) {
            return -1;
        }

        Set<Integer> amountSet = new HashSet<>();
        amountSet.add(amount);

        while (!amountSet.isEmpty()) {
            number++;
            Set<Integer> nextSet = new HashSet<>();
            for (int currentAmount:amountSet) {
                for (int coin:coins) {
                    int nextAmount = currentAmount - coin;
                    if (nextAmount > 0 && !set.contains(nextAmount)) {
                        set.add(nextAmount);
                        nextSet.add(nextAmount);
                    }
                    if (nextAmount == 0) {
                        return number;
                    }
                }
            }
            amountSet = nextSet;
        }
        return -1;
    }
}
