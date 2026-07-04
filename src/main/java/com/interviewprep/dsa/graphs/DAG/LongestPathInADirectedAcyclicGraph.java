package com.interviewprep.dsa.graphs.DAG;

import java.util.*;

//https://www.geeksforgeeks.org/dsa/find-longest-path-directed-acyclic-graph/
public class LongestPathInADirectedAcyclicGraph {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // when source is given
    // topological sort + dp
    // time: O(V+E)
    public int[] longestPath(int n, int[][] edges, int source) {

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        int[] indegree = new int[n];

        for (int[] e : edges) {
            graph.get(e[0]).add(new Edge(e[1], e[2]));
            indegree[e[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                queue.offer(i);

        List<Integer> topo = new ArrayList<>();

        while (!queue.isEmpty()) {
            int u = queue.poll();
            topo.add(u);

            for (Edge edge : graph.get(u)) {
                if (--indegree[edge.to] == 0)
                    queue.offer(edge.to);
            }
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MIN_VALUE);

        dist[source] = 0;

        for (int u : topo) {

            if (dist[u] == Integer.MIN_VALUE)
                continue;

            for (Edge edge : graph.get(u)) {

                dist[edge.to] = Math.max(
                        dist[edge.to],
                        dist[u] + edge.weight
                );
            }
        }

        return dist;
    }

    // when source is not given
    // DFS + Memoization
    // time: O(V+E)
    private List<List<Edge>> graph;
    private Integer[] memo;

    public int longestPath(int n, int[][] edges) {

        graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            graph.get(from).add(new Edge(to, weight));
        }

        memo = new Integer[n];

        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dfs(i));
        }

        return answer;
    }

    private int dfs(int node) {

        if (memo[node] != null)
            return memo[node];

        int longest = 0;

        for (Edge edge : graph.get(node)) {

            longest = Math.max(
                    longest,
                    edge.weight + dfs(edge.to)
            );
        }

        memo[node] = longest;
        return longest;
    }
}
