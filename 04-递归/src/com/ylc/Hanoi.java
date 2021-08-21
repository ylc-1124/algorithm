package com.ylc;

/**
 * 汉诺塔问题
 */
public class Hanoi {
    /**
     * 将 n 个碟子，从p1 移动到 p3
     * @param p2 中间的柱子
     */
    public void hanoi(int n, String p1, String p2, String p3) {
        if (n == 1) {
            move(n, p1, p3);
            return;
        }
        hanoi(n - 1, p1, p3, p2);
        move(n, p1, p3);
        hanoi(n - 1, p2, p1, p3);
    }

    /**
     * "将" + no + "号盘子从" + from + "移动到" + to
     */
    private void move(int no, String from, String to) {
        System.out.println("将" + no + "号盘子从" + from + "移动到" + to);
    }
}
