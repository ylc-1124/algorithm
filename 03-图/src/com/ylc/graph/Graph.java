package com.ylc.graph;


public interface Graph<V, E> {
    void addVertex(V v);

    void addEdge(V from, V to);

    void addEdge(V from, V to, E weight);

    void removeVertex(V v);

    void removeEdge(V from, V to);

    int edgeSize();

    int VerticesSize();
}
