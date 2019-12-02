package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2019-11-17
 */
public class UniquePaths {
    private int count = 0;
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        count++;
        doNextPath(1, 1, m, n);
        return count;
    }

    private void doNextPath(int first, int second, int m, int n) {
        if (first >= m || second >= n) {
            return;
        }
        count++;
        doNextPath(first+1, second, m, n);
        doNextPath(first, second+1, m, n);
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3, 2));
    }
}
