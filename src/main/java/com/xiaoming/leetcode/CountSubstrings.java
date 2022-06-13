package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2020-02-19
 */
public class CountSubstrings {

    private int count = 0;

    public int countSubstrings(String s) {
        if (null == s || s.length() <= 0) {
            return 0;
        }

        for (int i = 0; i < s.length(); i++) {
            int num = Math.min(s.length() - 1 - i, i - 0);
            for (int j = 0; j <= num; j++) {
                if (!isSubStrings(s, i-j, i+j)) {
                    break;
                }
                count++;
            }
        }
        return count;
    }

    boolean isSubStrings(String s, int start, int end) {
        int num = 0;
        while (end - start - 2 * num >= 0) {
            if (s.charAt(end - num) != s.charAt(start + num)) {
                return false;
            }
            num++;
        }
        return true;
    }

    public static void main(String[] args) {
        CountSubstrings countSubstrings = new CountSubstrings();
        System.out.println(countSubstrings.countSubstrings("aaa"));
    }
}
