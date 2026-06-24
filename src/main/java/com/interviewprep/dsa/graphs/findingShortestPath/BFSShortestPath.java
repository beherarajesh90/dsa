package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.*;

public class BFSShortestPath {
//    1---0   7---6
//    |   | / | / |
//    2   3---4---5

    public static void main(String[] args) {
        //no of vertices
        int v = 8;

        //adjacency list to store the connected neighbours
        List<List<Integer>> adj = new ArrayList<>(v);

        for(int i=0; i<v; i++){
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 0, 3);
        addEdge(adj, 1, 2);
        addEdge(adj, 3, 4);
        addEdge(adj, 3, 7);
        addEdge(adj, 4, 5);
        addEdge(adj, 4, 6);
        addEdge(adj, 4, 7);
        addEdge(adj, 5, 6);
        addEdge(adj, 6, 7);

        int source = 0, dest = 6;
        printShortestDistance(adj, source, dest, v);
    }

    private static void addEdge(List<List<Integer>> adj, int s, int d){
        adj.get(s).add(d);
        adj.get(d).add(s);
    }

    private static void printShortestDistance(List<List<Integer>> adj, int s, int d, int v){

        //predecessor array to store the predecessor of each node
        int[] pred = new int[v];
        int[] dist = new int[v];

        for (int i=0; i<v; i++){
            pred[i] = -1;
            dist[i] = Integer.MAX_VALUE;
        }

        //distance to source itself is 0
        dist[s] = 0;

        if(bfs(adj, s, d, pred, dist)){
            printPath(pred, s, d);
        } else {
            System.out.println("Path does not exist");
        }

    }

    private static boolean bfs(List<List<Integer>> adj, int s, int d, int[] pred, int[] dist){
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);

        while (!q.isEmpty()){
            int cur = q.poll();
            if(cur == d) return true;
            for (int neighbour :adj.get(cur)){
                if(dist[neighbour] == Integer.MAX_VALUE){
                    dist[neighbour] = dist[cur] + 1;
                    pred[neighbour] = cur;
                    q.offer(neighbour);
                }
            }
        }

        return false;

    }

    private static void printPath(int[] pred, int s, int d) {
        List<Integer> path = new ArrayList<>();

        for (int i=d; i>=0; i = pred[i]){
            path.add(i);
        }

        Collections.reverse(path);
        System.out.println(path);
    }


}
