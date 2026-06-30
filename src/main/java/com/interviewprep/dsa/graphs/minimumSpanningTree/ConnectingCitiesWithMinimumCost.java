package com.interviewprep.dsa.graphs.minimumSpanningTree;

import java.util.*;

//https://www.propeers.in/roadmaps/69692150442404dc257236a1/connecting-cities-with-minimum-cost?todoItemId=69b486808789fde24c0ec874
public class ConnectingCitiesWithMinimumCost {
    public int connectingCitiesWithMinimumCost(int n, int[][] connections) {
        // return connectingCitiesWithMinimumCostKruskals(n, connections);

        return connectingCitiesWithMinimumCostPrims(n, connections);
    }

    private int connectingCitiesWithMinimumCostPrims(int n, int[][] connections){

        List<int[]>[] adj = new ArrayList[n+1];

        for(int i=1; i<n+1; i++) adj[i] = new ArrayList<>();

        for(int[] con: connections){
            adj[con[0]].add(new int[]{con[1], con[2]});
            adj[con[1]].add(new int[]{con[0], con[2]});
        }

        // vertex, cost
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{1, 0});

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

        for(int i=1; i<n+1; i++){
            if(!visited[i]) return -1;
        }

        return mstCost;

    }

    private int connectingCitiesWithMinimumCostKruskals(int n, int[][] connections){

        // sort
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);

        DSU dsu = new DSU(n+1);
        int mstCost = 0;
        int edgesVisited = 0;


        for(int[] connection: connections){
            int u = connection[0];
            int v = connection[1];
            int cost = connection[2];
            if(dsu.union(u, v)){
                mstCost += cost;
                edgesVisited++;
                if(edgesVisited == n-1) break;
            }
        }

        return edgesVisited != n-1 ? -1 : mstCost;

    }

    class DSU{
        int[] parent;
        int[] rank;
        public DSU(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i=0; i<n; i++) parent[i] = i;
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
