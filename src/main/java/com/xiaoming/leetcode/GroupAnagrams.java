package com.xiaoming.leetcode;

import com.xiaoming.utils.GsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-11-13
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> resultList = new ArrayList<>();
        if (Objects.isNull(strs) || strs.length <= 0) {
            return resultList;
        }
        Map<String, List<String>> resultMap = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            List<String> valueList = resultMap.get(key);
            if (Objects.isNull(valueList)) {
                List<String> list = new ArrayList<>();
                list.add(str);
                resultMap.put(key, list);
            }else {
                valueList.add(str);
            }
        }
        return new ArrayList<>(resultMap.values());
    }

    private String getKey(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        List<List<String>> result = groupAnagrams.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(GsonUtils.getGson().toJson(result));
    }
}
