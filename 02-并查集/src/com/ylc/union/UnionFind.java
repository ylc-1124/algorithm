package com.ylc.union;

public abstract class UnionFind {
    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }


    public abstract int find(int v);

    public abstract void union(int v1, int v2);

    /**
     * 查看v1,v2是否属于同一个集合
     */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IndexOutOfBoundsException("v is out of bounds");
        }
    }
}
