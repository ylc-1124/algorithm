package com.ylc;

import java.util.Arrays;

/**
 * 最小装载问题（加勒比海盗问题）
 */
public class Pirate {
    public static void main(String[] args) {
        int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};
        Arrays.sort(weights);
        int capacity = 30,//总容量
                weight = 0;  //已使用容量
        int count = 0;
        for (int i = 0; i < weights.length && weight < capacity; i++) {
            int newWeight = weight + weights[i];
            if (newWeight > capacity) continue;
            weight = newWeight;
            count++;
            System.out.println(weights[i]);
        }
        System.out.println("一共选了"+count+"件古董");
    }
}
