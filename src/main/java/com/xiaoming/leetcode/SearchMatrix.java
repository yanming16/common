package com.xiaoming.leetcode;

import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-12-30
 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        if (Objects.isNull(matrix) || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }

        int row = matrix.length;
        int line = matrix[0].length;

        int currentRow = 0;
        int currentLine = line-1;

        while (true) {
            if (currentLine < 0 || currentRow >= row) {
                return false;
            }

            if (target > matrix[currentRow][currentLine]) {
                currentRow++;
            }else if (target < matrix[currentRow][currentLine]) {
                currentLine--;
            }else {
                return true;
            }

        }
    }
}
