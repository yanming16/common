package com.xiaoming.leetcode;

import java.util.Objects;
import java.util.Stack;

/**
 * @author xiaoming
 * @date 2020-01-13
 */
public class DecodeString {

    private Stack<Character> stack = new Stack<>();

    public String decodeString(String s) {
        if (Objects.isNull(s) || s.length() <= 1) {
            return s;
        }

        for (char c : s.toCharArray()) {
            if (c == ']') {
                StringBuilder sb = new StringBuilder();
                while (stack.peek() != '[') {
                    sb.insert(0, stack.pop());
                }
                stack.pop();
                int num = 0;
                int count = 1;
                while (!stack.isEmpty() && '0' <= stack.peek() && stack.peek() <= '9' ) {
                    num = num + count * (stack.pop() - '0');
                    count = 10 * count;
                }
                StringBuilder sb2 = new StringBuilder();
                for (int i = 0; i < num; i++) {
                    sb2.append(sb);
                }
                for (char currentC : sb2.toString().toCharArray()) {
                    stack.push(currentC);
                }
                continue;
            }
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        System.out.println(decodeString.decodeString("100[abc]"));
    }
}
