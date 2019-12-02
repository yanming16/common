package com.xiaoming.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author xiaoming
 * @date 2019-10-28
 */
public class ThreeSum {

    public static List<List<Integer>> solution(int[] nums) {

        Set<List<Integer>> results = new HashSet<>();

        Map<Integer, Integer> positiveNumbers = new HashMap<>();
        Map<Integer, Integer> negativeNumbers = new HashMap<>();
        int zeroCount = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                Integer count = positiveNumbers.get(nums[i]);
                if (Objects.isNull(count)) {
                    positiveNumbers.put(nums[i], 1);
                }else {
                    positiveNumbers.put(nums[i], 2);
                }
            }else if (nums[i] == 0) {
                zeroCount++;
            }else {
                Integer count = negativeNumbers.get(nums[i]);
                if (Objects.isNull(count)) {
                    negativeNumbers.put(nums[i], 1);
                }else {
                    negativeNumbers.put(nums[i], 2);
                }
            }
        }

        if (zeroCount > 2) {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(0);
            result.add(0);
            result.add(0);
            results.add(result);
        }


        for (Map.Entry<Integer, Integer> positiveEntry:positiveNumbers.entrySet()) {
            Integer positiveEntryKey = positiveEntry.getKey();
            Integer positiveEntryValue = positiveEntry.getValue();
            for (Map.Entry<Integer, Integer> negativeEntry:negativeNumbers.entrySet()){
                Integer negativeEntryKey = negativeEntry.getKey();
                Integer negativeEntryValue = negativeEntry.getValue();

                int theNext = 0 - positiveEntryKey - negativeEntryKey;
                if (theNext > 0) {
                    if (theNext == positiveEntryKey ) {
                        if (positiveEntryValue > 1) {
                            results.add(getList(positiveEntryKey, positiveEntryKey, negativeEntryKey));
                            continue;
                        }else {
                            continue;
                        }
                    }
                    Integer theNextValue = positiveNumbers.get(theNext);
                    if (Objects.nonNull(theNextValue)) {
                        if (theNext > positiveEntryKey) {
                            results.add(getList(theNext, positiveEntryKey, negativeEntryKey));
                        }else {
                            results.add(getList(positiveEntryKey, theNext, negativeEntryKey));
                        }
                        continue;
                    }
                }
                if (theNext == 0 && zeroCount > 0) {
                    results.add(getList(positiveEntryKey, 0, negativeEntryKey));
                    continue;
                }
                if (theNext < 0) {
                    if (theNext == negativeEntryKey) {
                        if ( negativeEntryValue > 1) {
                            results.add(getList(positiveEntryKey, negativeEntryKey, negativeEntryKey));
                            continue;
                        }else {
                            continue;
                        }
                    }
                    Integer theNextValue = negativeNumbers.get(theNext);
                    if (Objects.nonNull(theNextValue)) {
                        if (theNext > negativeEntryKey) {
                            results.add(getList(positiveEntryKey, theNext, negativeEntryKey));
                        }else {
                            results.add(getList(positiveEntryKey, negativeEntryKey, theNext));
                        }
                        continue;
                    }
                }

            }

        }

        return new ArrayList<>(results);

    }
    public static List<Integer> getList(int a, int b, int c) {
        List<Integer> result = new ArrayList<>();
        result.add(a);
        result.add(b);
        result.add(c);
        return result;
    }

    public static void main(String[] args) {
        solution(new int[]{-1,0,1,2,-1,-4});
    }
}
