import edu.duke.*;
import java.util.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordFileMap;

    // Constructor
    public WordsInFiles() {
        wordFileMap = new HashMap<>();
    }

    // Adds words from a file into the map
    private void addWordsFromFile(File f) {
        String content = FileResource.readFile(f);
        String[] words = content.split("\\s+");
        for (String word : words) {
            word = word.toLowerCase();
            ArrayList<String> files = wordFileMap.getOrDefault(word, new ArrayList<>());
            String fileName = f.getName();
            if (!files.contains(fileName)) {
                files.add(fileName);
            }
            wordFileMap.put(word, files);
        }
    }

    // Builds the map by processing a group of files
    public void buildWordFileMap() {
        wordFileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    // Returns the maximum number of files a word appears in
    public int maxNumber() {
        int max = 0;
        for (ArrayList<String> files : wordFileMap.values()) {
            max = Math.max(max, files.size());
        }
        return max;
    }

    // Returns words that appear in exactly 'number' files
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> entry : wordFileMap.entrySet()) {
            if (entry.getValue().size() == number) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    // Prints the filenames where a word appears
    public void printFilesIn(String word) {
        word = word.toLowerCase();
        if (wordFileMap.containsKey(word)) {
            for (String file : wordFileMap.get(word)) {
                System.out.println(file);
            }
        } else {
            System.out.println("No files found for word: " + word);
        }
    }

    // Tester method to process files and print results
    public void tester() {
        buildWordFileMap();
        int maxFiles = maxNumber();
        System.out.println("Max number of files a word appears in: " + maxFiles);
        ArrayList<String> wordsInMaxFiles = wordsInNumFiles(maxFiles);
        for (String word : wordsInMaxFiles) {
            System.out.println("Word: " + word);
            printFilesIn(word);
        }
    }
}
