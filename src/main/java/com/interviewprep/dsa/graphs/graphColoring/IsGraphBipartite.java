package com.interviewprep.dsa.graphs.graphColoring;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/is-graph-bipartite/description/
public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {

        // return isBipartiteDSU(graph);

        // return isBipartiteBFS(graph);

        return isBipartiteDFS(graph);


    }
    // time: O(V+E)
    private boolean isBipartiteDFS(int[][] graph){
        int n = graph.length;
        // -1 = unvisited, 0 = red, 1 = blue
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for(int i=0; i<n; i++){
            if(color[i] == -1){
                if(!dfs(i, 0, graph, color)) return false;
            }
        }

        return true;
    }

    private boolean dfs(int node, int c, int[][] graph, int[] color){

        color[node] = c;

        for(int neighbour: graph[node]){
            if(color[neighbour] == -1){
                if(!dfs(neighbour, 1 - color[node], graph, color)) return false;
            } else if(color[node] == color[neighbour]){
                return false;
            }
        }

        return true;
    }

    // time: O(V+E)
    private boolean isBipartiteBFS(int[][] graph){
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for(int i=0; i<n; i++){

            if(color[i] != -1) continue;

            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            color[i]=0;

            while(!q.isEmpty()){
                int node = q.poll();
                for(int neighbour: graph[node]){
                    if(color[neighbour] == -1){
                        color[neighbour] = 1 - color[node];
                        q.offer(neighbour);
                    } else if(color[neighbour] == color[node]){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private int[] parent;
    private int[] rank;
    // time: O((V+E) * alpha(V))
    private boolean isBipartiteDSU(int[][] graph){
        int n = graph.length;
        parent = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++) parent[i]=i;

        for(int u=0; u<n; u++){
            if(graph[u].length == 0) continue;

            // union all neighbours of the node
            for(int i=1; i<graph[u].length; i++){
                union(graph[u][0], graph[u][i]);
            }

            if(find(u) == find(graph[u][0])) return false;
        }

        return true;
    }

    private int find(int x){
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    private void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return;

        if(rank[rootA] < rank[rootB]){
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parent[rootB] = rootA;
        if(rank[rootA] == rank[rootB]) rank[rootA]++;
    }
}
