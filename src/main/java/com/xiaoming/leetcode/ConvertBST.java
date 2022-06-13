package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2020-02-13
 */
public class ConvertBST {

    public TreeNode convertBST(TreeNode root) {
        handle(root);
        return root;
    }

    private int handle(TreeNode root) {
        return handleRight(root);
    }

    private int handleRight(TreeNode root) {
        if (null == root) {
            return 0;
        }
        root.val = root.val + handleRight(root.right);
        if (root.left != null) {
            root.left.val = root.left.val + handleRight(root.left.right) + root.val;
        }
        return root.val;
    }


}
