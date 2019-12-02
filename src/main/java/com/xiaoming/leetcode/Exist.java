package com.xiaoming.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author xiaoming
 * @date 2019-11-24
 */
public class Exist {
    public boolean exist(char[][] board, String word) {
        if (Objects.isNull(board) || board.length <= 0) {
            return false;
        }
        if (Objects.isNull(word) || word.length() <= 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean result = existStart(board, i, j, word);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existStart(char[][] board, int i, int j, String word) {
        Map<Integer, Set<Integer>> hasExist = new HashMap<>();
        return nextExit(board, i, j, 0, word, hasExist);
    }

    private boolean nextExit(char[][] board, int i, int j, int k, String word, Map<Integer, Set<Integer>> hasExist) {
        if (k >= word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        if (word.charAt(k) != board[i][j] || (Objects.nonNull(hasExist.get(i)) && hasExist.get(i).contains(j))) {
            return false;
        }
        putIntoMap(i, j, hasExist);
        if (nextExit(board, i - 1, j, k + 1, word, hasExist) || nextExit(board, i + 1, j, k + 1, word, hasExist)
                || nextExit(board, i, j - 1, k + 1, word, hasExist) || nextExit(board, i, j + 1, k + 1, word, hasExist)) {
            return true;
        } else {
            hasExist.get(i).remove(j);
            return false;
        }
    }

    private void putIntoMap(int i, int j, Map<Integer, Set<Integer>> hasExist) {
        Set<Integer> integers = hasExist.get(i);
        if (Objects.isNull(integers)) {
            Set<Integer> set = new HashSet<>();
            set.add(j);
            hasExist.put(i, set);
        } else {
            integers.add(j);
        }
    }


    public static void main(String[] args) {
        Exist exist = new Exist();
        System.out.println(exist.exist(new char[][]{{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}}, "ABCB"));
    }


}
