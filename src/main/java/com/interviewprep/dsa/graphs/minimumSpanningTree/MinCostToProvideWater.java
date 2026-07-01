package com.interviewprep.dsa.graphs.minimumSpanningTree;

import java.util.*;

//https://www.propeers.in/roadmaps/69692150442404dc257236a1/min-cost-to-provide-water?todoItemId=69b486808789fde24c0ec877
public class MinCostToProvideWater {

    public int minCostToProvideWater(int n, int[] wells, int[][] pipes) {
        // return minCostToProvideWaterUsingKruskals(n, wells, pipes);

        return minCostToProvideWaterUsingPrims(n, wells, pipes);
    }

    private int minCostToProvideWaterUsingPrims(int n, int[] wells, int[][] pipes){

        List<int[]>[] adj = new ArrayList[n+1];

        // connect dummy node(0) to all the houses
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();

        for(int i=1; i<=n; i++){
            adj[0].add(new int[]{i, wells[i-1]});
            adj[i].add(new int[]{0, wells[i-1]});
        }

        for(int[] edge: pipes){
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj[u].add(new int[]{v, cost});
            adj[v].add(new int[]{u, cost});
        }

        // vertex, cost
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{0, 0});

        boolean[] visited = new boolean[n+1];
        int mstCost = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];

            if(visited[node]) continue;

            visited[node] = true;
            mstCost += cost;

            for(int[] neighbour: adj[node]){
                int nextNode = neighbour[0];
                int nextCost = neighbour[1];
                if(!visited[nextNode]){
                    pq.offer(new int[]{nextNode, nextCost});
                }
            }
        }

        return mstCost;
    }

    private int minCostToProvideWaterUsingKruskals(int n, int[] wells, int[][] pipes){
        List<int[]> edges = new ArrayList<>();

        // consider 0 as dummy node
        // connect 0 to all the houses
        for(int i=1; i<=n; i++){
            int[] edge = new int[]{0, i, wells[i-1]};
            edges.add(edge);
        }

        // add existing edges
        for(int[] edge: pipes){
            edges.add(edge);
        }

        Collections.sort(edges, (a, b) -> a[2] - b[2]);

        DSU dsu = new DSU(n);
        int mstCost = 0;
        int edgesVisited = 0;

        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            if(dsu.union(u, v)){
                mstCost += cost;

                edgesVisited++;

                if(edgesVisited == n) break;

            }
        }

        return mstCost;

    }

    class DSU{
        int[] parent;
        int[] rank;
        public DSU(int n){
            parent = new int[n+1];
            rank = new int[n+1];
            // 0 is the dummy node
            for(int i=0; i<=n; i++){
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
