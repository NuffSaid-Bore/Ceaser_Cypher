import java.io.*;
import java.util.*;

public class WordFrequency {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    // Constructor
    public WordFrequency() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    // Method to find unique words and their frequencies
    public void findUnique(String filename) {
        myWords.clear();
        myFreqs.clear();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase();  // Convert to lowercase
                    if (!word.isEmpty()) {
                        int index = myWords.indexOf(word);
                        if (index == -1) {
                            myWords.add(word);  // Add new word
                            myFreqs.add(1);  // Initialize frequency
                        } else {
                            myFreqs.set(index, myFreqs.get(index) + 1);  // Increment frequency
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to get the count of unique words
    public int getUniqueWordCount() {
        return myWords.size();
    }

    // Tester method
    public void tester(String filename) {
        findUnique(filename);
        System.out.println("Number of unique words: " + getUniqueWordCount());
    }

    public static void main(String[] args) {
        WordFrequency wordFreq = new WordFrequency();
        wordFreq.tester("./src/likeit.txt");  // Make sure the file is in the correct directory
    }
}