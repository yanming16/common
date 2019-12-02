package com.xiaoming.leetcode;

import java.util.Stack;

/**
 * @author xiaoming
 * @date 2019-11-30
 */
public class IsSymmetric {

    private Stack<TreeNode> stack = new Stack<>();

    private Stack<Integer> stack2 = new Stack<>();

    private boolean isRight = false;

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        TreeNode currentNode = root;
        while (!stack.empty() || currentNode != null) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            if (currentNode == root) {
                isRight = true;
                currentNode = currentNode.right;
                continue;
            }
            if (!isRight) {
                stack2.push(currentNode.val);
                currentNode = currentNode.right;
                continue;
            }
            if (stack2.empty() || !stack2.pop().equals(currentNode.val)) {
                return false;
            }
            currentNode = currentNode.right;
        }
        return stack2.empty();
    }

    /**
     *       1
     *      / \
     *     2  2
     *    / \/ \
     *   3  44 3
     * @param args
     */
    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        rootNode.left = node1;
        rootNode.right = node2;
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right = node4;
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        node2.left = node5;
        node2.right = node6;
        IsSymmetric isSymmetric = new IsSymmetric();
        System.out.println(isSymmetric.isSymmetric(rootNode));
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
