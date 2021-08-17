package com.ylc.sort.cmp;

import com.ylc.sort.Sort;

public class QuickSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * 对 [ begin , end )范围的元素进行快速排序
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;

        //确定轴点位置
        int mid = pivotIndex(begin, end);

        //对子序列进行快速排序
        sort(begin, mid);
        sort(mid + 1, end);
    }

    /**
     * 确定[ begin , end )轴点位置
     *
     * @return 轴点元素的最终位置
     */
    private int pivotIndex(int begin, int end) {
        //随机选择一个索引的元素和begin位置进行交换
        swap(begin, begin + ((int) (Math.random() * (end - begin))));
        //备份begin位置的元素
        E privot = array[begin];
        //让end指向最后一个元素
        end--;
        while (begin < end) {
            while (begin < end) {
                if (cmp(privot, array[end]) < 0) { //右边元素 > 轴点元素
                    end--;
                } else { //右边元素 <= 轴点元素
                    array[begin++] = array[end];
                    break;
                }
            }
            while (begin < end) {
                if (cmp(privot, array[begin]) > 0) { //左边元素 < 轴点元素
                    begin++;
                } else { //左边元素 >= 轴点元素
                    array[end--] = array[begin];
                    break;
                }
            }

        }
        //将备份的值覆盖begin的位置，并返回
        array[begin] = privot;
        return begin;
    }

}
