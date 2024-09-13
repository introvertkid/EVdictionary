package main;

public class TrieNode {
    TrieNode[] child;
    boolean wordExisted;
    String meaning;

    public TrieNode()
    {
        child=new TrieNode[26];
        wordExisted =false;
    }
}