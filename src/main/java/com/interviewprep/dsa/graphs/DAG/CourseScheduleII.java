package com.interviewprep.dsa.graphs.DAG;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/course-schedule-ii/description/
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // return findOrderBFSTopoSort(numCourses, prerequisites);
        return findOrderDFSTopoSort(numCourses, prerequisites);
    }

    // DFS Topological Sort
    // time: O(V + E)
    private int[] findOrderDFSTopoSort(int numCourses, int[][] prerequisites){
        // build adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0; i<numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] prereq: prerequisites){

            int u = prereq[0];
            int v = prereq[1];

            adj.get(v).add(u);
        }
        // 0 = not visited, 1 = visiting, 2 = visited
        int[] state = new int[numCourses];
        List<Integer> order = new ArrayList<>();
        for(int i=0; i< numCourses; i++){
            if(state[i] == 0){
                if(!dfs(i, state, adj, order)){
                    return new int[0];
                }
            }
        }

        // reverse the post order
        int[] res = new int[numCourses];
        for(int i=0; i<numCourses; i++){
            res[i] = order.get(numCourses - 1 - i);
        }
        return res;
    }

    private boolean dfs(int course, int[] state, List<List<Integer>> graph, List<Integer> order){

        if(state[course] == 1) return false;

        if(state[course] == 2) return true;

        state[course] = 1;

        for(int nCourse: graph.get(course)){
            if(!dfs(nCourse, state, graph, order)) return false;
        }

        state[course] = 2;
        order.add(course);
        return true;
    }

    // kahns algorithm (BFS Topological sort)
    // time: O(V + E)
    private int[] findOrderBFSTopoSort(int numCourses, int[][] prerequisites){
        // build adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        int preLen = prerequisites.length;
        int[] inDegree = new int[numCourses];

        for(int i=0; i<numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] prereq: prerequisites){

            int u = prereq[0];
            int v = prereq[1];

            inDegree[u]++;
            adj.get(v).add(u);
        }

        Queue<Integer> q = new LinkedList<>();

        for(int course=0; course<inDegree.length; course++){
            if(inDegree[course] == 0) q.offer(course);
        }

        int[] res = new int[numCourses];
        int ind = 0;

        while(!q.isEmpty()){
            int course = q.poll();
            res[ind++] = course;
            for(int neighbourCourse: adj.get(course)){
                inDegree[neighbourCourse]--;
                if(inDegree[neighbourCourse] == 0) q.offer(neighbourCourse);
            }
        }

        return ind == numCourses ? res: new int[0];
    }
}
