package com.ylc;

/**
 * 最长上升子序列-动态规划
 */
public class LIS {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS3(new int[]{10, -1, 2, 2, 5, 7, 101, 4}));
    }

    /**
     * 二分搜索优化
     */
    static int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //牌堆的数量
        int len = 0;
        //牌顶数组
        int[] top = new int[nums.length];
        //遍历所有的牌
        for (int num : nums) {
            int begin = 0;
            int end = len;
            while (begin < end) {
                int mid = (begin + end) >> 1;
                if (num <= top[mid]) { //往左找
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            }
            //覆盖牌顶
            top[begin] = num;
            if (begin == len) len++;
        }

        return len;
    }
    /**
     * 牌顶
     */
    static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //牌堆的数量
        int len = 0;
        //牌顶数组
        int[] top = new int[nums.length];
        //遍历所有的牌
        for (int num : nums) {
            int i = 0;
            while (i < len) {
                //找到一个大于等于num的牌堆
                if (top[i] >= num) {
                    top[i] = num;
                    break;
                }
                i++;
            }
            if (i == len) {
                top[len++] = num;
            }

        }

        return len;
    }


    static int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
