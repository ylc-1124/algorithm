package com.ylc;

/**
 * 八皇后问题,效率优化版
 */
public class EightQueues2 {

    //标记某一列是否有皇后
    private boolean[] cols;
    private boolean[] leftTop; //标记左上角->右下角的对角线是否有皇后
    private boolean[] rightTop; //标记右上角->左下角的对角线是否有皇后
    private int[] queens;
    private int ways;

    public void placeQueues(int n) {
        if (n < 1) return;
        queens = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
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
            if (cols[col]) continue;
            int ltIndex = row - col + cols.length - 1;
            if (leftTop[ltIndex]) continue;
            int rtIndex = row + col;
            if (rightTop[rtIndex]) continue;

                //摆放皇后
            queens[row] = col;
            cols[col] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;
                //摆放下一行
            place(row + 1);
            cols[col] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;

        }
    }


    private void show() {
        System.out.println("【" + ways + "】");
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (queens[row] == col) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }
}
