package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloydWarshallAlgorithm {
    private static final int INF = 100000000; // Large value, but not Integer.MAX_VALUE
    static int[][] dist;
    static int[][] next;

    public static int[][] floydWarshall(int[][] graph) {
        int V = graph.length;

        //distance of i to j considering k as intermediary
        dist = new int[V][V];
        //next[i][j] = k: the next vertex after i to reach j
        //parent or predecessor
        next = new int[V][V];

        // Initialize distance matrix with the input graph
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if(i == j){
                    dist[i][j] = 0;
                    next[i][j] = -1;
                } else if(graph[i][j] == 0) {
                    dist[i][j] = INF;
                    next[i][j] = -1; // No path known yet
                } else{
                    dist[i][j] = graph[i][j];
//                    "If I'm currently at i and want to reach j, which vertex should I visit next?"
//                    Initially, only direct edges exist.
                    next[i][j] = j;
                }
            }
        }

        // Try every vertex as an intermediate
//        For each pair (i, j), check if going through vertex k improves the path.
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        if(hasNegativeCycle()) return null;

        return dist;
    }

    private static boolean hasNegativeCycle(){
        for(int i=0; i<dist.length; i++){
            if(dist[i][i] < 0){
                return true;
            }
        }
        return false;
    }

    private static List<Integer> getPath(int from, int to){

        if (next[from][to] == -1) {
            return Collections.emptyList(); // No path exists
        }

        List<Integer> path = new ArrayList<>();
        path.add(from);
        while (from!=to){
            from = next[from][to];
            path.add(from);
        }
        return path;
    }

    public static void main(String[] args) {

        //distance from i to j
        int[][] graph = {
                {0,1,0,10},
                {0,0,1,0},
                {0,0,0,1},
                {0,0,0,0}
        };

        floydWarshall(graph);
        int V = graph.length;

        for(int i=0; i<V; i++){
            for (int j=0; j<V; j++){
//                System.out.println("Path from " + i + " to " + j + ": " + getPath(i, j));
                System.out.print(next[i][j]+" ");
            }
            System.out.println();
        }

    }
}
