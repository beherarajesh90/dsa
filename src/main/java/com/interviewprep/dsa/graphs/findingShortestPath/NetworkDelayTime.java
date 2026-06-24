package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.*;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {

        // return networkDelayTimeUsingDijkstraAlgo(times, n, k);

        return networkDelayTimeUsingBellmanFordAlgo(times, n, k);
    }

    //using BFA its fast here
    private int networkDelayTimeUsingBellmanFordAlgo(int[][] times, int n, int k){

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        //relax all the edges V-1 times
        for(int i=0; i<n-1; i++){

            boolean updated = false;

            for(int[] edge: times){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if(dist[u]!=Integer.MAX_VALUE && dist[u] + w < dist[v]){
                    dist[v] = dist[u] + w;
                    updated = true;
                }
            }

            if(!updated) break;

        }

        //negative cycle checck not required here as the weights are all +ve

        //max dist
        int maxDist = 0;
        for(int i=1; i<=n; i++){
            if(dist[i] == Integer.MAX_VALUE) return -1;
            maxDist = Math.max(maxDist, dist[i]);
        }
        return maxDist;
    }

    //using dijkstra's - optimal(interview solution)
    private int networkDelayTimeUsingDijkstraAlgo(int[][] times, int n, int k){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        boolean[] visited = new boolean[n+1];

        //minHeap
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});

        List<int[]>[] adj = buildAdjacencyMatrix(n, times);

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if(visited[curNode]) continue;
            visited[curNode] = true;

            for(int[] edge: adj[curNode]){
                int v = edge[0];
                int w = edge[1];
                if(!visited[v] && curDist + w < dist[v]){
                    dist[v] = curDist + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        int maxDist = 0;
        for(int i=1; i<=n; i++){
            if(dist[i] == Integer.MAX_VALUE) return -1;
            maxDist = Math.max(maxDist, dist[i]);
        }
        return maxDist;
    }

    private List<int[]>[] buildAdjacencyMatrix(int n, int[][] times){
        List<int[]>[] adj = new ArrayList[n+1];

        for(int i=0; i<adj.length; i++){
            adj[i] = new ArrayList<>();
        }

        for(int[] edge: times){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj[u].add(new int[]{v, w});
        }

        return adj;
    }
}
