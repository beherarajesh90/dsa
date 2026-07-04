package com.interviewprep.dsa.graphs.graphColoring;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/flower-planting-with-no-adjacent/description/
public class FlowerPlantingWithNoAdjacent {
    public int[] gardenNoAdj(int n, int[][] paths) {

        List<Integer>[] graph = new ArrayList[n];

        for(int i=0; i<n; i++) graph[i] = new ArrayList<>();

        for(int[] path: paths){
            int u = path[0] - 1;
            int v = path[1] - 1;
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] flowers = new int[n];
        for(int i=0; i<n; i++){

            boolean[] used = new boolean[5];

            for(int neighbour: graph[i]){
                used[flowers[neighbour]] = true;

            }
            for(int c=1; c<=4; c++){
                if(!used[c]){
                    flowers[i] = c;
                    break;
                }
            }
        }

        return flowers;
    }
}
