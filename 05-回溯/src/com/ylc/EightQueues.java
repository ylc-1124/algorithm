package com.ylc;

/**
 * 八皇后问题
 */
public class EightQueues {
    /**
     * 数组索引是行号，数组元素是皇后放的列号
     */
   private int[] cols;
   private int ways;

    public void placeQueues(int n) {
        if (n < 1) return;
        cols = new int[n];
        place(0);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第row行开始摆放皇后
     *
     * @param row 行号
     */
    private void place(int row) {
        if (row == cols.length) {
            ways++;
            show();
            return;
        }


        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                //摆放皇后
                cols[row] = col;
                //摆放下一行
                place(row + 1);
            }
        }
    }

    /**
     * 判断第row行，col列是否可以放皇后
     */
    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            //前几行摆放皇后的位置 是否与传入的col在同一列
            if (cols[i] == col) return false;
            //前几行摆放皇后的位置 是否在传入位置的两条对角线上
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

    private void show() {
        System.out.println("【" + ways + "】");
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }
}
