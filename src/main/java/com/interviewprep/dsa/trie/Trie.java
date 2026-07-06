package com.interviewprep.dsa.trie;

import java.util.ArrayList;
import java.util.List;

class TrieNode{
    TrieNode[] children;
    boolean isWord;

    public TrieNode(){
        children = new TrieNode[26];
        isWord = false;
    }
}
// Trie or Prefix Tree
public class Trie {

    private static final TrieNode root = new TrieNode();

    public void insert(String word){
        TrieNode node = root;

        for(char c: word.toCharArray()){
            int index = c - 'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWord = true;

        System.out.printf("word: %s inserted into the trie\n", word);
    }

    public boolean search(String word){
        TrieNode node = findNode(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix){
        return findNode(prefix) != null;
    }

    private TrieNode findNode(String prefix){
        TrieNode node = root;
        for (char c: prefix.toCharArray()){
            int index = c - 'a';
            if(node.children[index] == null){
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    public boolean delete(String word){
        return delete(root, 0, word);
    }

    public boolean delete(TrieNode node, int depth, String word){
        if(depth == word.length()){
            if(!node.isWord){
                return false;   // word was never in the trie
            }
            node.isWord = false;
            return isEmpty(node);
        }

        int index = word.charAt(depth) - 'a';
        boolean shouldDeleteChild = delete(node.children[index], depth + 1, word);

        if(shouldDeleteChild){
            node.children[index] = null;
            return !node.isWord && isEmpty(node);
        }
        return false;
    }

    private boolean isEmpty(TrieNode node) {
        for(TrieNode child: node.children){
            if (child != null) return false;
        }
        return true;
    }

    // complete
    public List<String> autoComplete(String prefix){
        List<String> results = new ArrayList<>();
        TrieNode start = findNode(prefix);
        if(start == null){
            return results;
        }
        collectWords(start, new StringBuilder(prefix), results);
        return results;
    }

    private void collectWords(TrieNode node, StringBuilder current, List<String> results) {
        if(node.isWord){
            results.add(current.toString());
        }

        for(char c='a'; c<='z'; c++){
            TrieNode child = node.children[c - 'a'];

            if(child == null) continue;

            current.append(c);
            collectWords(child, current, results);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("desire");
        t.insert("destination");
//        t.insert("desiring");

        System.out.println(t.delete("desire"));
        System.out.println(t.search("desire"));

        // auto complete
        Trie t1 = new Trie();
        t1.insert("desire");
        t1.insert("desktop");
        t1.insert("destination");
        t1.insert("delight");
        t1.insert("dog");

        List<String> results = t1.autoComplete("des");

        results.forEach(System.out::println);
    }
}

//k - length of the word
//N - total number of words
//c - size of the alphabet[26 for lowercase english]

//Time complexity
//insert - O(k)
//search - O(k)
//startsWith - O(k)
//delete - O(k)

//Space complexity
//insert - O(k) [in worst case all new path]
//search - O(1)
//startsWith - O(1)
//delete - O(k) [recursion stack]

// total space trie occupies(array) - O(N * k * c)
// HashMap<Character, TrieNode> - O(N * k)

//if the problem involves prefixes or ordered word traversal, use a trie. If the problem only involves exact lookups,
// a hash map is simpler and uses less memory.