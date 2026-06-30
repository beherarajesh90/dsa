package com.interviewprep.dsa.graphs.minimumSpanningTree;

import java.util.*;

//https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
public class MinimumSpanningTree {
    public int spanningTree(int V, int[][] edges) {

        // return spanningTreeUsingKrushkalsAlgo(V, edges);

        return spanningTreeUsingPrimsAlgo(V, edges);

    }

    private int spanningTreeUsingPrimsAlgo(int V, int[][] edges){

        // build adjacency list
        List<int[]>[] adj = new ArrayList[V];

        for(int i=0; i<V; i++){
            adj[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }

        int mstWeight = 0;

        // vertex, weight
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{0, 0});

        boolean[] visited = new boolean[V];

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int node = cur[0];
            int weight = cur[1];

            if(visited[node]) continue;
            visited[node] = true;
            mstWeight+=weight;

            for(int[] neighbour: adj[node]){
                int nextNode = neighbour[0];
                int nextWeight = neighbour[1];

                if(!visited[nextNode])
                    pq.offer(new int[]{nextNode, nextWeight});
            }
        }

        return mstWeight;

    }

    private int spanningTreeUsingKrushkalsAlgo(int V, int[][] edges){
        // sort edges based on weight
        Arrays.sort(edges, (e1, e2) -> e1[2] - e2[2]);

        int mstWeight = 0;
        int edgesVisited = 0;

        DSU dsu = new DSU(V);

        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if(dsu.union(u, v)){
                mstWeight+=w;
                edgesVisited++;

                if(edgesVisited == V-1) break;
            }
        }

        return mstWeight;
    }

    class DSU{
        int[] parent;
        int[] rank;

        DSU(int n){
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
