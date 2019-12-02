package com.xiaoming.leetcode;

import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-11-18
 */
public class MinPathSum {

    public int minPathSum(int[][] grid) {
        if (Objects.isNull(grid) || grid.length <= 0 || grid[0].length <= 0){
            return 0;
        }
        int[][] minPathArray =new int[grid.length][grid[0].length];
        minPathArray[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            minPathArray[i][0] = minPathArray[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            minPathArray[0][i] = minPathArray[0][i-1] + grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                minPathArray[i][j] = Math.min(minPathArray[i-1][j], minPathArray[i][j-1]) + grid[i][j];
            }
        }
        return minPathArray[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
        MinPathSum minPathSum = new MinPathSum();
        System.out.println(minPathSum.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }

}
