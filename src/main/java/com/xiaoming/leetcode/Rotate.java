package com.xiaoming.leetcode;

import com.xiaoming.utils.GsonUtils;

/**
 * @author xiaoming
 * @date 2019-11-12
 */
public class Rotate {
    public void rotate(int[][] matrix) {
        if (null == matrix) {
            return;
        }
        int length = matrix.length;
        int count = length / 2;
        for (int i = 0; i < count; i++) {
            cirul(matrix, i);
        }
    }

    private void cirul(int[][] matrix, int num) {
        int tmp1 = 0;
        int tmp2 = 0;
        int last = matrix.length - 2 * num - 1;
        for (int i = 0; i < last; i++) {
            int j = i + num;
            int length = matrix.length - num - 1;
            tmp1 = matrix[j][length];
            matrix[j][length] = matrix[num][j];
            tmp2 = matrix[length][length - j + num];
            matrix[length][length - j + num] = tmp1;
            tmp1 = matrix[length - j + num][num];
            matrix[length - j + num][num] = tmp2;
            matrix[num][j] = tmp1;
        }
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        int[][] matrix = new int[][]{{5, 1, 9,11},{2, 4, 8,10},{13, 3, 6, 7},{15,14,12,16}};
        System.out.println(GsonUtils.getGson().toJson(matrix));
        rotate.rotate(matrix);
        System.out.println(GsonUtils.getGson().toJson(matrix));
    }
}
