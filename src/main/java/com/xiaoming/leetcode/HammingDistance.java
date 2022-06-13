package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2020-01-19
 */
public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int i = x ^ y;
        int count = 0;
        while (i != 0) {
            if ((i & 1) == 1) {
                count++;
            }
            i = i >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance(1, 4));
    }
}
