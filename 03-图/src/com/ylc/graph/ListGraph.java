package com.ylc.graph;

import java.util.*;

public class ListGraph<V, E> implements Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();


    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));

    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        //判断from 和 to存不存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        //删除原来集合中的边

        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

        //添加
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;

        Iterator<Edge<V, E>> outEdgeIterator = vertex.outEdges.iterator();
        while (outEdgeIterator.hasNext()) {
            Edge<V, E> edge = outEdgeIterator.next();
            edge.to.inEdges.remove(edge);
            //将当前遍历到的元素edge从集合中删掉
            outEdgeIterator.remove();
            edges.remove(edge);
        }
        Iterator<Edge<V, E>> inEdgeIterator = vertex.inEdges.iterator();
        while (inEdgeIterator.hasNext()) {
            Edge<V, E> edge = inEdgeIterator.next();
            edge.from.outEdges.remove(edge);
            //将当前遍历到的元素edge从集合中删掉
            inEdgeIterator.remove();
            edges.remove(edge);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int VerticesSize() {
        return vertices.size();
    }

    @Override
    public void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            //do....
            System.out.println(vertex.value);

            for (Edge<V, E> edge : vertex.outEdges) {
                if (!visitedVertices.contains(edge.to)) {
                    queue.offer(edge.to);
                    visitedVertices.add(edge.to);
                }
            }
        }
    }

    @Override
    public void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        dfs(beginVertex,new HashSet<>());

    }

    private void dfs(Vertex<V, E> vertex,Set<Vertex<V, E>> visitedVertices) {

        System.out.println(vertex.value);
        visitedVertices.add(vertex);

        for (Edge<V, E> edge : vertex.outEdges) {
            if (!visitedVertices.contains(edge.to)) {
                dfs(edge.to, visitedVertices);
            }
        }
    }

    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> outEdges = new HashSet<>();  //入度
        Set<Edge<V, E>> inEdges = new HashSet<>(); //出度

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            Edge<V, E> edge = (Edge<V, E>) obj;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    public void print() {
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            System.out.println(v);
            System.out.println("outEdges");
            System.out.println(vertex.outEdges);
            System.out.println("inEdges");
            System.out.println(vertex.inEdges);
            System.out.println("=================================");
        });
        edges.forEach((Edge<V,E> edge)->{
            System.out.println(edge);
        });



    }
}
