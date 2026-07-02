package com.interviewprep.dsa.graphs.DAG;

import java.util.*;

//https://leetcode.com/problems/course-schedule-iv/
public class CourseScheduleIV {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {

        // return checkIfPrerequisiteTopoSort(numCourses, prerequisites, queries);

        // (interview approach)
        // return checkIfPrerequisiteDFS(numCourses, prerequisites, queries);

        // (interview approach)
        return checkIfPrerequisiteFloydWarshall(numCourses, prerequisites, queries);
    }

    // time: O(V^3 + E + m)
    private List<Boolean> checkIfPrerequisiteFloydWarshall(int numCourses, int[][] prerequisites, int[][] queries) {

        // path from prereq to course
        boolean[][] isPreReq = new boolean[numCourses][numCourses];

        for(int[] p: prerequisites){
            isPreReq[p[0]][p[1]] = true;
        }

        for(int k=0; k<numCourses; k++){
            for(int i=0; i<numCourses; i++){
                for(int j=0; j<numCourses; j++){
                    isPreReq[i][j] = isPreReq[i][j] || (isPreReq[i][k] && isPreReq[k][j]);
                }
            }
        }

        List<Boolean> res = new ArrayList<>();
        for(int[] q: queries){
            res.add(isPreReq[q[0]][q[1]]);
        }
        return res;
    }

    // DP + DFS
    // time: O(V * (V+E) + m) [V = courses, E = prerequisites, m = queries]
    private List<Boolean> checkIfPrerequisiteDFS(int numCourses, int[][] prerequisites, int[][] queries) {
        int[][] isPreReq = new int[numCourses][numCourses];

        // build adjacency matrix
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0; i<numCourses; i++){
            adj.add(new ArrayList<>());
            Arrays.fill(isPreReq[i], -1);
        }

        for(int[] prereq : prerequisites){
            int p = prereq[0];
            int c = prereq[1];
            adj.get(p).add(c);
            isPreReq[p][c] = 1;
        }

        List<Boolean> res = new ArrayList<>();

        for(int[] q: queries){
            int p = q[0];
            int c = q[1];
            res.add(dfs(p, c, isPreReq, adj));
        }

        return res;
    }

    private boolean dfs(int p, int c, int[][] isPreReq, List<List<Integer>> adj){

        if(isPreReq[p][c]!=-1) return isPreReq[p][c] == 1;

        for(int nP: adj.get(p)){
            if(dfs(nP, c, isPreReq, adj)){
                isPreReq[p][c] = 1;
                return true;
            }
        }
        isPreReq[p][c] = 0;
        return false;
    }

    // topo sort
    // time: O(V∗(V+E)+m)
    private List<Boolean> checkIfPrerequisiteTopoSort(int numCourses, int[][] prerequisites, int[][] queries) {

        int[] inDegree = new int[numCourses];

        List<List<Integer>> adj = new ArrayList<>();
        List<Set<Integer>> isPreReq = new ArrayList<>();    // course(prereq)

        for(int i=0; i<numCourses; i++){
            adj.add(new ArrayList<>());
            isPreReq.add(new HashSet<>());
        }

        for(int[] prereq: prerequisites){
            int p = prereq[0];
            int c = prereq[1];

            adj.get(p).add(c);
            inDegree[c]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<numCourses; i++){
            if(inDegree[i] == 0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int p = q.poll();

            for(int nP: adj.get(p)){
                isPreReq.get(nP).add(p);
                isPreReq.get(nP).addAll(isPreReq.get(p));
                inDegree[nP]--;
                if(inDegree[nP] == 0){
                    q.offer(nP);
                }
            }
        }

        List<Boolean> res = new ArrayList<>();
        for(int[] qr: queries){
            int p = qr[0];
            int c = qr[1];
            res.add(isPreReq.get(c).contains(p));
        }

        return res;

    }
}
