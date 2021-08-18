package com.ylc;

import com.ylc.tools.Asserts;
import com.ylc.union.UnionFind;
import com.ylc.union.UnionFind_QF;
import com.ylc.union.UnionFind_QU;

public class Main {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind_QU(12);
        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);


        Asserts.test(!uf.isSame(2, 7));
        uf.union(4, 6);
        Asserts.test(uf.isSame(2, 7));
    }
}
