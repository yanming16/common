package com.xiaoming.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoming
 * @date 2019-10-26
 */
public class Convert {

    public static String convert(String s, int numRows) {
            if(numRows < 2) {
                return s;
            }
            List<StringBuilder> rows = new ArrayList<StringBuilder>();
            for(int i = 0; i < numRows; i++) {
                rows.add(new StringBuilder());
            }
            int i = 0, flag = -1;
            for(char c : s.toCharArray()) {
                rows.get(i).append(c);
                if(i == 0 || i == numRows -1) {
                    flag = - flag;
                }
                i += flag;
            }
            StringBuilder res = new StringBuilder();
            for(StringBuilder row : rows) {
                res.append(row);
            }
            return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
    }
}
