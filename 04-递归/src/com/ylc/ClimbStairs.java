package com.ylc;

/**
 * 爬楼梯问题
 */
public class ClimbStairs {
    //类似于斐波那契问题，只有初始值不一样
    public int climbStairs(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
