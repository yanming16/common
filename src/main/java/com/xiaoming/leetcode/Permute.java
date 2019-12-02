package com.xiaoming.leetcode;

import com.xiaoming.utils.GsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiaoming
 * @date 2019-11-10
 */
public class Permute {

    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<Integer>(), visited);
        return res;

    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 1) continue;
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        Permute permute = new Permute();
        System.out.println(GsonUtils.getGson().toJson(permute.permute(new int[]{1,1,3})));
    }

}
