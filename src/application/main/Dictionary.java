package main;

import java.util.ArrayList;

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

    public int getSize()
    {
        return dictionary.size();
    }

    public Word get(int i)
    {
        return dictionary.get(i);
    }
}
