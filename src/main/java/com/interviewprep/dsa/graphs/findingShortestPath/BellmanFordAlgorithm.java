package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.*;

public class BellmanFordAlgorithm {

    static int[] bellmanFord(int V, int[][] edges, int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Relax all edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            boolean updated = false;

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    updated = true;
                }
            }

            // Early termination: if no distance was updated, we are done
            if (!updated) break;
        }

        // Check for negative cycles (one more pass)
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                System.out.println("Graph contains a negative cycle");
                return null;
            }
        }

        return dist;
    }

    //Shortest Path Faster Algorithm
    static int[] spfa(int V, List<int[]>[] adj, int source) {
        int[] dist = new int[V];
        boolean[] inQueue = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        inQueue[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;

            for (int[] edge : adj[u]) {
                int v = edge[0], w = edge[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    if (!inQueue[v]) {
                        queue.offer(v);
                        inQueue[v] = true;
                    }
                }
            }
        }

        return dist;
    }

    public static List<int[]>[] buildGraph(int n, int[][] edges) {
        List<int[]>[] graph = new ArrayList[n];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph[from].add(new int[]{to, weight});
        }

        return graph;
    }

    public static void main(String[] args) {
        int V = 5;
        // edges[i] = {from, to, weight}
        int[][] edges = {
                {0, 1, 4}, {0, 2, 2}, {1, 3, 2}, {1, 2, 3},
                {2, 1, 1}, {2, 3, 4}, {2, 4, 5}, {3, 4, -5}
        };

        //bellman ford
//        int[] dist = bellmanFord(V, edges, 0);

        List<int[]>[] adj = buildGraph(V, edges);
        int[] dist = spfa(V, adj, 0);

        if (dist != null) {
            for (int i = 0; i < V; i++) {
                System.out.println("Distance to " + i + ": " + dist[i]);
            }
        }
    }
}
