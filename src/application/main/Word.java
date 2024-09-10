package main;

public class Word
{
    private String target, explain;

    Word(String target, String explain)
    {
        this.target=target;
        this.explain=explain;
    }

    public void setWord(String target, String explain)
    {
        this.target=target;
        this.explain=explain;
    }

    public final String getTarget()
    {
        return this.target;
    }

    public final String getExplain()
    {
        return this.explain;
    }
}
