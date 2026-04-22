package com.interviewprep.dsa.recursion.recursiveSearch;

public class PathWithMaximumGold {
    private int maxGold = 0;
    public int getMaximumGold(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        // boolean[][] visited = new boolean[rows][cols];
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                if(grid[i][j]>0){
                    int curGold = backtrack(grid, i, j);
                    maxGold = Math.max( maxGold,curGold);
                }
            }
        }
        return maxGold;
    }

    private int backtrack(int[][] grid, int x, int y){
        if(x<0 || y<0 || x>=grid.length || y>=grid[0].length || grid[x][y]==0) return 0;
        int curGoldSum = grid[x][y];
        grid[x][y] = 0;
        int gold1 = curGoldSum + backtrack(grid, x, y+1 );
        int gold2 = curGoldSum + backtrack(grid, x+1, y );
        int gold3 = curGoldSum + backtrack(grid, x, y-1 );
        int gold4 = curGoldSum + backtrack(grid, x-1, y );
        grid[x][y] = curGoldSum;

        return Math.max(gold1, Math.max(gold2, Math.max(gold3, gold4)));
    }
}
