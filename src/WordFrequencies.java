import java.io.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    // Constructor
    public WordFrequencies() {
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
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();  // Remove punctuation and lowercase
                    if (!word.isEmpty()) {
                        int index = myWords.indexOf(word);
                        if (index == -1) {
                            myWords.add(word);
                            myFreqs.add(1);
                        } else {
                            myFreqs.set(index, myFreqs.get(index) + 1);
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to find the index of the most frequent word
    public int findIndexOfMax() {
        int maxIndex = 0;
        for (int i = 1; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Tester method
    public void tester(String filename) {
        findUnique(filename);

        System.out.println("\nNumber of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }

        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " +
                myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }

    public static void main(String[] args) {
        WordFrequencies wordFreq = new WordFrequencies();
        wordFreq.tester("./src/testwordfreqs.txt");  // Change the filename as needed
    }
}
