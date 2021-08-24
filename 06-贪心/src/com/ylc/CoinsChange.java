package com.ylc;

import java.util.Arrays;

/**
 * 找零钱问题-贪心算法-不是最优解
 */
public class CoinsChange {
    public static void main(String[] args) {
        Integer[] faces = {25, 5, 1, 10};
        Arrays.sort(faces, (i1, i2) -> i2 - i1); //从大到小排列
     //   coinChange2(faces, 41);
        coinChange(faces,41);

    }

    static void coinChange(Integer[] faces, int money) {
        int count = 0;
        int index = 0;
        while (index <= faces.length - 1) {
            while (money >= faces[index]) {
                money -= faces[index];
                count++;
            }
            index++;
        }
        System.out.println("找了"+count+"张纸币");
    }




    static void coinChange2(Integer[] faces, int money) {
        int count = 0;
        int i = 0;
        while (i < faces.length) {
            if (faces[i] > money) {
                i++;
                continue;
            }
            money -= faces[i];
            count++;
            System.out.println(faces[i]);

        }
        System.out.println("找了"+count+"张纸币");
    }
}
