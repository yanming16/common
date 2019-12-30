package com.xiaoming.leetcode;

import java.util.Objects;

/**
 * @author liangyi
 * @Date 2019/12/25
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        TreeNode result = root;
        invert(root);
        return result;
    }

    private void invert(TreeNode node) {
        if (Objects.isNull(node)) {
            return;
        }
        final TreeNode left = node.left;
        node.left = node.right;
        node.right = left;
        invert(node.left);
        invert(node.right);
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
