package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionManagement
{
    public static Dictionary dictionary=new Dictionary();

    //read words from txt file
    public static void readWordfromFile()
    {
        Path path = Path.of("src/resources/DictionaryDatabase/Vocabulary.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path))))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String thisWord[] = line.split("\t");
                dictionary.add(new Word(thisWord[0], thisWord[1]));
            }
            //todo: sort dictionary ?
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
        ArrayList<String> wordsToRemove = new ArrayList<>();
        Path path=Path.of("src/resources/DictionaryDatabase/Vocabulary.txt");

        System.out.println("Enter " + removeSize + " words you want to remove");
        for(int i = 0; i < removeSize; i++)
        {
            String line=Reader.readLine();
            wordsToRemove.add(line);
        }

        try(Stream<String> lines=Files.lines(path))
        {
            String filteredContent = lines
                    .filter(line -> {
                        String[] parts = line.split("\t");
                        return !wordsToRemove.contains(parts[0]);
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

    //Option 3: Add word
    //TODO: This function does not sort the dictionary after added words
    public static void addWord()
    {
        System.out.println("Enter the number of words you want to add: ");
        int addSize = Reader.readInt();
        Reader.readLine();

        for(int i=0; i < addSize; i++) {
            System.out.println("Enter the words and their translation (separated by tab): ");
            String input = Reader.readLine();
            String[] target;
            if(input.contains("\t"))
            {
                target = input.split("\t");
            }
            else
            {
                System.out.println("Invalid format. Please use tab to separate target and definition.");
                i--; //Retry the current word
                continue;
            }

            if(target.length == 2)
            {
                Word newWord = new Word(target[0], target[1]);
                dictionary.add(newWord);
            }
            else
            {
                System.out.println("Invalid input. Please try again");
                i--;
            }
        }
//        Collections.sort(dictionary);
    }
}
