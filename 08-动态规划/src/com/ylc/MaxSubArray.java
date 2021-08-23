package com.ylc;

/**
 * 最长连续子序列和-动态规划
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray2(nums));
    }
    static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp = dp + nums[i];
            }
            max = Math.max(dp, max);
        }
        return max;
    }
    static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        System.out.println("dp[0] = " + dp[0]);
        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(dp[i], max);
            System.out.println("dp[" + i + "] = " + dp[i]);
        }
        return max;
    }

}
