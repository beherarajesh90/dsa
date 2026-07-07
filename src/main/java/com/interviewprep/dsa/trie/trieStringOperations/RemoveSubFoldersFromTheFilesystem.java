package com.interviewprep.dsa.trie.trieStringOperations;

import java.util.*;

//https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/description/
public class RemoveSubFoldersFromTheFilesystem {
    private TrieNode root = new TrieNode();

    // optimal
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> result = new ArrayList<>();
        result.add(folder[0]);

        for(int i=1; i<folder.length; i++){
            String last = result.get(result.size() - 1);
            if(!folder[i].startsWith(last+"/")){
                result.add(folder[i]);
            }
        }

        return result;
    }

    public List<String> removeSubfolders2(String[] folder) {
        List<String> result = new ArrayList<>();
        for(String f: folder){
            insert(f);
        }
        dfs(root, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TrieNode node, List<String> cur, List<String> result){

        if(node.isEnd){
            result.add("/" + String.join("/", cur));
            return;
        }

        for(Map.Entry<String, TrieNode> entry: node.children.entrySet()){
            cur.add(entry.getKey());
            dfs(entry.getValue(), cur, result);
            cur.remove(cur.size()-1);
        }
    }

    private void insert(String folder){
        TrieNode node = root;

        String[] parts = folder.split("/");
        for(String part: parts){
            if(part.isEmpty()) continue;

            node.children.putIfAbsent(part, new TrieNode());
            node = node.children.get(part);
        }
        node.isEnd = true;
    }

    class TrieNode{
        Map<String,TrieNode> children;
        boolean isEnd;
        public TrieNode(){
            children = new HashMap<>();
            isEnd = false;
        }
    }
}
