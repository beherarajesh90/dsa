package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.*;

//https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time/description/
public class MinimumCostToReachDestinationInTime {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        // return minCostDijkstra(maxTime, edges, passingFees);

        return minCostDijkstraOptimal(maxTime, edges, passingFees);
    }

    // V = number of cities (n)
    // E = number of roads (edges.length)
    // T = maxTime
    // total relaxtions: O(E), pq insertion: O(log E)
    // time: O(E * logE)
    //track min time - optimal
    private int minCostDijkstraOptimal(int maxTime, int[][] edges, int[] passingFees){
        int n = passingFees.length;

        List<int[]>[] adj = buildAdjacencyList(edges, n);

        // (city, time, cost)
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 0, passingFees[0]});

        // min time to reach the city
        int[] minTime = new int[n];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        minTime[0] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int city = cur[0];
            int time = cur[1];
            int fee = cur[2];

            if(city == n-1) return fee;

            for(int[] neighbour: adj[city]){

                int nextCity = neighbour[0];
                int nextTime = time + neighbour[1];

                if(nextTime > maxTime) continue;

                if(nextTime < minTime[nextCity]){
                    minTime[nextCity] = nextTime;
                    int nextCost = fee + passingFees[nextCity];
                    pq.offer(new int[]{nextCity, nextTime, nextCost});
                }
            }
        }

        return -1;
    }


    // total relaxations: O(E * (T+1) = E * T), pq size: V*T, pq insertion: O(logVT)
    // time: O(ETlog(VT))
    //initial intuition
    private int minCostDijkstra(int maxTime, int[][] edges, int[] passingFees){
        int n = passingFees.length;

        List<int[]>[] adj = buildAdjacencyList(edges, n);

        // (city, time, cost)
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 0, passingFees[0]});

        int[][] cost = new int[n][maxTime+1];
        for(int i=0; i<n; i++){
            for(int j=0; j<=maxTime; j++){
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        cost[0][0] = passingFees[0];

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int city = cur[0];
            int time = cur[1];
            int fee = cur[2];

            // Skip outdated state
            if(fee > cost[city][time]) continue;

            if(city == n-1) return fee;

            for(int[] neighbour: adj[city]){

                int nextCity = neighbour[0];
                int nextTime = time + neighbour[1];

                if(nextTime > maxTime) continue;

                int nextCost = fee + passingFees[nextCity];

                if(nextCost >= cost[nextCity][nextTime]) continue;

                cost[nextCity][nextTime] = nextCost;
                pq.offer(new int[]{nextCity, nextTime, nextCost});
            }
        }

        return -1;
    }

    private List<int[]>[] buildAdjacencyList(int[][] edges, int n){
        List<int[]>[] adj = new ArrayList[n];

        for(int i=0; i<n; i++){
            adj[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];
            int time = edge[2];
            adj[from].add(new int[]{to, time});
            adj[to].add(new int[]{from, time});
        }

        return adj;
    }
}
