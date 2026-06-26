package com.interviewprep.dsa.graphs.findingShortestPath;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/description/
public class MinimumMovesToMoveABoxToTheirTargetLocation {
    // Directions: Up, Right, Down, Left
    private final int[][] DIRS = {{-1,0},{0,1},{1,0},{0,-1}};

    // time: O((m*n)^3)
    public int minPushBox(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int playerRow = 0, playerCol = 0;
        int boxRow = 0, boxCol = 0;
        int targetRow = 0, targetCol = 0;

        // Find initial positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 'S') {
                    playerRow = i;
                    playerCol = j;
                }

                if (grid[i][j] == 'B') {
                    boxRow = i;
                    boxCol = j;
                }

                if (grid[i][j] == 'T') {
                    targetRow = i;
                    targetCol = j;
                }
            }
        }

        Queue<State> q = new LinkedList<>();
        q.offer(new State(boxRow, boxCol, playerRow, playerCol, 0));

        boolean[][][][] visited = new boolean[m][n][m][n];
        visited[boxRow][boxCol][playerRow][playerCol] = true;

        while(!q.isEmpty()){
            State cur = q.poll();

            //target reached
            if(cur.boxRow == targetRow && cur.boxCol == targetCol) return cur.pushes;

            //try pushing in all 4 directions
            for(int[] dir: DIRS){

                //new box position
                int newBoxRow = cur.boxRow + dir[0];
                int newBoxCol = cur.boxCol + dir[1];

                //check new box position is valid
                if(!isValid(newBoxRow, newBoxCol, grid)) continue;

                //required player position - player must stand in opposite direction
                int reqPlayerRow = cur.boxRow - dir[0];
                int reqPlayerCol = cur.boxCol - dir[1];

                //check new player position is valid
                if(!isValid(reqPlayerRow, reqPlayerCol, grid)) continue;

                // can player reach required position
                if(!canReach(cur.playerRow, cur.playerCol, reqPlayerRow, reqPlayerCol, cur.boxRow, cur.boxCol, grid)) continue;

                // After push, player stands at old box position
                int newPlayerRow = cur.boxRow;
                int newPlayerCol = cur.boxCol;

                if(visited[newBoxRow][newBoxCol][newPlayerRow][newPlayerCol]) continue;

                visited[newBoxRow][newBoxCol][newPlayerRow][newPlayerCol] = true;

                q.offer(new State(newBoxRow, newBoxCol, newPlayerRow, newPlayerCol, cur.pushes + 1));
            }
        }

        return -1;

    }

    private boolean canReach(int startRow, int startCol, int targetRow, int targetCol, int boxRow, int boxCol, char[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startRow, startCol});

        boolean[][] visited = new boolean[m][n];
        visited[startRow][startCol] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(cur[0] == targetRow && cur[1] == targetCol) return true;

            for(int[] dir: DIRS){
                int nr = cur[0] + dir[0];
                int nc = cur[1] + dir[1];

                if(!isValid(nr, nc, grid)) continue;

                // Treat box as wall
                if(nr == boxRow && nc == boxCol) continue;

                if(visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }

        return false;
    }

    private boolean isValid(int row, int col, char[][] grid){
        return row>=0 && row<grid.length
                && col>=0 && col<grid[0].length
                && grid[row][col]!='#';
    }


    static class State{
        int boxRow;
        int boxCol;

        int playerRow;
        int playerCol;

        int pushes;

        State(int br, int bc, int pr, int pc, int pushes) {
            this.boxRow = br;
            this.boxCol = bc;
            this.playerRow = pr;
            this.playerCol = pc;
            this.pushes = pushes;
        }
    }
}
