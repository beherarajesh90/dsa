package com.interviewprep.dsa.graphs.cycleDetection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/course-schedule/description/
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // return canFinishTopologicalSort(numCourses, prerequisites);

        return canFinishDFS(numCourses, prerequisites);
    }

    // time: O(V + E)
    private boolean canFinishDFS(int numCourses, int[][] prerequisites){
        //0 - not visited yet, 1 - visited, in current dfs stage, 2 - visited and exited the dfs stage
        int[] state = new int[numCourses];

        List<Integer>[] adj = new ArrayList[numCourses];
        for(int i=0; i<numCourses; i++){
            adj[i] = new ArrayList<>();
        }

        for(int[] pre: prerequisites){
            int course = pre[0];
            int prerequisite = pre[1];
            adj[prerequisite].add(course);
        }

        for(int i=0; i<numCourses; i++){
            if(state[i] == 0){
                if(hasCycle(i, state, adj)) return false;
            }
        }

        return true;

    }

    private boolean hasCycle(int curCourse,int[] state, List<Integer>[] adj){

        if(state[curCourse] == 1) return true;

        if(state[curCourse] == 2) return false;

        state[curCourse] = 1;

        for(int neighbour: adj[curCourse]){
            if(hasCycle(neighbour, state, adj)) return true;
        }

        state[curCourse] = 2;
        return false;
    }


    // time: O(V+E)
    private boolean canFinishTopologicalSort(int numCourses, int[][] prerequisites){

        int[] inDegree = new int[numCourses];

        List<Integer>[] adj = new ArrayList[numCourses];
        for(int i=0; i<numCourses; i++){
            adj[i] = new ArrayList<>();
        }

        for(int[] pre: prerequisites){
            int course = pre[0];
            int prerequisite = pre[1];
            adj[prerequisite].add(course);
            inDegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int course=0; course<numCourses; course++){
            if(inDegree[course] == 0) q.offer(course);
        }

        int visited = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            visited++;
            for(int course: adj[cur]){
                inDegree[course]--;
                if(inDegree[course] == 0) q.offer(course);
            }
        }

        return visited == numCourses;
    }
}
