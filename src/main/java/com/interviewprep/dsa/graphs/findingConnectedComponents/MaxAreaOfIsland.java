package com.interviewprep.dsa.graphs.findingConnectedComponents;

//https://leetcode.com/problems/max-area-of-island/description/
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        // return maxAreaOfIslandDSU(grid);

        int maxArea = 0;

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1){
                    maxArea = Math.max(maxArea, dfs(i, j, grid));
                }
            }
        }

        return maxArea;

    }

    private int dfs(int i, int j, int[][] grid){
        if(i < 0 || j < 0 || i>=grid.length || j>=grid[0].length || grid[i][j] == 0){
            return 0;
        }

        grid[i][j] = 0;

        int area = 1;

        area+=dfs(i, j+1, grid);
        area+=dfs(i+1, j, grid);
        area+=dfs(i, j-1, grid);
        area+=dfs(i-1, j, grid);

        return area;
    }

    //DSU is overkill here. It will be very useful when in case of
    // 1. Dynamic connectivity (edges added over time)
    // 2. Multiple connectivity queries
    // 3. Offline merging problems (You repeatedly merge groups)
    // 4. Kruskal’s MST algorithm (cycle detection, edge sorting and union)
    // 5. Grid + dynamic changes + constraints
    private int maxAreaOfIslandDSU(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        DSU dsu = new DSU(m*n);

        int[][] dirs = {{0,1}, {1, 0}};

        // Step 1: activate land
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    int id = i * n + j;
                    dsu.size[id] = 1; // activate
                }
            }
        }

        //step:2 union
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0) continue;

                int id1 = i * n + j;

                for(int[] dir: dirs){
                    int nr = i + dir[0];
                    int nc = j + dir[1];
                    if(nr < m && nc < n && grid[nr][nc] == 1){
                        int id2 = nr * n + nc;
                        dsu.union(id1, id2);
                    }
                }
            }
        }

        return dsu.getMaxSize();
    }

    class DSU{
        int[] parent;
        int[] size;

        public DSU(int n){
            parent = new int[n];
            size = new int[n];
            for(int i=0; i<n; i++){
                parent[i] = i;
                size[i] = 0;
            }
        }

        private int find(int x){
            if(parent[x] == x) return x;

            return parent[x] = find(parent[x]);
        }

        private boolean union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);
            if(rootA == rootB){
                return false;
            }

            if(size[rootA] < size[rootB]){
                parent[rootA] = rootB;
                size[rootB] += size[rootA];
            } else{
                parent[rootB] = rootA;
                size[rootA] += size[rootB];
            }

            return true;
        }

        private int getMaxSize(){
            int maxSize = 0;
            for(int s: size){
                System.out.println(s);
                if(maxSize < s){
                    maxSize = s;
                }
            }
            return maxSize;
        }

    }
}
