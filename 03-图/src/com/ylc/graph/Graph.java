package com.ylc.graph;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public abstract class Graph<V, E> {
    protected WeightManager<E> weightManager;

    public Graph() {
    }

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    public abstract void addVertex(V v);

    public abstract void addEdge(V from, V to);

    public abstract void addEdge(V from, V to, E weight);

    public abstract void removeVertex(V v);

    public abstract  void removeEdge(V from, V to);

    public abstract int edgeSize();

    public abstract int VerticesSize();

    public abstract void bfs(V begin, VertexVisitor<V> visitor); //广度优先遍历

    public abstract void dfs(V begin, VertexVisitor<V> visitor);//深度优先搜索

    public abstract Map<V, E> shortestPath(V begin); //最短路径

    public interface VertexVisitor<V> {
        boolean visit(V v);
    }

    public interface WeightManager<E> {
        int compare(E w1, E w2);

        E add(E w1, E w2);
    }

    public abstract List<V> topologicalSort();  //拓扑排序条件：有向无环图

    public abstract  Set<EdgeInfo<V, E>> mst();// 最小生成树

    public static class EdgeInfo<V, E> {
        private V from;
        private V to;
        private E weight;

        protected EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
