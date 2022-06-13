package com.xiaoming.leetcode;

import com.xiaoming.utils.JacksonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoming
 * @date 2020-01-19
 */
public class FindDisappearedNumbers {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] result = new int[nums.length + 1];
        for (int num:nums) {
            result[num] = 1;
        }
        List<Integer> resultList = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (result[i] == 0) {
                resultList.add(i);
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        FindDisappearedNumbers findDisappearedNumbers = new FindDisappearedNumbers();
        System.out.println(JacksonUtils.encode2String(findDisappearedNumbers.findDisappearedNumbers(new int[]{1,1})));
    }

}
