package com.ylc;

import java.util.Arrays;

/**
 * 找零钱问题
 */
public class coinsChange {
    public static void main(String[] args) {
        Integer[] faces = {25, 5, 1, 10};
        Arrays.sort(faces, (i1, i2) -> i2 - i1);
        int money = 41; //需要找零的数量
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
