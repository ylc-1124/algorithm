package com.ylc.sort;

/**
 * 计数排序
 */
public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        //找出最值
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        //开辟内存空间，存储次数
        int[] counts = new int[max - min + 1];
        //统计每个整数出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }
        //累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        //从后往前遍历array中的元素，将它放到有序数组中
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i] - min]] = array[i];

        }
        //将有序数组覆盖array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }


    private void sort0() {
        //找出最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        //开辟内存空间，存储每个整数出现的次数
        int[] counts = new int[max + 1];
        //统计每个整数出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }

        //根据整数出现的次数对整数进行排序
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) {
                array[index++] = i;
            }

        }
    }
}
