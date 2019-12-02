package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2019-10-25
 */
public class LongestPalindrome {

    public static String solution(String s) {
        if (s.length() == 0) {
            return "";
        }
        if (s.length() == 1){
            return s;
        }
        int currentIndex = 0;
        boolean isTwo = true;
        int maxNum = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            int twoMax = getTwo(chars, i, i + 1);
            int oneMax = getOne(chars, i);
            if (twoMax > oneMax && twoMax > maxNum) {
                isTwo = true;
                maxNum = twoMax;
                currentIndex = i;
            }
            if (oneMax > twoMax && oneMax > maxNum) {
                isTwo = false;
                maxNum = oneMax;
                currentIndex = i;
            }
        }

        if (maxNum == 1) {
            return s.substring(currentIndex, 1);
        }

        if (isTwo) {
            int two = maxNum/2;
            return s.substring(currentIndex - (two - 1), currentIndex + two + 1);
        }
        int one = (maxNum - 1)/2;
        return s.substring(currentIndex - one, currentIndex + one + 1);

    }

    private static int getOne(char[] chars, int i) {

        int num = 1;
        int count = 1;
        int left = i - 1;
        int right = i + 1;
        while (isEquals(chars, left, right)) {
            num = 2 * count + 1;
            count++;
            left--;
            right++;
        }
        return num;
    }

    private static boolean isEquals(char[] chars, int left, int right) {
        return left >= 0 && right < chars.length && chars[left] == chars[right];
    }

    private static int getTwo(char[] chars, int left, int right) {
        int num = 0;
        while (isEquals(chars, left, right)) {
            num = num + 2;
            left = left - 1;
            right = right + 1;
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(solution("a"));
    }
}
