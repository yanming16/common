package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2019-11-05
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int max = 0;
        int start = 0;
        int current = 0;
        int length = 0;
        while (current + 1 < s.length()) {
            if ('(' == s.charAt(current) && ')' == s.charAt(current+1)) {
                length += 2;
                current += 2;
            }else {
                if (length > max) {
                    max = length;
                }
                length = 0;
                start = start + length;
                current++;
            }
        }
        if (length > max) {
            max = length;
        }
        return max;
    }

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        System.out.println(longestValidParentheses.longestValidParentheses(")()())"));
    }
}
