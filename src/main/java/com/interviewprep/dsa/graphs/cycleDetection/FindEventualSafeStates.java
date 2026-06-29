package com.interviewprep.dsa.graphs.cycleDetection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/find-eventual-safe-states/description/
public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {

        // return eventualSafeNodesUsingTopoSort(graph);

        return eventualSafeNodesUsingDFS(graph);
    }

    private List<Integer> eventualSafeNodesUsingDFS(int[][] graph){

        int n = graph.length;

        // 0-not visited, 1-visiting, 2-already processed and safe, 3-unsafe
        int[] state = new int[n];

        List<Integer> res = new ArrayList<>();

        for(int i=0; i<n; i++){
            if(state[i] == 0){
                if(!isNodeSafe(i, state, graph)){
                    continue;
                }
            }
        }

        for(int i=0; i<n; i++){
            if(state[i] == 2){
                res.add(i);
            }
        }

        return res;
    }

    private boolean isNodeSafe(int node, int[] state, int[][] graph){
        if(state[node] == 1) return false;

        if(state[node] == 2) return true;

        state[node] = 1;
        for(int neighbour: graph[node]){
            if(!isNodeSafe(neighbour, state, graph)){
                state[neighbour] = 3;
                return false;
            }
        }

        state[node] = 2;
        return true;
    }

    // time: O(V+E)
    private List<Integer> eventualSafeNodesUsingTopoSort(int[][] graph){
        int n = graph.length;
        int[] outDegree = new int[n];

        // u -> v  to v -> u
        List<Integer>[] reverseGraph = new ArrayList[n];

        for(int i=0; i<reverseGraph.length; i++){
            reverseGraph[i] = new ArrayList<>();
        }

        for(int u=0; u<n; u++){
            outDegree[u] = graph[u].length;
            for(int v: graph[u]){
                reverseGraph[v].add(u);
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<n; i++){
            if(outDegree[i] == 0) q.offer(i);
        }

        boolean[] safe = new boolean[n];
        while(!q.isEmpty()){
            int cur = q.poll();
            System.out.println(cur);
            safe[cur] = true;
            for(int neighbour: reverseGraph[cur]){
                outDegree[neighbour]--;
                if(outDegree[neighbour] == 0){
                    q.offer(neighbour);
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for(int i=0; i<n; i++){
            if(safe[i]) result.add(i);
        }

        return result;
    }
}
