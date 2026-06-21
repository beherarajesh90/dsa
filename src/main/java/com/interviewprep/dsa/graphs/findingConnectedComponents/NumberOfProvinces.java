package com.interviewprep.dsa.graphs.findingConnectedComponents;

//https://leetcode.com/problems/number-of-provinces/
public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;
        DSU dsu = new DSU(n);
        int provinces = n;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(isConnected[i][j] == 1){
                    if(dsu.union(i, j)){
                        provinces--;
                    }
                }
            }
        }

        return provinces;
    }

    class DSU{
        int[] parent;
        int[] size;
        public DSU(int n){
            parent = new int[n];
            size = new int[n];

            for(int i=0; i<n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int x){
            if(parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }

        private boolean union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);

            if(rootA == rootB){
                return false;
            }

            if(size[rootA] < size[rootB]){
                parent[rootA] = rootB;
                size[rootB] += size[rootA];
            } else{
                parent[rootB] = rootA;
                size[rootA] += size[rootB];
            }

            return true;
        }
    }
}
