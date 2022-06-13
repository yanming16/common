package com.xiaoming.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiaoming
 * @date 2019-12-07
 */
public class MaxPathSum {

    private int maxSum = Integer.MIN_VALUE;

    private Map<TreeNode, Integer> map = new HashMap<>();

    public int maxPathSum(TreeNode root) {
        doMax(root);
        return maxSum;
    }

    public int doMax(TreeNode root) {
        if (null == root) {
            return Integer.MIN_VALUE;
        }
        maxSum = Math.max(maxSum, root.val);
        int maxLeft = maxRootSum(root.left);
        int maxRight = maxRootSum(root.right);
        int maxLeftRoot = doMax(root.left);
        int maxRightRoot = doMax(root.right);
        int max = Math.max(Math.max(maxLeftRoot, maxRightRoot), root.val + Math.max(0, maxLeft) + Math.max(0, maxRight));
        maxSum = Math.max(max, maxSum);
        return max;
    }

    private int maxRootSum(TreeNode node) {
        if (null == node) {
            return 0;
        }
        Integer integer = map.get(node);
        if (Objects.nonNull(integer)) {
            return integer;
        }
        int result = node.val + Math.max(0, Math.max(maxRootSum(node.left), maxRootSum(node.right)));
        map.put(node, result);
        return result;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
