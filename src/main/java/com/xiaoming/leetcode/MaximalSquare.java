package com.xiaoming.leetcode;

import com.xiaoming.utils.JacksonUtils;

import java.util.Objects;

/**
 * @author liangyi
 * @Date 2019/12/25
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (Objects.isNull(matrix)) {
            return 0;
        }
        int row = matrix.length;
        if (row < 1) {
            return 0;
        }
        int line = matrix[0].length;
        int max = 0;
        int[][] dp = new int[row][line];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < line; j++) {
                dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < row; j++) {
                dp[j][i] = matrix[j][i] == '1' ? 1 : 0;
                if (dp[j][i] > max) {
                    max = dp[i][j];
                }
            }
        }
        System.out.println(JacksonUtils.encode2String(dp));
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < line; j++) {
                dp[i][j] = matrix[i][j] == 1 ? Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1 : 0;
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        System.out.println(JacksonUtils.encode2String(dp));
        return max * max;
    }

    public static void main(String[] args) {
        MaximalSquare maximalSquare = new MaximalSquare();
        maximalSquare.maximalSquare(new char[][]{{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','1','1','1'},
                {'1','0','0','1','0'}});
    }
}
