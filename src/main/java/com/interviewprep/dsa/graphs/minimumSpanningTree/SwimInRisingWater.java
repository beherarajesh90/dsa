package com.interviewprep.dsa.graphs.minimumSpanningTree;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/swim-in-rising-water/description/
public class SwimInRisingWater {
    public int swimInWater(int[][] grid) {

        return swimInWaterUsingBinarySearch(grid);

        // return swimInWaterUsingDijkstras(grid);

        // return swimInWaterUsingDSU(grid);
    }

    // time: O(n^2 alpha(n^2)) = O(n^2) [alpha(n^2) = O(1)]
    private int swimInWaterUsingDSU(int[][] grid){
        int n = grid.length;

        // [height[row, col]]
        int[][] height = new int[n*n][2];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                height[grid[i][j]] = new int[]{i, j};
            }
        }

        DSU dsu = new DSU(n*n);
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        boolean[][] active = new boolean[n][n];

        for(int t=0; t<n*n; t++){
            int row = height[t][0];
            int col = height[t][1];
            active[row][col] = true;
            int id = row * n + col;
            for(int[] dir: dirs){
                int nr = row + dir[0];
                int nc = col + dir[1];

                // out of the grid
                if(nr<0 || nr>=n || nc<0 || nc>=n) continue;

                // neighbour not active just continue
                if(!active[nr][nc]) continue;

                int nid = nr * n + nc;
                dsu.union(id, nid);

            }
            if(dsu.find(0) == dsu.find(n*n-1)) return t;
        }

        return -1;
    }

    class DSU{
        int[] parent;
        int[] rank;
        public DSU(int n){
            parent = new int[n];
            rank = new int[n];

            for(int i=0; i<n; i++) parent[i] = i;
        }

        private int find(int x){
            if(parent[x] == x) return x;

            return parent[x] = find(parent[x]);
        }

        private boolean union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);

            if(rootA == rootB) return false;

            if(rank[rootA] < rank[rootB]){
                parent[rootA] = rootB;
            } else if(rank[rootA] > rank[rootB]){
                parent[rootB] = rootA;
            } else{
                parent[rootB] = rootA;
                rank[rootA]++;
            }

            return true;
        }
    }

    // time: O(n^2 log n)
    private int swimInWaterUsingDijkstras(int[][] grid){
        int n = grid.length;

        // elevation, i, j - elevation is maxElevation of the path. (choose minmax)
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{grid[0][0], 0, 0});

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int elevation = cur[0];
            int row = cur[1];
            int col = cur[2];

            visited[row][col] = true;
            if(row == n-1 && col == n-1) return elevation;

            for(int[] dir: dirs){
                int nr = row + dir[0];
                int nc = col + dir[1];

                // out of grid
                if(nr<0 || nr>=n || nc<0 || nc>=n) continue;

                // continue if next cell already visited
                if(visited[nr][nc]) continue;

                int nextElevation = grid[nr][nc];

                // continue with the max elevation of the path if next elevation is smaller
                pq.offer(new int[]{Math.max(nextElevation, elevation), nr, nc});
            }
        }

        return -1;
    }

    // binary search + bfs
    // time: O(n^2 logn)
    private int swimInWaterUsingBinarySearch(int[][] grid){
        int n = grid.length;
        int low = grid[0][0];
        int high = n * n - 1;
        int res = -1;
        while(low<=high){
            int mid = (low+high)/2;

            if(canReach(grid, mid)){
                res = mid;
                high = mid - 1;
            } else{
                low = mid+1;
            }
        }

        return res;
    }

    private boolean canReach(int[][] grid, int waterLevel){
        // cannot reach from (0,0) to (n-1,n-1) if standing water level at 0,0 is greater than waterLevel
        if(grid[0][0] > waterLevel) return false;

        int n = grid.length;
        if(grid[n-1][n-1] > waterLevel) return false;

        // row, col
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        while(!q.isEmpty()){
            int[] cur = q.poll();

            int row = cur[0];
            int col = cur[1];

            if(row == n-1 && col == n-1) return true;

            for(int[] dir: dirs){
                int nr = row + dir[0];
                int nc = col + dir[1];

                if(nr<0 || nr>=n || nc<0 || nc>=n) continue;

                if(visited[nr][nc]) continue;

                if(grid[nr][nc] > waterLevel) continue;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }

        return false;
    }
}
