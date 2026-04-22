package com.interviewprep.dsa.recursion.backtracking;

//https://leetcode.com/problems/unique-paths-iii/description/
public class UniquePathsIII {
    private int res = 0;
    public int uniquePathsIIIOptimized(int[][] grid) {
        int sx=0, sy=0, empty = 0;
        for(int i=0;i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1){
                    sx = i;
                    sy = j;
                } else if(grid[i][j] == 0){
                    empty++;
                }
            }
        }
        backtrackOptimized(grid, sx, sy, empty+1);   //we add 1 to empty to include the grid with 2
        return res;
    }

    private void backtrackOptimized(int[][] grid, int x, int y, int remain){
        if(x<0 || y<0 || x>=grid.length || y>=grid[0].length || grid[x][y]==-1){
            return;
        }
        if(grid[x][y] == 2){
            if(remain == 0) res++;
            return;
        }
        int temp = grid[x][y];
        grid[x][y] = -1;
        backtrackOptimized(grid, x, y+1, remain - 1);    //direction (0,1)
        backtrackOptimized(grid, x+1, y, remain - 1);    //direction (1,0)
        backtrackOptimized(grid, x, y-1, remain - 1);    //direction (0,-1)
        backtrackOptimized(grid, x-1, y, remain - 1);    //direction (-1,0)
        grid[x][y] = temp;
    }

    public int uniquePathsIII(int[][] grid) {
        int sx=0, sy=0, empty = 1;                  //we start empty from 1 to include the grid with 2
        for(int i=0;i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1){
                    sx = i;
                    sy = j;
                } else if(grid[i][j] == 0){
                    empty++;
                }
            }
        }
        backtrack(grid, sx, sy, empty);
        return res;
    }

    private void backtrack(int[][] grid, int x, int y, int remain){
        if(x<0 || y<0 || x>=grid.length || y>=grid[0].length || grid[x][y]==-1){
            return;
        }
        if(grid[x][y] == 2){
            if(remain == 0) res++;
            return;
        }

        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
        for(int[] d: dir){
            int temp = grid[x][y];
            grid[x][y] = -1;
            backtrack(grid, x + d[0], y + d[1], remain - 1);
            grid[x][y] = temp;
        }
    }
}
