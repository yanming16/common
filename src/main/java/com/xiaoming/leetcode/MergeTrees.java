package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2020-02-17
 */
public class MergeTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return merge(t1, t2);
    }
    private TreeNode merge(TreeNode t1, TreeNode t2) {

        if (null == t1) {
            return t2;
        }

        if (null == t2) {
            return t1;
        }

        TreeNode treeNode = new TreeNode(0);
        treeNode.val = t1.val + t2.val;
        treeNode.left = merge(t1.left, t2.left);
        treeNode.right = merge(t1.right, t2.right);
        return treeNode;

    }
}
