package com.ylc.graph;


import java.util.List;
import java.util.function.Consumer;

public interface Graph<V, E> {
    void addVertex(V v);

    void addEdge(V from, V to);

    void addEdge(V from, V to, E weight);

    void removeVertex(V v);

    void removeEdge(V from, V to);

    int edgeSize();

    int VerticesSize();

    void bfs(V begin, VertexVisitor<V> visitor); //广度优先遍历

    void dfs(V begin, VertexVisitor<V> visitor);//深度优先搜索

    interface VertexVisitor<V> {
        boolean visit(V v);
    }

    List<V> topologicalSort();  //拓扑排序条件：有向无环图
}
