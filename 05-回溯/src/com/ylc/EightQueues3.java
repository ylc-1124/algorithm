package com.ylc;

/**
 * 八皇后问题,位运算优化
 */
public class EightQueues3 {

    //标记某一列是否有皇后
    private byte cols;
    private short leftTop; //标记左上角->右下角的对角线是否有皇后
    private short rightTop; //标记右上角->左下角的对角线是否有皇后
    private int[] queens;
    private int ways;

    public void place8Queues() {
        queens = new int[8];
        place(0);
        System.out.println("8皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第row行开始摆放皇后
     *
     * @param row 行号
     */
    private void place(int row) {
        if (row == 8) {
            ways++;
            show();
            return;
        }


        for (int col = 0; col < 8; col++) {
            int cv = 1 << col;
            if ((cols & cv) != 0) continue;
            int lv = 1 << (row - col + 7);
            if ((leftTop & lv) != 0) continue;
            int rv = 1 << (row + col);
            if ((rightTop & rv) != 0) continue;

            //摆放皇后
            queens[row] = col;
            cols |= cv;
            leftTop |= lv;
            rightTop |= rv;
            //摆放下一行
            place(row + 1);
//            cols &= ~cv;
//            leftTop &= ~lv;
//            rightTop &= ~rv;
            cols ^= cv;
            leftTop ^= lv;
            rightTop ^= rv;

        }
    }


    private void show() {
        System.out.println("【" + ways + "】");
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
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
