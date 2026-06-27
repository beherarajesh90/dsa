package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//https://leetcode.com/problems/path-with-maximum-probability/description/
public class PathWithMaximumProbability {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // Build adjacency list
        List<List<double[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            double p = succProb[i];
            graph.get(u).add(new double[]{v, p});
            graph.get(v).add(new double[]{u, p});
        }

        double[] prob = new double[n];
        prob[start_node] = 1.0;

        // Max-heap: stores (probability, node), sorted by probability descending
        PriorityQueue<double[]> heap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        heap.offer(new double[]{1.0, start_node});

        while (!heap.isEmpty()) {
            double[] top = heap.poll();
            double currProb = top[0];
            int curr = (int) top[1];

            // Reached the destination
            if (curr == end_node) return currProb;

            // Skip if we already found a better path to this node
            if (currProb < prob[curr]) continue;

            for (double[] neighbor : graph.get(curr)) {
                int next = (int) neighbor[0];
                double edgeProb = neighbor[1];
                double newProb = currProb * edgeProb;

                // Only update if this path is better
                if (newProb > prob[next]) {
                    prob[next] = newProb;
                    heap.offer(new double[]{newProb, next});
                }
            }
        }

        return 0.0;
    }
}
