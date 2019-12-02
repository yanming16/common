package com.xiaoming.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoming
 * @date 2019-10-23
 */
public class LengthOfLongestSubstring {
    private int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) {
            return s.length();
        }
        int max = 1;
        int maxNum = 1;
        int startIndex = 0;
        int currentIndex = 1;
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(startIndex), 0);
        for(; currentIndex < s.length(); currentIndex++) {
            if(map.containsKey(s.charAt(currentIndex))){
                if(maxNum > max) {
                    max = maxNum;
                }
                if(map.get(s.charAt(currentIndex)) < startIndex) {
                    map.put(s.charAt(currentIndex), currentIndex);
                    maxNum++;
                    continue;
                }
                startIndex = map.get(s.charAt(currentIndex));
                map.put(s.charAt(currentIndex), currentIndex);
                maxNum = currentIndex - startIndex;
                continue;
            }
            map.put(s.charAt(currentIndex), currentIndex);
            maxNum++;
        }
        if (maxNum > max) {
            max = maxNum;
        }
        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring solution = new LengthOfLongestSubstring();
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}
