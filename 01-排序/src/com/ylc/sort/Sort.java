package com.ylc.sort;

import com.ylc.Student;
import com.ylc.sort.cmp.ShellSort;

import java.text.DecimalFormat;

public abstract class Sort<E extends Comparable<E>> implements Comparable<Sort<E>>{
    protected E[] array;
    private int cmpCount;
    private int swapCount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(E[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        this.array = array;

        long begin = System.currentTimeMillis();
        sort();
        this.time = System.currentTimeMillis() - begin;

    }

    protected abstract void sort();

    /**
     *
     * @param i1
     * @param i2
     * @return  =0  说明 array[i1] == array[i2]
     *          <0  说明 array[i1] < array[i2]
     *          >0  说明 array[i1] > array[i2]
     */
    protected int cmp(int i1, int i2) {
        cmpCount++;
        return array[i1].compareTo(array[i2]);
    }

    /**
     * 比较数组元素
     * @param e1
     * @param e2
     * @return
     */
    protected int cmp(E e1, E e2) {
        cmpCount++;
        return e1.compareTo(e2);
    }

    /**
     * 传入两个索引，交换索引所在位置的值
     * @param i1
     * @param i2
     */
    protected void swap(int i1, int i2) {
        swapCount++;
        E tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
                + stableStr + " \t"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";
    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }

    @Override
    public int compareTo(Sort<E> o) {
        int result = (int) (this.time - o.time);
        if (result!=0) return result;
        //时间相等 比较两者比较次数
        result = this.cmpCount - o.cmpCount;
        if (result!=0) return result;
        //比较次数也相同就返回交换次数
        return this.swapCount - o.swapCount;

    }

    private boolean isStable() {
        if (this instanceof CountingSort) return false;
        if (this instanceof ShellSort) return false;
        Student[] students = new Student[20];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(i * 10, 10);
        }
        sort((E[]) students);
        for (int i = 1; i < students.length; i++) {
            int score = students[i].score;
            int prevScore = students[i - 1].score;
            if (score != prevScore + 10) return false;
        }
        return true;
    }
}
