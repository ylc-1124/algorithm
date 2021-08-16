package com.ylc;

/**
 * 二分查找 左闭右开 [ begin , end )
 */
public class BinarySearch {
    /**
     * 查找目标值在有序数组中是否存在
     * @param array
     * @param target
     * @return 存在则返回索引位置，不存在则返回-1
     */
    public static int indexOf(int[] array, int target) {
        if (array==null||array.length==0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (target < array[mid]) {
                end = mid;
            } else if (target > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找目标值在有序数组中的待插入位置
     * @param array
     * @param target
     * @return 插入位置的索引
     */
    public static int search(int[] array, int target) {
        if (array==null||array.length==0) return -1;

        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (target < array[mid]) { //往左找
                end = mid;
            } else { //  target >= array[mid]  往右找
                begin = mid + 1;
            }
        }
        return begin;
    }
}
