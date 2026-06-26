package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/path-with-minimum-effort/description/
public class PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        return minimumEffortPathUsingDijkstras(heights);
    }

    // Binary Search + BFS
    //Time: O(mn × log(MaxHeightDifference))[MaxHeightDifference = 10^6]
    private int minimumEffortPathUsingBFS(int[][] heights){

        int rows = heights.length, cols = heights[0].length;
        int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
        int low = 1, high = 1_000_000;  //given in constraints

        while(low < high){

            int mid = (low + high) / 2;

            //BFS

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, 0});   //(r, c)

            boolean[][] visited = new boolean[rows][cols];
            visited[0][0] = true;

            while(!q.isEmpty()){
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                for(int[] dir: dirs){
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr>=0 && nr<rows && nc>=0 && nc<cols && !visited[nr][nc] && Math.abs(heights[nr][nc] - heights[r][c]) <= mid){
                        q.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }

            if(visited[rows-1][cols-1]){
                high = mid;
            } else{
                low = mid+1;
            }
        }

        return low;
    }

    //Dijkstra's Algo
    // Time: O(m * n * log(m * n))
    private int minimumEffortPathUsingDijkstras(int[][] heights){

        int rows = heights.length, cols = heights[0].length;
        int[][] effort = new int[rows][cols];
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        for (int[] row : effort) Arrays.fill(row, Integer.MAX_VALUE);
        effort[0][0] = 0;

        // Min-heap: (effort, row, col)
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.offer(new int[]{0, 0, 0});

        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int e = curr[0], r = curr[1], c = curr[2];

            // Reached destination
            if (r == rows - 1 && c == cols - 1) return e;

            // Skip if we already found a better path to this cell
            if (e > effort[r][c]) continue;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    int newEffort = Math.max(e, Math.abs(heights[nr][nc] - heights[r][c]));
                    if (newEffort < effort[nr][nc]) {
                        effort[nr][nc] = newEffort;
                        heap.offer(new int[]{newEffort, nr, nc});
                    }
                }
            }
        }

        return effort[rows - 1][cols - 1];
    }
}
