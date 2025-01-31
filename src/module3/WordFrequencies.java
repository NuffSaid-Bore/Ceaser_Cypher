package module3;
import java.util.*;
import java.io.*;
public class WordFrequencies {
    

    public static void main(String[] args) {
        // Create a HashMap to store the frequency of each word
        HashMap<String, Integer> wordCountMap = new HashMap<>();
        
        // Example file path, replace with your actual file path
        String fileName = "./src/data/madtemplate.txt";
        
        try {
            // Create a BufferedReader to read the file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            
            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Split the line into words (you can add your logic to handle punctuation and case)
                String[] words = line.toLowerCase().split("\\W+"); // \\W+ splits by non-word characters
                
                for (String word : words) {
                    if (word.length() > 0) {
                        // Check if the word is already in the map
                        if (wordCountMap.containsKey(word)) {
                            // Increment the count if the word is already in the map
                            wordCountMap.put(word, wordCountMap.get(word) + 1);
                        } else {
                            // Add the word to the map with a count of 1
                            wordCountMap.put(word, 1);
                        }
                    }
                }
            }
            reader.close();
            
            // Print out the word counts
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}