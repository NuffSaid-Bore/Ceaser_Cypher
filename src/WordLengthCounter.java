import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordLengthCounter {

    // Method to check if a character is punctuation
    public static boolean isPunctuation(char c) {
        return ",.!?;:".indexOf(c) != -1;
    }

    // Method to process the word by removing punctuation from the first and last characters
    public static String processWord(String word) {
        // Remove punctuation from the first character
        if (word.length() > 0 && isPunctuation(word.charAt(0))) {
            word = word.substring(1);
        }
        // Remove punctuation from the last character
        if (word.length() > 0 && isPunctuation(word.charAt(word.length() - 1))) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }

    // Method to find the most common word length
    public static void findMostCommonWordLength(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            Map<Integer, Integer> lengthCount = new HashMap<>();

            while (scanner.hasNext()) {
                String word = scanner.next();
                word = processWord(word); // Process word to remove punctuation
                int length = word.length();

                // Increment the count of this word length
                if (length > 0) { // Ignore empty strings (could occur if the word was fully punctuation)
                    lengthCount.put(length, lengthCount.getOrDefault(length, 0) + 1);
                }
            }

            // Find the most common word length
            int mostCommonLength = -1;
            int maxCount = 0;
            for (Map.Entry<Integer, Integer> entry : lengthCount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    mostCommonLength = entry.getKey();
                    maxCount = entry.getValue();
                }
            }

            System.out.println("The most common word length is: " + mostCommonLength);
            System.out.println("It appears " + maxCount + " times.");

            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
       
        String filePaths = "./src/romeo.txt";
        String filePatherror = "./src/errors.txt";
        String filePathMany = "./src/manywords.txt";
        String filePath = "./src/lotsOfWords.txt"; 
        System.out.println("\nLots Of Words Text");
        findMostCommonWordLength(filePath);
        System.out.println("\nRomeo Text");
        findMostCommonWordLength(filePaths);
        System.out.println("\nErrors Text");
        findMostCommonWordLength(filePatherror);

        System.out.println("\nMany Words Text");
        findMostCommonWordLength(filePathMany);
    }
}
