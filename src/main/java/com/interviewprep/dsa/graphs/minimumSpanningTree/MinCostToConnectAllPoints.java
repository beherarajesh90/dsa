package com.interviewprep.dsa.graphs.minimumSpanningTree;

import java.util.*;

//https://leetcode.com/problems/min-cost-to-connect-all-points/description/
public class MinCostToConnectAllPoints {
    public int minCostConnectPoints(int[][] points) {

        // return minCostConnectPointsUsingKruskalsAlgo(points);

        // return minCostConnectPointsUsingPrimsAlgo(points);

        return minCostConnectPointsUsingPrimsAlgoWithoutHeap(points);
    }

    private int minCostConnectPointsUsingPrimsAlgoWithoutHeap(int[][] points){

        int n = points.length;
        // minDist[j] = cheapest edge from any MST node to j. -1 means visited.
        int[] minDist = new int[n];
        for (int j = 1; j < n; j++) {
            minDist[j] = Math.abs(points[0][0] - points[j][0]) + Math.abs(points[0][1] - points[j][1]);
        }
        minDist[0] = -1; // node 0 is in the MST

        int totalCost = 0;

        for (int i = 0; i < n - 1; i++) {
            // Find unvisited node with smallest minDist
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (minDist[j] >= 0 && (u == -1 || minDist[j] < minDist[u])) {
                    u = j;
                }
            }

            totalCost += minDist[u];
            minDist[u] = -1; // mark as visited

            // Update minDist for remaining unvisited nodes
            for (int v = 0; v < n; v++) {
                if (minDist[v] >= 0) {
                    int dist = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    minDist[v] = Math.min(minDist[v], dist);
                }
            }
        }

        return totalCost;

    }

    // time: O(n^2 log n), space: n^2
    private int minCostConnectPointsUsingPrimsAlgo(int[][] points){
        int n = points.length;
        boolean[] visited = new boolean[n];

        // vertex, cost
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        //from 0 to all vertices and cost
        for(int j=1; j<n; j++){
            int cost = Math.abs(points[0][0] - points[j][0]) + Math.abs(points[0][1] - points[j][1]);
            pq.offer(new int[]{j, cost});
        }

        // mark 0 as visited
        visited[0] = true;

        int mstCost = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            int node = cur[0];
            int cost = cur[1];
            System.out.println(node+" "+cost);
            if(visited[node]) continue;
            visited[node] = true;
            mstCost+=cost;

            for(int j=0; j<n; j++){
                if(!visited[j]){
                    int newCost = Math.abs(points[node][0] - points[j][0]) + Math.abs(points[node][1] - points[j][1]);
                    pq.offer(new int[]{j, newCost});
                }
            }
        }

        return mstCost;
    }

    // generating all the edges: O(n^2)
    // sorting: O(n^2 log n)
    // union: O(1)
    // overal time: dorting dominates so O(n^2 log n)

    // space:
    // edges: n^2
    // union parent: n
    private int minCostConnectPointsUsingKruskalsAlgo(int[][] points){
        int n = points.length;

        List<int[]> edges = new ArrayList<>();

        // form edges between one point to all the points
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){

                int cost = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                int[] edge = new int[]{i, j, cost};
                edges.add(edge);
            }
        }

        // sort the edges based on cost
        Collections.sort(edges, (a, b) -> a[2] - b[2]);

        DSU dsu = new DSU(n);
        int mstCost = 0;
        int edgesVisited = 0;

        for(int[] edge: edges){
            if(dsu.union(edge[0], edge[1])){
                mstCost += edge[2];
                edgesVisited++;

                if(edgesVisited == n-1){
                    break;
                }
            }
        }

        return mstCost;
    }

    class DSU{
        int[] parent;
        int[] rank;
        public DSU(int n){
            parent = new int[n];
            rank = new int[n];

            for(int i=0; i<n; i++){
                parent[i] = i;
            }
        }

        private int find(int x){
            if(parent[x] == x) return x;

            return parent[x] = find(parent[x]);
        }

        private boolean union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);
            if(rootA == rootB) return false;

            if(rank[rootA] < rank[rootB]){
                parent[rootA] = rootB;
            } else if(rank[rootA] > rank[rootB]){
                parent[rootB] = rootA;
            } else{
                parent[rootB] = rootA;
                rank[rootA]++;
            }

            return true;
        }
    }
}
