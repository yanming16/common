package com.xiaoming.leetcode;

import com.xiaoming.utils.GsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoming
 * @date 2019-10-29
 */
public class LetterCombinations {

    public static List<String> letterCombinations(String digits) {

        Map<Character, String> numToStrings = new HashMap<>();
        numToStrings.put('2', "abc");
        numToStrings.put('3', "def");
        numToStrings.put('4', "ghi");
        numToStrings.put('5', "jkl");
        numToStrings.put('6', "mno");
        numToStrings.put('7', "pqrs");
        numToStrings.put('8', "tuv");
        numToStrings.put('9', "wxyz");

        ArrayList<String> sbs = new ArrayList<>();

        for (int i = 0; i < digits.length(); i++) {
            List<String> stringBuilders = getCombinations(sbs, digits.charAt(i), numToStrings);
            sbs.clear();
            sbs.addAll(stringBuilders);
        }

        return sbs;
    }

    private static List<String> getCombinations(ArrayList<String> sbs, char charAt, Map<Character, String> numToStrings) {

        ArrayList<String> result = new ArrayList<>();
        String string = numToStrings.get(charAt);
        for (int i = 0; i < string.length(); i++) {
            if (sbs.isEmpty()) {
                result.add(string.substring(i, i+1));
                continue;
            }
            for (String sb : sbs) {
                result.add(sb+string.substring(i, i+1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(GsonUtils.getGson().toJson(letterCombinations("23")));
    }
}
