package com.ylc;

/**
 * 最长公共子序列
 */
public class LCS {
    public static void main(String[] args) {

        int len = lcs5(new int[]{1, 3, 5, 9, 10},
                new int[]{1, 4, 9, 10});
        System.out.println(len);
    }

    /**
     * 一维数组 长度为两个序列长度短的
     * @param nums1
     * @param nums2
     * @return
     */
    static int lcs5(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int rows = 0, cols = 0;
        int[] rowNums, colNums;

        if (nums1.length > nums2.length) {
            cols = nums2.length;
            rows = nums1.length;
            colNums = nums2;
            rowNums = nums1;
        } else {
            cols = nums1.length;
            rows = nums2.length;
            colNums = nums1;
            rowNums = nums2;
        }
        int[] dp = new int[cols + 1];

        for (int i = 1; i <= rows; i++) {
            int cur = 0;
            for (int j = 1; j <= cols; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowNums[i - 1] == colNums[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);

                }
            }
        }
        return dp[cols];
    }

    /**
     * 使用一维数组
     */
    static int lcs4(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[] dp = new int[nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            int cur = 0;
            for (int j = 1; j <= nums2.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);

                }
            }
        }
        return dp[nums2.length];
    }

    /**
     * 滚动数组实现
     */
    static int lcs3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[][] dp = new int[2][nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            int row = i & 1;
            int prevRow = (i - 1) & 1;
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[row][j] = dp[prevRow][j - 1] + 1;
                } else {
                    dp[row][j] = Math.max(dp[prevRow][j], dp[row][j - 1]);

                }
            }
        }
        return dp[nums1.length & 1][nums2.length];
    }


    /**
     *  dp(i)(j) 表示nums1前i个元素和nums2前j个元素的最长公共子序列长度
     *   使用动态规划非递归实现
     */
    static int lcs2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
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
