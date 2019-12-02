package com.xiaoming.leetcode;

import java.util.Stack;

/**
 * @author xiaoming
 * @date 2019-11-25
 */
public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        Stack < Integer > stack = new Stack< >();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
        }
        return maxarea;
    }

    public static void main(String[] args) {
        LargestRectangleArea largestRectangleArea = new LargestRectangleArea();
        largestRectangleArea.largestRectangleArea(new int[]{2,1,5,6,2,3});
    }
}
