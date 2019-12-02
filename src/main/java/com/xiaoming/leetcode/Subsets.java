package com.xiaoming.leetcode;

import com.xiaoming.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-11-23
 */
public class Subsets {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        result.add(new ArrayList<Integer>());
        if (Objects.isNull(nums) || nums.length <= 0) {
            return result;
        }
        for (int num : nums) {
            addNext(num);
        }
        return result;
    }

    private void addNext(int num) {
        List<List<Integer>> tmp = new ArrayList<>();
        for (List<Integer> list : result) {
            List<Integer> listTmp = new ArrayList<>(list);
            listTmp.add(num);
            tmp.add(listTmp);
        }
        result.addAll(tmp);
    }

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        System.out.println(GsonUtils.getGson().toJson(subsets.subsets(new int[]{1,2,3})));
    }

}
