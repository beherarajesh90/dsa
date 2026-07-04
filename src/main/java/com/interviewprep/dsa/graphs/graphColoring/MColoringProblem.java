package com.interviewprep.dsa.graphs.graphColoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://www.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1
public class MColoringProblem {
    boolean graphColoring(int v, int[][] edges, int m) {
        // code here
        int[] color = new int[v];
        //0 - no color
        Arrays.fill(color, 0);

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<v; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        if(!graphColoringUtil(0, v, m, graph, color)){
            return false;
        }

        return true;

    }

    private boolean graphColoringUtil(int v, int V, int m, List<List<Integer>> graph, int[] color){

        if(v == V) return true;

        for(int c=1; c<=m; c++){

            if(!isSafe(v, c, graph, color)) continue;

            color[v] = c;

            if(graphColoringUtil(v+1, V, m, graph, color)) return true;

            color[v] = 0;
        }

        return false;
    }

    private boolean isSafe(int v, int c, List<List<Integer>> graph, int[] color){
        for(int neighbour: graph.get(v)){
            if(color[neighbour] == c) return false;
        }

        return true;
    }
}
