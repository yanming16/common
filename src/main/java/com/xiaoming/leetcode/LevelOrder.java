package com.xiaoming.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoming
 * @date 2019-12-01
 */
public class LevelOrder {
    private int level = 1;
    private Map<Integer, List<TreeNode>> map = new HashMap();

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (null == root){
            return result;
        }
        List<Integer> levels = new ArrayList<>();
        levels.add(root.val);
        result.add(levels);
        List<TreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(root);
        map.put(level, levelNodes);
        while (true) {
            List<Integer> levelList = new ArrayList<>();
            List<TreeNode> levelNodeList = new ArrayList<>();
            List<TreeNode> treeNodes = map.get(level);
            level++;
            if (null == treeNodes || treeNodes.isEmpty()) {
                break;
            }
            for (TreeNode treeNode:treeNodes) {
                TreeNode left = treeNode.left;
                if (null != left) {
                    levelList.add(left.val);
                    levelNodeList.add(left);
                }
                TreeNode right = treeNode.right;
                if (null != right) {
                    levelList.add(right.val);
                    levelNodeList.add(right);
                }
            }
            if (levelList.isEmpty()) {
                break;
            }
            result.add(levelList);
            map.put(level, levelNodeList);
        }
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
