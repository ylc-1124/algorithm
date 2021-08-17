package com.ylc.sort.cmp;

import com.ylc.sort.Sort;

public class HeapSort<E extends Comparable<E>> extends Sort<E> {
    private int heapSize;

    @Override
    protected void sort() {
        //原地建堆
        heapSize = array.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (heapSize > 1) {
            //交换堆顶元素和尾部元素
            swap(0, --heapSize);

            //对 0 位置进行下滤（回复堆的性质）
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        E element = array[index];
        int half = heapSize >> 1;  //非叶子节点数量

        while (index < half) {  //必须保证index节点是非叶子节点
            //index的子节点只有两种情况
            //1.只有左节点
            //2.同时有左右子节点

            // 默认使用左子节点跟它进行比较
            int childIndex = (index << 1) + 1;
            E child = array[childIndex];

            //右子节点
            int rightIndex = childIndex + 1;
            //选出左右子节点最大的
            if (rightIndex < heapSize
                    && cmp(array[rightIndex], child) > 0) {

                child = array[childIndex = rightIndex];
            }
            if (cmp(element, child) >= 0) break;

            //将子节点存放到index位置
            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }

}
