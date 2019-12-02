package com.xiaoming.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaoming
 * @date 2019-12-02
 */
public class MaxDepth {

    private Set<TreeNode> list = new HashSet<>();

    private int level = 0;

    public int maxDepth(TreeNode root) {
        if (null == root) {
            return level;
        }
        level++;
        list.add(root);

        while (true) {
            Set<TreeNode> nodeList = new HashSet<>();
            for (TreeNode treeNode : list) {
                TreeNode left = treeNode.left;
                if (null != left) {
                    nodeList.add(left);
                }
                TreeNode right = treeNode.right;
                if (null != right) {
                    nodeList.add(right);
                }
            }
            if (nodeList.isEmpty()) {
                return level;
            }
            level++;
            list = nodeList;
        }
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
