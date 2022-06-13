package com.xiaoming.leetcode;

import com.xiaoming.utils.JacksonUtils;

/**
 * @author xiaoming
 * @date 2020-01-10
 */
public class CountBits {

    public int[] countBits(int num) {

        int[] results = new int[num+1];

        for (int i = 0; i < num + 1; i++) {
            int currentNum = i;
            int result = 0;
            while (currentNum > 0) {
                if (currentNum % 2 == 1) {
                    result++;
                }
                currentNum = currentNum/2;
            }
            results[i] = result;
        }
        return results;
    }

    public static void main(String[] args) {
        CountBits countBits = new CountBits();
        System.out.println(JacksonUtils.encode2String(countBits.countBits(5)));
    }
}
