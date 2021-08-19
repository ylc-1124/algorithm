package com.ylc.graph;


public interface Graph<V, E> {
    void addVertex(V v);

    void addEdge(V from, V to);

    void addEdge(V from, V to, E weight);

    void removeVertex(V v);

    void removeEdge(V from, V to);

    int edgeSize();

    int VerticesSize();

    void bfs(V begin); //广度优先遍历

    void dfs(V begin);//深度优先搜索
}
