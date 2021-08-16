package com.ylc;

import com.ylc.sort.*;
import com.ylc.tools.Integers;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] array = Integers.random(10000, 1, 20000);

        //测试任意种排序算法的性能
        testSorts(array
                , new BubbleSort3()
                , new BubbleSort2()
                , new BubbleSort1()
                , new SelectionSort()
                , new HeapSort());

    }

    static void testSorts(Integer[] array,Sort... sorts) {
        for (Sort sort : sorts) {
            sort.sort(Integers.copy(array));
        }
        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
