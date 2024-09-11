package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionManagement
{
    //read words from txt file
    public static void readWordfromFile()
    {
        String filePath = "D:\\code_app\\intellij\\intel_demo\\java_demo2\\src\\Vocabulary.txt";
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
            Collections.sort(words);
            for(int i=0; i < words.size(); i++){
                String[] vocab = words.get(i).split("\t");
                Word thisWord = new Word(vocab[0], vocab[1]);
                Dictionary dictionary = new Dictionary();
                dictionary.put(thisWord);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Option 2: Remove words
    public static void removeWordsFromCLI()
    {
        System.out.println("How many words do you want to remove?");

        int removeSize = Reader.readInt();
        Reader.readLine();
        ArrayList<String> words = new ArrayList<>();
        Path path=Path.of("src/resources/DictionaryDatabase/Vocabulary.txt");

        System.out.println("Enter " + removeSize + " words you want to remove");
        for(int i = 0; i < removeSize; i++)
        {
            String line=Reader.readLine();
            words.add(line);
        }

        try(Stream<String> lines=Files.lines(path))
        {
            String filteredContent = lines
                    .filter(line -> {
                        String[] parts = line.split("\t");
                        return !words.contains(parts[0]);
                    })
                    .collect(Collectors.joining(System.lineSeparator()));

            try(FileWriter fw=new FileWriter(path.toFile()))
            {
                fw.write(filteredContent);
                System.out.println("Removed words successfully");
                pressEnterToContinue();
            }
            catch(IOException ex)
            {
                System.out.println("Can't rewrite!!!");
            }
        }
        catch(IOException ex)
        {
            System.err.println("Can't read the file " + ex.getMessage());
        }
    }

    //Option 1: Translate word
    public static void translateWord()
    {
        System.out.print("Enter what you want to translate (En-Vi): ");
        String text=Reader.readLine();
        String answer=Translator.translate("en", "vi", text);
        System.out.println(text + " = " + answer);
        pressEnterToContinue();
    }

    //Option 0: Exit app
    public static void exitApp()
    {
        System.out.println("Thank you for choosing us");
        System.exit(0);
    }

    public static void pressEnterToContinue()
    {
        System.out.println("Please press Enter to continue the program !");
        Reader.readLine();
    }

    //Option 2: Add word
    public static void addWord()
    {
        System.out.println("Enter the number of words you want to add: ");
        int numberOfWordsAdd = Reader.readInt();
        Reader.readLine();
        for(int i=0; i < numberOfWordsAdd; i++) {
            System.out.println("Enter the words and their translation (separated by tab): ");
            String input = Reader.readLine();
            String[] userWord;
            if(input.contains("\t")){
                userWord = input.split("\t");
            }else{
                System.out.println("Invalid format. Please use tab to separate word and translation.");
                i--; //Retry the current word
                continue;
            }

            if(userWord.length == 2){
                Word newWord = new Word(userWord[0], userWord[1]);
                dictionary.add(newWord);
            }else{
                System.out.println("Invalid input. Please try again");
                i--;
            }
        }
    }
}
