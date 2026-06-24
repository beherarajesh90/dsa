package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.*;

public class CheapestFlightsWithinKStops {

    //dijkstra's algo with best flights - optimal(interview solution)
    // Time: O(k * E * log(k * E)) because each city can be expanded at most O(k) times (with decreasing flight counts), generating O(kE) heap states, and each heap operation costs O(log(kE)).
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        // Build adjacency list
        List<int[]>[] adj = new ArrayList[n];
        for(int i=0; i<n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int[] flight: flights){
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            adj[from].add(new int[]{to, price});
        }

        // State: {cost, city, flights taken}, ordered by cost
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        pq.offer(new int[]{0, src, 0});     //{cost, city, flights taken}

        // Fewest flights used among expanded states per city
        int[] bestFlights = new int[n];
        Arrays.fill(bestFlights, Integer.MAX_VALUE);

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curCityCost = cur[0];
            int curCity = cur[1];
            int flightsTaken = cur[2];

            if(curCity == dst) return curCityCost;

            // Discard if out of flights or dominated by an earlier pop
            if(flightsTaken > k || flightsTaken >= bestFlights[curCity]) continue;

            bestFlights[curCity] = flightsTaken;

            for(int[] neighbour: adj[curCity]){
                pq.offer(new int[]{curCityCost + neighbour[1], neighbour[0], flightsTaken+1});
            }
        }

        return -1;
    }

    public int findCheapestPriceUsingMap(int n, int[][] flights, int src, int dst, int k) {

        Map<Integer, List<int[]>> adj = new HashMap<>();
        for(int[] flight: flights){
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            adj.computeIfAbsent(from, x -> new ArrayList<>()).add(new int[]{to, price});
        }

        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        //{cost, city, stops}
        pq.offer(new int[]{0, src, 0});

        // key = city + stops, value = cost
        Map<String, Integer> visited = new HashMap<>();

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curCityCost = cur[0];
            int curCity = cur[1];
            int stops = cur[2];

            //cur city is destination return cost
            if(curCity == dst) return curCityCost;

            // if stops exceeds k just continue
            if(stops > k) continue;

            //no routes from the cur city just continue
            if(!adj.containsKey(curCity)) continue;

            for(int[] neigh: adj.get(curCity)){
                int neighCity = neigh[0];
                int neighCityCost = neigh[1];

                String key = neighCity+","+stops;
                if(!visited.containsKey(key) || curCityCost + neighCityCost < visited.get(key)){
                    visited.put(key, curCityCost + neighCityCost);
                    pq.offer(new int[]{curCityCost + neighCityCost, neighCity, stops+1});
                }
            }
        }

        return -1;
    }
}
