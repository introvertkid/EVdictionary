package main;
public class TrieNode {
    TrieNode[] child;
    boolean wordExist;
    String meaning;
    public TrieNode()
    {
        child=new TrieNode[26];
        wordExist=false;
    }

}