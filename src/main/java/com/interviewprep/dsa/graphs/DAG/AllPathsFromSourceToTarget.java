package com.interviewprep.dsa.graphs.DAG;

import java.util.*;

//https://leetcode.com/problems/all-paths-from-source-to-target/description/
public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        return allPathsSourceTargetBFS(graph);

        // return allPathsSourceTargetDfsBacktrack(graph);

        // return allPathsSourceTargetDFSOptimal(graph);
    }

    //al time complexities: O(P * L) = O(P*V)(L<=V) [P = no of paths, L = length of list, V = no of vertices]

    // DFS + memoization
    public List<List<Integer>> allPathsSourceTargetDFSOptimal(int[][] graph) {
        Map<Integer, List<List<Integer>>> memo = new HashMap<>();
        return allPaths(graph, 0, memo);
    }

    private List<List<Integer>> allPaths(int[][] graph, int node, Map<Integer, List<List<Integer>>> memo) {
        if (memo.containsKey(node)) return memo.get(node);

        List<List<Integer>> paths = new ArrayList<>();

        // Base case: we reached the target
        if (node == graph.length - 1) {
            paths.add(new ArrayList<>(List.of(node)));
            memo.put(node, paths);
            return paths;
        }

        // For each neighbor, get all paths from neighbor to target and prepend current node
        for (int neighbor : graph[node]) {
            for (List<Integer> subPath : allPaths(graph, neighbor, memo)) {
                List<Integer> fullPath = new ArrayList<>();
                fullPath.add(node);
                fullPath.addAll(subPath);
                paths.add(fullPath);
            }
        }

        memo.put(node, paths);
        return paths;
    }

    // DFS + backtrack
    private List<List<Integer>> allPathsSourceTargetDfsBacktrack(int[][] graph) {

        List<List<Integer>> res = new ArrayList<>();
        dfsBacktrack(0, new ArrayList<>(), res, graph);
        return res;
    }

    private void dfsBacktrack(int source, List<Integer> path, List<List<Integer>> res, int[][] graph){

        path.add(source);
        System.out.println(path);
        if(source == graph.length-1) res.add(new ArrayList<>(path));

        for(int neighbour: graph[source]){
            dfsBacktrack(neighbour, path, res, graph);
        }

        path.remove(path.size()-1);
    }

    // BFS
    private List<List<Integer>> allPathsSourceTargetBFS(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<List<Integer>> q = new LinkedList<>();
        q.offer(new ArrayList<>(List.of(0)));

        while(!q.isEmpty()){
            List<Integer> path = q.poll();
            int lastNode = path.get(path.size() - 1);

            if(lastNode == graph.length-1){
                res.add(path);
            }

            for(int neighbour: graph[lastNode]){
                List<Integer> newPath = new ArrayList<>();
                newPath.addAll(path);
                newPath.add(neighbour);
                q.offer(newPath);
            }
        }

        return res;
    }
}
