package com.ylc;

import com.ylc.tools.Integers;
import com.ylc.tools.Times;

public class BubbleSort {
    public static void main(String[] args) {
        Integer[] arr1 = Integers.tailAscOrder(1, 10000, 2000);
        Integer[] arr2 = Integers.copy(arr1);
        Integer[] arr3 = Integers.copy(arr1);
        Times.test("冒泡排序未优化版",()->{
            bubbleSort1(arr1);
        });
        Times.test("冒泡排序优化版1",()->{
            bubbleSort2(arr2);
        });
        Times.test("冒泡排序优化版2",()->{
            bubbleSort3(arr3);
        });

    }

    static void bubbleSort2(Integer[] arr) {  //优化过,针对提前有序的情况
        for (int end = arr.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin] < arr[begin - 1]) {
                    int tmp = arr[begin];
                    arr[begin] = arr[begin - 1];
                    arr[begin - 1] = tmp;
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }
    static void bubbleSort1(Integer[] arr) {  //未优化
        for (int end = arr.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin] < arr[begin - 1]) {
                    int tmp = arr[begin];
                    arr[begin] = arr[begin - 1];
                    arr[begin - 1] = tmp;
                }
            }
        }
    }
    static void bubbleSort3(Integer[] arr) {  //优化版2，针对局部有序的情况
        for (int end = arr.length - 1; end > 0; end--) {
            int sortedIndex = 1;  //存放最后一次交换的位置,如果完全有序，扫描一遍直接退出外层循环
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin] < arr[begin - 1]) {
                    int tmp = arr[begin];
                    arr[begin] = arr[begin - 1];
                    arr[begin - 1] = tmp;

                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }
}
