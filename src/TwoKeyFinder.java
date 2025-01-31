import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TwoKeyFinder {

    // Method to count frequency of letters in a given string
    private static char mostFrequentLetter(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }
        return frequencyMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    // Method to compute key by assuming 'E' is the most common letter
    private static int computeKey(char mostCommonLetter) {
        int key = mostCommonLetter - 'E';
        if (key < 0) key += 26; // Ensure key is within 0-25 range
        return key;
    }

    public static void main(String[] args) {
        try {
            // Read encrypted file
            String filePath = "./src/mysteryTwoKeysPractice.txt";  // Ensure the file is in the same directory
            String encryptedText = new String(Files.readAllBytes(Paths.get(filePath)));

            // Separate even and odd indexed letters
            StringBuilder evenChars = new StringBuilder();
            StringBuilder oddChars = new StringBuilder();

            for (int i = 0; i < encryptedText.length(); i++) {
                char c = encryptedText.charAt(i);
                if (Character.isLetter(c)) {
                    if (i % 2 == 0) {
                        evenChars.append(c);
                    } else {
                        oddChars.append(c);
                    }
                }
            }

            // Find most common letters in each set
            char mostCommonEven = mostFrequentLetter(evenChars.toString());
            char mostCommonOdd = mostFrequentLetter(oddChars.toString());

            // Compute the two keys
            int key1 = computeKey(mostCommonEven);
            int key2 = computeKey(mostCommonOdd);

            // Print the keys in the required format
            System.out.println("Keys: " + key1 + "," + key2);

        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }
    }
}
