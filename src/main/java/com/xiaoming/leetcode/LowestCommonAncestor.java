package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2019-12-27
 */
public class LowestCommonAncestor {

    private TreeNode result = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        meetNode(root, p, q);
        return result;
    }

    private boolean meetNode(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return false;
        }

        int leftMeet = meetNode(root.left, p, q) ? 1 : 0;
        int rightMeet = meetNode(root.right, p, q) ? 1 : 0;

        int meet = 0;
        if (root.val == p.val || root.val == q.val) {
            meet = 1;
        }

        if (meet + leftMeet + rightMeet >= 2) {
            result = root;
        }

        if (meet + leftMeet + rightMeet >= 1) {
            return true;
        }
        return false;
    }


}
