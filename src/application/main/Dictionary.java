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



    public void add(Word word)
    {
        dictionary.add(word);
    }
}
