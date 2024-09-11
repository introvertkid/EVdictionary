package main;

import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;

public class OptionManagement
{
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
}
