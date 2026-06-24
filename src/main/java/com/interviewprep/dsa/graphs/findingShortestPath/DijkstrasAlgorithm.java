package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.*;

public class DijkstrasAlgorithm {

    // Run Dijkstra's algorithm from the source vertex.
    // Returns an array where dist[i] = shortest distance from source to vertex i.
    public static int[] dijkstra(int n, List<int[]>[] graph, int source) {
        // dist[i] holds the shortest known distance from source to i
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // visited[i] is true once vertex i's shortest distance is finalized
        boolean[] visited = new boolean[n];

        // Min-heap storing {distance, vertex}
        // We compare by distance so the closest vertex is dequeued first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, source});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];

            // If we already finalized this vertex, skip it.
            // This handles duplicate entries in the priority queue.
            if (visited[u]) continue;
            visited[u] = true;

            // Relax all edges from u
            for (int[] edge : graph[u]) {
                int v = edge[0];
                int weight = edge[1];

                if (!visited[v] && d + weight < dist[v]) {
                    dist[v] = d + weight;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        return dist;
    }

    // Helper to build an adjacency list for a directed graph
    public static List<int[]>[] buildGraph(int n, int[][] edges) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            graph[from].add(new int[]{to, weight});
        }
        return graph;
    }

    public static void main(String[] args) {
        //[source, destination, weight]
        int[][] edges = {{0,1,2},{0,2,4},{1,2,1},{1,3,7},{2,4,3},{4,3,2},{4,5,5},{3,5,1}};
        List<int[]>[] graph = buildGraph(6, edges);
        int[] dist = dijkstra(6, graph, 0);
        System.out.println(Arrays.toString(dist));
    }
}
