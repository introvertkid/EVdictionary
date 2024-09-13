package main;

import java.io.IOException;

public class Main
{
    private static final int EXIT=0;
    private static final int TRANSLATE=1;
    private static final int REMOVE=2;
    private static final int ADD=3;
    private static final int LOOKUP=4;

    private static  Dictionary dictionary=new Dictionary();
  public static  Trie trie=new Trie();
    public static void displayOptions()
    {
        System.out.println("Welcome to my English - Vietnamese Dictionary App");
        System.out.println("[0] = Exit");
        System.out.println("[1] = Google Translate");
        System.out.println("[2] = Remove words from dictionary database");
        System.out.println("[3] = Add words from CLI into dictionary database");
        System.out.println("[4] = Search word");
        System.out.print("Enter an option (0 - 10): ");
    }

    public static void handleOptions(int option) throws IOException
    {
        switch (option)
        {
            case EXIT -> OptionManagement.exitApp();
            case TRANSLATE -> OptionManagement.translateWord();
            case REMOVE -> OptionManagement.removeWordsFromCLI();
            case ADD -> OptionManagement.addWord();
            case LOOKUP -> OptionManagement.dictionaryLookup();
            default ->
            {
                System.out.println("Invalid option, please enter an option in range 0 to 10");
                OptionManagement.pressEnterToContinue();
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
//        DatabaseHelper.connectToDatabase();

        dictionary=OptionManagement.readWordFromFile();
        Word word=new Word("wind","gios");

        for(int i=0;i<dictionary.dictionary.size();i++)
        {
            trie.insert(dictionary.dictionary.get(i).getWord());

        }
        int option;
        do {
            displayOptions();
            option=Reader.readInt();
            Reader.readLine(); //skip \n symbol when read an integer
            handleOptions(option);
        } while (option!=0);
    }
}
