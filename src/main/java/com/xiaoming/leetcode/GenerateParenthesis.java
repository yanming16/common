package com.xiaoming.leetcode;

import com.xiaoming.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoming
 * @date 2019-11-02
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        String s = "(";
        getNext(result, s, 1, 0, n);
        return result;
    }

    private void getNext(List<String> result, String s, int leftNum, int rightNum, int n) {

        if (s.length() >= 2 * n) {
            result.add(s);
            return;
        }
        if (leftNum < n) {
            if (leftNum > rightNum) {
                getNext(result, s + "(", leftNum + 1, rightNum, n);
                getNext(result, s + ")", leftNum, rightNum + 1, n);
            }else {
                getNext(result, s + "(", leftNum + 1, rightNum, n);
            }
        }else {
            getNext(result, s + ")", leftNum, rightNum + 1, n);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        System.out.println(GsonUtils.getGson().toJson(generateParenthesis.generateParenthesis(3)));
    }
}
