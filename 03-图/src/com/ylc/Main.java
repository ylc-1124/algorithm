package com.ylc;

import com.ylc.graph.Graph;
import com.ylc.graph.ListGraph;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	static Graph.WeightManager<Double> weightManager = new Graph.WeightManager<Double>() {
		@Override
		public int compare(Double w1, Double w2) {
			return w1.compareTo(w2);
		}

		@Override
		public Double add(Double w1, Double w2) {
			return w1 + w2;
		}
	};
    public static void main(String[] args) {
		testSp();
    }

	static void testSp() {
		Graph<Object, Double> graph = directedGraph(Data.SP);

		Map<Object, Double> sp = graph.shortestPath("A");
		System.out.println(sp);
	}

	static void testMst() {
		Graph<Object, Double> graph = undirectedGraph(Data.MST_01);
		Set<Graph.EdgeInfo<Object, Double>> edgeInfos = graph.mst();
		for (Graph.EdgeInfo<Object, Double> edgeInfo : edgeInfos) {
			System.out.println(edgeInfo);
		}
	}
	static void testDfs() {
		Graph<Object, Double> graph = directedGraph(Data.DFS_02);
		//graph.dfs("a");
	}

	static void testBfs() {
		Graph<Object, Double> graph = directedGraph(Data.BFS_02);
		graph.bfs(0,(Object obj)->{
			System.out.println(obj);
			return obj.equals(2);
		});


	}

	static void testTopo() {
		Graph<Object, Double> graph = directedGraph(Data.TOPO);
		List<Object> list = graph.topologicalSort();
		System.out.println(list);
	}

//	static void test() {
//		ListGraph<String, Integer> graph = new ListGraph<>();
//		graph.addEdge("v1", "v0", 9);
//		graph.addEdge("v1", "v2", 3);
//		graph.addEdge("v2", "v0", 2);
//		graph.addEdge("v2", "v3", 5);
//		graph.addEdge("v3", "v4", 1);
//		graph.addEdge("v0", "v4", 6);
//
//		//graph.bfs("v1");
//
//	}
	/**
	 * 有向图
	 */
	private static Graph<Object, Double> directedGraph(Object[][] data) {
		Graph<Object, Double> graph = new ListGraph<>(weightManager);
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
			}
		}
		return graph;
	}

	/**
	 * 无向图
	 * @param data
	 * @return
	 */
	private static Graph<Object, Double> undirectedGraph(Object[][] data) {
		Graph<Object, Double> graph = new ListGraph<>(weightManager);
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
				graph.addEdge(edge[1], edge[0]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
				graph.addEdge(edge[1], edge[0], weight);
			}
		}
		return graph;
	}

}
