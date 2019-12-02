package com.xiaoming.leetcode;

/**
 * @author xiaoming
 * @date 2019-10-24
 */
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int allLength = nums1.length + nums2.length;
        boolean isTwo = allLength % 2 == 1;
        int num = allLength / 2 + 1;
        int preNum = 0;
        int currentNum = 0;
        for (int i = 0, j = 0, k = 0; k < num; k++) {
            preNum = currentNum;

            if (i >= nums1.length) {
                currentNum = nums2[j];
                j++;
                continue;
            }

            if (j >= nums2.length || nums1[i] < nums2[j]) {
                currentNum = nums1[i];
                j++;
                continue;
            }

            if (nums1[i] > nums2[j]) {
                currentNum = nums2[j];
                j++;
            }

        }
        if (isTwo) {
            return (preNum + currentNum) / 2d;
        }else {
            return currentNum;
        }
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {


        int n = nums1.length;
        int m = nums2.length;
        int i = (n + m + 1)/2;
        int j = (n + m + 2)/2;
        return (getKth(nums1, 0, n-1, nums2, 0, m-1, i) + getKth(nums1, 0, n-1, nums2, 0, m-1, j)) * 0.5;
    }

    private double getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {

        int n = end1 - start1 + 1;
        int m = end2 - start2 + 1;

        if (n < m) {
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if (n == 0) {
            return nums2[start2 + k - 1];
        }

        int i = Math.min(n - 1, k/2);
        int j = Math.min(m - 1, k/2);

        if (nums1[start1 + i - 1] > nums2[start2 + j - 1]) {
            return getKth(nums1, start1, end1, nums2, start2 + j, end2, (k - j));
        }else {
            return getKth(nums1, start1 + i, end1, nums2, start2, end2, (k - i));
        }
    }
}
