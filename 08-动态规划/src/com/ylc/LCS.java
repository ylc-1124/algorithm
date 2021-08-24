package com.ylc;

/**
 * 最长公共子序列
 */
public class LCS {
    public static void main(String[] args) {

        int len = lcs1(new int[]{1, 3, 5, 9, 10},
                new int[]{1, 4, 9, 10});
        System.out.println(len);
    }

    //递归实现
    static int lcs1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        return lcs1(nums1, nums1.length, nums2, nums2.length);
    }

    /**
     *  求nums1前i个元素和nums2前j个元素的最长公共子序列长度
     */
    static int lcs1(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]) {
            return lcs1(nums1, i - 1, nums2, j - 1) + 1;
        }

        return Math.max(lcs1(nums1, i - 1, nums2, j),
                lcs1(nums1, i, nums2, j - 1));
    }
}
