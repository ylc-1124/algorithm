package com.ylc.sort.cmp;

import com.ylc.sort.Sort;

/**
 * 插入算法
 * @param <E>
 */
public class InsertionSort1<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                swap(cur, cur - 1);
                cur--;
            }
        }
    }
}
