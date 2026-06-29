package com.interviewprep.dsa.graphs.cycleDetection;

//https://leetcode.com/problems/redundant-connection-ii/description/
public class RedundantConnectionII {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        // an extra edge can lead to
        //  1. two parents
        //  2. cycle
        //  3. cycle + two parents

        int n = edges.length;

        int[] parent = new int[n+1];

        for(int i=1; i<=n; i++){
            parent[i] = i;
        }

        int[] candidate1 = null;
        int[] candidate2 = null;

        // step: 1 - detect a node with two parents
        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            if(parent[v] != v){
                // existing parent edge
                candidate1 = new int[]{parent[v], v};

                // conflicting parent edge
                candidate2 = new int[]{u, v};

                //invalidate second edge
                edge[1] = 0;
            } else{
                parent[v] = u;
            }
        }

        DSU dsu = new DSU(n);
        // step: 2 - union find
        for(int[] edge: edges){

            // ignore the conflicting edge
            if(edge[1] == 0) continue;

            int u = edge[0];
            int v = edge[1];
            if(!dsu.union(u,v)){

                // only cycle
                if(candidate1 == null) return edge;

                // two parents + cycle
                return candidate1;
            }
        }

        // two parents
        return candidate2;
    }

    class DSU{
        int[] parent;

        public DSU(int n){
            parent = new int[n+1];

            for(int i=1; i<=n; i++){
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

            parent[rootB] = rootA;
            return true;
        }
    }
}
