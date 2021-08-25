package com.ylc;

/**
 * 最长公共子串
 */
public class LCSubstring {
    public static void main(String[] args) {
        System.out.println(lcs("ABCBA", "BABCA"));
    }

    /**
     * dp[i][j]表示 以str1[i-1]和str2[j-1]结尾的最长公共子串的值
     */
    static int lcs(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        char[] chars1 = str1.toCharArray();
        if (chars1.length == 0) return 0;
        char[] chars2 = str2.toCharArray();
        if (chars2.length == 0) return 0;

        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = 0;
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] != chars2[j - 1]) continue;
                dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(dp[i][j], max);
                }
            }
        return max;
        }

    }

