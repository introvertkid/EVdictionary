package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary
{
    public ArrayList<Word> dictionary;

    public Dictionary()
    {
        this.dictionary=new ArrayList<>();
    }

  public static void dictionaryLookup()
  {
      System.out.println("Type word to look up");
      Scanner ip=new Scanner(System.in);
      String word= ip.next();
      Trie searcher=new Trie();
      searcher.search(word);
  }

    public void add(Word word)
    {
        dictionary.add(word);
    }
}
