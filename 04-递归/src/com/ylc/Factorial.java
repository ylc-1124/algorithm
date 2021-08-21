package com.ylc;

public class Factorial {

    /**
     * 求n的阶乘
     */
    public int factorial1(int n) {
        if (n <= 1) return 1;
        return n * factorial1(n - 1);
    }

    /**
     * 尾递归形式
     */
    public int factorial2(int n) {
        return factorial2(n, 1);
    }

    private int factorial2(int n, int result) {
        if (n <= 1) return result;
        return factorial2(n - 1, n * result);
    }

}
