package com.ylc;

import com.ylc.sort.*;
import com.ylc.tools.Asserts;
import com.ylc.tools.Integers;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
   /*     int[] array = {2, 4, 8, 8, 8, 12, 14};

        Asserts.test(BinarySearch.search(array,5)==2);
        Asserts.test(BinarySearch.search(array,1)==0);
        Asserts.test(BinarySearch.search(array,15)==7);
        Asserts.test(BinarySearch.search(array,8)==5);
*/
        Integer[] array = Integers.random(10000, 1, 20000);

        //测试任意种排序算法的性能
        testSorts(array
                , new BubbleSort3()
                , new InsertionSort1()
                , new InsertionSort2()
                , new InsertionSort3()
                , new SelectionSort()
                , new MergeSort()
                , new HeapSort());

    }

    static void testSorts(Integer[] array,Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
