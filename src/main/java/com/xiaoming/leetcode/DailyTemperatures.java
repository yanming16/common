package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2020-02-20
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            int count = 0;
            int num = T[i];
            for (int j = i+1; j < T.length; j++) {
                if (T[j] > num) {
                    count = j - i;
                    break;
                }
            }
            result[i] = count;
        }
        return result;
    }
}
