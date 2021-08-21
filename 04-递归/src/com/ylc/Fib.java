package com.ylc;

/**
 * 斐波那契数列
 */
public class Fib {
    /**
     * 计算斐波那契数列第n项方法-简单版（有大量重复计算）
     */
    public int fib0(int n) {
        if (n <= 2) return 1;

        return fib0(n - 1) + fib0(n - 2);
    }

    /**
     * 计算第 n 项斐波那契数列的值-优化版
     */
    public int fib1(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        return fib1(n, array);
    }

    private int fib1(int n, int[] array) {
        if (array[n] == 0) {
            array[n] = fib1(n - 1, array) + fib1(n - 2, array);
        }
        return array[n];
    }

    /**
     * 非递归版本优化
     */
    public int fib2(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    /**
     * 滚动数组优化
     */
    public int fib3(int n) {
        if (n <= 2) return 1;
        int[] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            // % 2 可以用 & 1 替代
            array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
        }
        return array[n  & 1];
    }

    /**
     * 两个变量优化
     */
    public int fib4(int n) {
        if (n <= 2) return 1;
        int first = 1;
        int second = 1;
        for (int i = 3; i <= n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }
}
