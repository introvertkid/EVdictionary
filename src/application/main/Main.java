package main;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final int EXIT = 0;
    private static final int TRANSLATE = 1;
    private static final int REMOVE = 2;
    private static final int ADD = 3;
    private static final int LOOKUP = 4;
    private static final int UPDATE_WORD = 5;

    public static Trie trie = new Trie();
    public static OptionManagement.Update dictionaryUpdate;

    // Khởi tạo từ điển từ nhiều file
    static {
        try {
            String[] fileNames = {
                    "src/resources/DictionaryDatabase/anhviet109K_unused.txt",
                    "src/resources/DictionaryDatabase/anhviet138K.txt",
                    "src/resources/DictionaryDatabase/out.txt"
            };
            dictionaryUpdate = new OptionManagement.Update(fileNames);
        } catch (IOException e) {
            System.err.println("Lỗi khi tải từ điển: " + e.getMessage());
        }
    }

    public static void displayOptions() {
        System.out.println("Welcome to my English - Vietnamese Dictionary App");
        System.out.println("[0] = Exit");
        System.out.println("[1] = Google Translate");
        System.out.println("[2] = Remove words from dictionary database");
        System.out.println("[3] = Add words from CLI into dictionary database");
        System.out.println("[4] = Look up words in database");
        System.out.println("[5] = Update or add word");
        System.out.print("Enter an option (0 - 10): ");
    }

    public static void handleOptions(int option) {
        switch (option) {
            case EXIT -> OptionManagement.exitApp();
            case TRANSLATE -> OptionManagement.translateWord();
            case REMOVE -> OptionManagement.removeWordsFromCLI();
            case ADD -> OptionManagement.addWord();
            case LOOKUP -> OptionManagement.dictionaryLookup();
            case UPDATE_WORD -> {
                try {
                    dictionaryUpdate.updateWord();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            default -> {
                System.out.println("Invalid option, please enter an option in range 0 to 6");
                OptionManagement.pressEnterToContinue();
            }
        }
    }
    public static void main(String[] args) {
        Dictionary dictionary = OptionManagement.readWordFromFile();
        for (int i = 0; i < dictionary.dictionary.size(); i++) {
            trie.insert(dictionary.dictionary.get(i).getWord());
        }

        int option;
        do {
            displayOptions();
            option = Reader.readInt();
            Reader.readLine();
            handleOptions(option);
        } while (option != 0);
    }

}
