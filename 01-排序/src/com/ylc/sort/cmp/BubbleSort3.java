package com.ylc.sort.cmp;

import com.ylc.sort.Sort;

/**
 * 冒泡排序针对 局部有序 情况优化
 */
public class BubbleSort3<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 1;  //存放最后一次交换的位置,如果完全有序，扫描一遍直接退出外层循环
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }
}
