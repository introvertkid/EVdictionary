package main;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Scanner;
public class OptionManagement

{
    //Option 1: Translate word
    public static void translateWord()
    {
        System.out.print("Enter what you want to translate (En-Vi): ");
        String text=Reader.readLine();
        String answer=Translator.translate("en", "vi", text);
//        System.out.println(text);
        System.out.println(answer);
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
    //Option 2: Remove words
    public static void removeWords()
    {

        System.out.println("How many words you want to remove?");
        //dung Reader class de doc file
        Scanner ip=new Scanner(System.in);
        int wordssize=ip.nextInt();
        ArrayList<String> words=new ArrayList<>();
        for(int i=0;i<wordssize;i++)
        {
            String line=ip.next();
            words.add(line);
        }
        ip.close();
        Path path=Path.of("dictionaries.txt");
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

            }
            catch(IOException ex)
            {
                System.out.println("Can't rewrite!!!");
            }

        }
        catch(IOException ex)
        {
            System.out.println("Can't open file or file don't have any content!!!");
        }
    }

}
