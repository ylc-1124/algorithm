package com.ylc.graph;

import com.ylc.MinHeap;
import com.ylc.UnionFind;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
        return weightManager.compare(e1.weight, e2.weight);
    };

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    public ListGraph() {
    }

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
    public void bfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) return;
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            //do....
            if (visitor.visit(vertex.value)) return;

            for (Edge<V, E> edge : vertex.outEdges) {
                if (!visitedVertices.contains(edge.to)) {
                    queue.offer(edge.to);
                    visitedVertices.add(edge.to);
                }
            }
        }

    }

    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) return;
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        //先访问起点
        stack.push(beginVertex);
        visitedVertices.add(beginVertex);
        if (visitor.visit(beginVertex.value)) return;

        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();

            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                visitedVertices.add(edge.to);
                if (visitor.visit(edge.to.value)) return;
                break;
            }
        }
    }

    @Override
    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return dijkstra(begin);
    }

    private Map<V, PathInfo<V, E>> dijkstra(V begin) { //Dijkstra算法-最短路径
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;

        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
        //初始化paths
        for (Edge<V, E> edge : beginVertex.outEdges) {
            PathInfo<V, E> pathInfo = new PathInfo<>();
            pathInfo.weight = edge.weight;
            pathInfo.edgeInfos.add(edge.info());
            paths.put(edge.to, pathInfo);

        }

        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);
            // minEntry离开桌面
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPathInfo = minEntry.getValue();
            selectedPaths.put(minVertex.value, minPathInfo);
            paths.remove(minVertex);
            //对它的outEdges进行松弛操作
            for (Edge<V, E> edge : minVertex.outEdges) {
                //如果edge.to已经离开桌面就没必要松弛操作
                if (selectedPaths.containsKey(edge.to.value)) continue;
                relax(edge, minPathInfo, paths); //松弛操作

            }
        }
        selectedPaths.remove(begin);


        return selectedPaths;
    }
    /**
     * 松弛操作
     * @param edge  需要进行松弛的边
     * @param fromPathInfo  edge的from顶点的最短路径信息
     * @param paths  存放其他点（还没有离开桌面的点）的最短路径信息
     */
    private void relax(Edge<V, E> edge,PathInfo<V, E> fromPathInfo,Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        // 新的可选择的最短路径：  beginVertex 到 edge.from的最短路径 + edge.weight
        E newWeight = weightManager.add(fromPathInfo.weight, edge.weight);
        //以前的最短路径 beginVertex 到 edge.to
        PathInfo<V, E> oldPathInfo = paths.get(edge.to);
        if (oldPathInfo != null
                && weightManager.compare(newWeight, oldPathInfo.weight) >= 0) return;

        if (oldPathInfo == null) {
            oldPathInfo = new PathInfo<>();
            paths.put(edge.to, oldPathInfo);
        } else {
            oldPathInfo.edgeInfos.clear();
        }

        oldPathInfo.weight = newWeight;
        oldPathInfo.edgeInfos.addAll(fromPathInfo.edgeInfos);
        oldPathInfo.edgeInfos.add(edge.info());

    }

 /*   @Override
    public Map<V, E> shortestPath(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;

        Map<V, E> selectedPaths = new HashMap<>();
        Map<Vertex<V, E>, E> paths = new HashMap<>();
        //初始化paths
        for (Edge<V, E> edge : beginVertex.outEdges) {
            paths.put(edge.to, edge.weight);
        }

        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, E> minEntry = getMinPath(paths);
            // minEntry离开桌面
            Vertex<V, E> minVertex = minEntry.getKey();
            selectedPaths.put(minVertex.value, minEntry.getValue());
            paths.remove(minVertex);
            //对它的outEdges进行松弛操作
            for (Edge<V, E> edge : minVertex.outEdges) {
                //如果edge.to已经离开桌面就没必要松弛操作
                if (selectedPaths.containsKey(edge.to.value)) continue;
                // 新的可选择的最短路径：  beginVertex 到 edge.from的最短路径 + edge.weight
                E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
                //以前的最短路径 beginVertex 到 edge.to
                E oldWeight = paths.get(edge.to);
                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
                    paths.put(edge.to, newWeight);
                }
            }
        }
        selectedPaths.remove(begin);


        return selectedPaths;
    }*/



    /**
     * 从paths中挑出路径最短的返回
     */
    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> it = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = it.next();
        while (it.hasNext()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> entry = it.next();
            if (weightManager.compare(entry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    @Override
    public List<V> topologicalSort() {
        List<V> list = new ArrayList<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Map<Vertex<V, E>, Integer> inDegreeMap = new HashMap<>();
        //初始化（将度为0的节点入队）
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int inDegree = vertex.inEdges.size();
            if (inDegree == 0) {
                queue.offer(vertex);
            } else {
                inDegreeMap.put(vertex, inDegree);
            }
        });

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            //放入返回结果中
            list.add(vertex.value);
            for (Edge<V, E> edge : vertex.outEdges) {
                int toInDegree = inDegreeMap.get(edge.to) - 1;
                if (toInDegree == 0) {
                    queue.offer(edge.to);
                } else {
                    inDegreeMap.put(edge.to, toInDegree);
                }
            }
        }
        return list;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return Math.random() > 0.5 ? prim() : kruskal(); //随机使用prim或者kruskal算法
    }

    private Set<EdgeInfo<V, E>> prim() { // Prim算法
        Iterator<Vertex<V, E>> it = vertices.values().iterator();
        if (!it.hasNext()) return null;
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        Set<Vertex<V, E>> addedVertices = new HashSet<>();
        Vertex<V, E> vertex = it.next(); //从所有顶点中随机取出一个
        addedVertices.add(vertex);

        MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);

        int edgeSize = vertices.size() - 1;
        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, E> edge = heap.remove();
            if (addedVertices.contains(edge.to)) continue;

            edgeInfos.add(edge.info());

            addedVertices.add(edge.to);
            heap.addAll(edge.to.outEdges);
        }
        return edgeInfos;
    }

    private Set<EdgeInfo<V, E>> kruskal() { // Kruskal算法
        int edgeSize = vertices.size() - 1;
        if (edgeSize == -1) return null;
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);
        UnionFind<Vertex<V, E>> uf = new UnionFind<>(); //并查集检验是否成环

        vertices.forEach((V v, Vertex<V, E> vertex) -> { //初始化并查集
            uf.makeSet(vertex);
        });

        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, E> edge = heap.remove();

            //判断最小的边选择是否会有环
            if (uf.isSame(edge.from, edge.to)) continue;

            edgeInfos.add(edge.info());
            uf.union(edge.from, edge.to);
        }

        return edgeInfos;
    }

/*
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
    }*/
/*    @Override
    public void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        //先访问起点
        stack.push(beginVertex);
        visitedVertices.add(beginVertex);
        System.out.println(beginVertex.value);

        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();

            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                visitedVertices.add(edge.to);
                System.out.println(edge.to.value);
                break;
            }
        }
    }*/


    /*@Override
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
    }*/

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

        EdgeInfo<V, E> info() {
            return new EdgeInfo<>(from.value, to.value, weight);
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
        edges.forEach((Edge<V, E> edge) -> {
            System.out.println(edge);
        });


    }
}
