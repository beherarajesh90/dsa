package com.interviewprep.dsa.graphs.graphColoring;

import java.util.*;

//https://leetcode.com/problems/possible-bipartition/description/
public class PossibleBipartition {
    public boolean possibleBipartition(int n, int[][] dislikes) {

        return possibleBipartitionDFS(n, dislikes);

        // return possibleBipartitionBFS(n, dislikes);
    }

    // DFS
    private boolean possibleBipartitionDFS(int n, int[][] dislikes){
        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<=n; i++) graph.add(new ArrayList<>());

        for(int[] d: dislikes){
            int u = d[0];
            int v = d[1];
            graph.get(u).add(v);
            graph.get(v).add(u);

        }

        int[] color = new int[n+1];
        Arrays.fill(color, -1);

        for(int i=1; i<=n; i++){
            if(color[i] == -1){
                if(!dfs(i, 0, graph, color)) return false;
            }
        }

        return true;
    }

    private boolean dfs(int node, int c, List<List<Integer>> graph, int[] color){
        color[node] = c;
        for(int neighbour: graph.get(node)){
            if(color[neighbour] == -1){
                if(!dfs(neighbour, 1 - color[node], graph, color)) return false;
            } else if(color[neighbour] == color[node]){
                return false;
            }
        }

        return true;
    }

    // BFS
    private boolean possibleBipartitionBFS(int n, int[][] dislikes){
        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<=n; i++) graph.add(new ArrayList<>());

        for(int[] d: dislikes){
            int u = d[0];
            int v = d[1];
            graph.get(u).add(v);
            graph.get(v).add(u);

        }

        int[] color = new int[n+1];
        Arrays.fill(color, -1);

        for(int i=1; i<=n; i++){

            if(color[i] != -1) continue;
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            color[i] = 0;

            while(!q.isEmpty()){
                int node = q.poll();
                for(int neighbour: graph.get(node)){
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
}
