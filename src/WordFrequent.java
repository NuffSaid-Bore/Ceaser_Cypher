import java.io.*;
import java.util.*;

public class WordFrequent {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    // Constructor
    public WordFrequent() {
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

    // Method to find the word with the maximum frequency
    public String findMostFrequentWord() {
        int maxIndex = 0;
        for (int i = 1; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return myWords.get(maxIndex);
    }

    // Method to get the frequency of the most frequent word
    public int getFrequencyOfMostFrequentWord() {
        int maxIndex = 0;
        for (int i = 1; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return myFreqs.get(maxIndex);
    }

    // Tester method
    public void tester(String filename) {
        findUnique(filename);
        
        // Find the most frequent word and its count
        String mostFrequentWord = findMostFrequentWord();
        int frequency = getFrequencyOfMostFrequentWord();

        System.out.println("The word that occurs most often and its count are: " +
                mostFrequentWord + " " + frequency);
    }

    public static void main(String[] args) {
        WordFrequent wordFreq = new WordFrequent();
        wordFreq.tester("./src/likeit.txt");  // Make sure the file is in the correct directory
    }
}