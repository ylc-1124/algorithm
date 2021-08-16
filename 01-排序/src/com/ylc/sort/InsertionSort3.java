package com.ylc.sort;

/**
 * 插入算法,二分搜索优化版
 * @param <E>
 */
public class InsertionSort3<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            insert(begin, search(begin));
        }
    }

    /**
     * 将source位置的元素取出来插入dest的位置
     * @param source
     * @param dest
     */
    private void insert(int source, int dest) {
        E e = array[source];
        for (int i = source; i > dest; i--) {
            array[i] = array[i - 1];
        }
        array[dest] = e;
    }

    /**
     * 利用二分搜索找到index位置元素的待插入位置
     * 已经排好序数组的区间范围 [ 0 , index )
     * @param index 这个index可以表示前面已经排序好的数组的长度
     * @return
     */
    private int search(int index) {
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) < 0) { //往左找
                end = mid;
            } else { //  target >= array[mid]  往右找
                begin = mid + 1;
            }
        }
        return begin;
    }
}
