package com.interviewprep.dsa.graphs.findingConnectedComponents;

import java.util.*;

//https://leetcode.com/problems/accounts-merge/description/
public class AccountsMerge {
    int[] parent;
    int[] rank;

    // M = total number of email occurrences across all accounts.
    // E = number of unique emails.
    // Building the DSU (union operations): O(M · α(E))
    // Finding roots and grouping: O(E · α(E))
    // Sorting emails within groups: O(E log E) in the worst case.
    // Overall: O(M · α(E) + E log E)
    // Since α(E) (inverse Ackermann function) grows extremely slowly (less than 5 for any practical input),
    // this is effectively: how O(M + E log E)

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // return accountsMergeUsingDFS(accounts);

        int n = accounts.size();
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i]=i;
        rank = new int[n];
        Map<String,Integer> emailToName = new HashMap<>();

        // map email to name index if not exists. else update the parent of existing owner with root owner
        for(int i=0; i<n; i++){
            for(int j=1; j<accounts.get(i).size(); j++){
                String email = accounts.get(i).get(j);
                Integer owner = emailToName.get(email);
                if(owner == null){
                    emailToName.put(email, i);
                } else{
                    union(i, owner);
                }
            }
        }

        // group emails by owner
        Map<Integer, List<String>> groups = new HashMap<>();
        for(Map.Entry<String, Integer> entry: emailToName.entrySet()){
            String email = entry.getKey();
            Integer owner = entry.getValue();
            Integer rootOwner = find(owner);
            groups.computeIfAbsent(rootOwner, k -> new ArrayList<>()).add(email);
        }

        List<List<String>> result = new ArrayList<>();
        // sort the emails and prepend owner names
        for(Map.Entry<Integer, List<String>> entry: groups.entrySet()){
            int owner = entry.getKey();
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            emails.add(0, accounts.get(owner).get(0));
            result.add(emails);
        }

        return result;
    }

    private int find(int x){
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    private void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return;

        if(rank[rootA] < rank[rootB]){
            parent[rootA] = rootB;
        } else if(rank[rootA] > rank[rootB]){
            parent[rootB] = rootA;
        } else{
            parent[rootB] = rootA;
            rank[rootA]++;
        }
    }



    // N = number of accounts
    // E = total number of unique emails
    // M = total number of email entries across all accounts
    // DFS = O(E + M)
    // sorting O(E log E)
    // time: O(M + E log E), space: O(E + M)
    private List<List<String>> accountsMergeUsingDFS(List<List<String>> accounts){
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // build graph and map emails to name
        for(List<String> account: accounts){
            String name = account.get(0);
            String firstEmail = account.get(1);
            emailToName.put(firstEmail, name);
            graph.putIfAbsent(firstEmail, new HashSet<>());
            for(int i=2; i<account.size(); i++){
                String email = account.get(i);
                emailToName.put(email, name);

                graph.putIfAbsent(email, new HashSet<>());
                graph.get(firstEmail).add(email);
                graph.get(email).add(firstEmail);
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> result = new LinkedList<>();

        for(String email: graph.keySet()){

            if(visited.contains(email)) continue;

            Stack<String> s = new Stack<>();
            s.push(email);

            List<String> component = new ArrayList<>();

            // iterative dfs
            while(!s.isEmpty()){
                String curEmail = s.pop();

                visited.add(curEmail);
                component.add(curEmail);
                for(String neighbour: graph.get(curEmail)){
                    if(!visited.contains(neighbour)){
                        visited.add(neighbour);
                        s.push(neighbour);
                    }
                }
            }
            // sort ascending order
            Collections.sort(component);
            // prepend name to the component list
            component.add(0, emailToName.get(email));

            result.add(component);
        }

        return result;
    }
}
