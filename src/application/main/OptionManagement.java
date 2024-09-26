package main;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionManagement
{
    public static Dictionary dictionary=new Dictionary();

    //Option 4: Lookup words
    public static void dictionaryLookup()
    {
        System.out.print("Type word to lookup: ");
        String word= Reader.readLine();
        Main.trie.search(word);
        pressEnterToContinue();
    }

    //read words from txt file
    public static Dictionary readWordFromFile()
    {
        Path path = Path.of("src/resources/DictionaryDatabase/test.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path))))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] thisWord = line.split("\t");
                if (thisWord.length == 2) dictionary.add(new Word(thisWord[0], thisWord[1]));
            }
            Collections.sort(dictionary.dictionary, Comparator.comparing(Word::getTarget));
        } catch (IOException e) {
            e.printStackTrace();
        }
      return dictionary;
    }

    //Option 2: Remove words
    public static void removeWordsFromCLI()
    {
        System.out.println("How many words do you want to remove?");

        int removeSize = Reader.readInt();
        Reader.readLine();
        ArrayList<String> wordsToRemove = new ArrayList<>();
        Path path=Path.of("src/resources/DictionaryDatabase/test.txt");
        System.out.println("Enter " + removeSize + " words you want to remove");
        for(int i = 0; i < removeSize; i++)
        {
            String line=Reader.readLine();
            wordsToRemove.add(line);
        }

        try(Stream<String> lines=Files.lines(path))
        {
            String filteredContent = lines.filter(line -> {
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
        System.out.println("---> Please press Enter to continue the program !");
        Reader.readLine();
    }

    //Option 3: Add word
    public static void addWord()
    {
        Path path = Path.of("src/resources/DictionaryDatabase/test.txt");
        int addSize;

        // Loop to ensure valid input for the number of words to add
        do
        {
            System.out.print("Enter the number of words you want to add: ");
            addSize = Reader.readInt();
            Reader.readLine();
            if(addSize<0)
            {
                System.out.println("Please enter a positive number");
            }
        } while(addSize<0);

        readWordFromFile();
        for (int i = 0; i < addSize; i++) {
            System.out.println("Enter the words and their translation (separated by tab): ");
            String input = Reader.readLine();
            String[] target;
            if (input.contains("\t")) 
            {
                target = input.split("\t");
            }
            else
            {
                System.out.println("Invalid format. Please use tab to separate target and definition.");
                i--; //Retry the current word
                continue;
            }

            // Check if the word already exists in the dictionary
            boolean wordExists = dictionary.dictionary.stream()
                    .anyMatch(word -> word.getTarget().equalsIgnoreCase(target[0]));

            if (wordExists)
            {
                System.out.println("The word '" + target[0] + "' is already in the dictionary. Continue to the next iteration");
                continue;  // Skip adding this word and continue to the next iteration
            }

            if (target.length == 2) 
            {
                Word newWord = new Word(target[0], target[1]);
                dictionary.add(newWord);
                System.out.println("Word added successfully");
            }
            else
            {
                System.out.println("Invalid input. Please try again");
                i--;
            }
        }
        //Collections.sort()
        Collections.sort(dictionary.dictionary, Comparator.comparing(Word::getTarget));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(path)))) {
            for (Word word : dictionary.dictionary) {
                writer.write(word.getTarget() + "\t" + word.getExplain());
                writer.newLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        pressEnterToContinue();
    }
    public static class Update {
        private HashMap<String, String> words;
        private final String filePath = "src/resources/DictionaryDatabase/test.txt"; // Đường dẫn tĩnh

        public Update(String[] fileNames) throws IOException {
            words = new HashMap<>();
            loadFromFile();
        }

        private void loadFromFile() throws IOException {
            File file = new File(filePath); // Sử dụng đường dẫn tĩnh
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=", 2); // Chia dòng thành từ và định nghĩa
                    if (parts.length == 2) {
                        words.put(parts[0], parts[1]); // Thêm vào từ điển
                    }
                }
                reader.close();
            }
        }

        private void saveToFile() throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)); // Sử dụng đường dẫn tĩnh
            for (String word : words.keySet()) {
                writer.write(word + "=" + words.get(word));
                writer.newLine();
            }
            writer.close();
        }

        public String findWord(String word) {
            return words.getOrDefault(word, null); // Trả về null nếu không tìm thấy
        }

        public void updateWord() throws IOException {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the word you want to look up: ");
            String word = scanner.nextLine();
            String definition = findWord(word);

            if (definition != null) {
                System.out.println("The word '" + word + "' already exists with the definition: " + definition);
                System.out.print("Do you want to change the definition? (y/n): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("y")) {
                    System.out.print("Enter the new definition: ");
                    String newDefinition = scanner.nextLine();
                    words.put(word, newDefinition);
                    saveToFile();
                    System.out.println("Definition updated.");
                } else {
                    System.out.println("No changes were made.");
                }
            } else {
                System.out.println("The word '" + word + "' does not exist.");
                System.out.print("Do you want to add this word? (y/n): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("y")) {
                    System.out.print("Enter the definition: ");
                    definition = scanner.nextLine();
                    words.put(word, definition);
                    saveToFile();
                    System.out.println("New word added.");
                } else {
                    System.out.println("No changes were made.");
                }
            }
        }
    }

}
