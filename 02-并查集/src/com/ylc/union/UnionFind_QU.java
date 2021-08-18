package com.ylc.union;

/**
 * QuickUnion实现
 */
public class UnionFind_QU extends UnionFind {

    public UnionFind_QU(int capacity) {
        super(capacity);
    }

    /**
     * 查找v的根节点，父节点就是根节点
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    /**
     *将v1的根节点嫁接到v2的根节点上
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1==p2) return;

        parents[p1] = p2;
    }
}
