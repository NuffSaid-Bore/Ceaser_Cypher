import java.util.*;

public class CaesarCipherCracker {
    private final String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final Set<String> commonWords = new HashSet<>(Arrays.asList("the", "and", "is", "to", "in", "that", "it", "he", "was", "for"));

    public String decryptWithTwoKeys(String input, int key1, int key2) {
        StringBuilder result = new StringBuilder(input);
        int charSetLength = allCharacters.length();

        for (int i = 0; i < result.length(); i++) {
            char currentChar = result.charAt(i);

            // Leave spaces unchanged
            if (currentChar == ' ') {
                continue;
            }

            int key = (i % 2 == 0) ? key1 : key2; // Alternate keys
            int index = allCharacters.indexOf(currentChar);

            if (index != -1) {
                int newIndex = (index - key + charSetLength) % charSetLength;
                result.setCharAt(i, allCharacters.charAt(newIndex));
            }
        }
        return result.toString();
    }

    public int countCommonWords(String text) {
        String[] words = text.toLowerCase().split("\\W+");
        int count = 0;
        for (String word : words) {
            if (commonWords.contains(word)) {
                count++;
            }
        }
        return count;
    }

    public void bruteForceDecrypt(String encryptedText) {
        int bestKey1 = 0, bestKey2 = 0;
        String bestDecryption = "";
        int maxCommonWords = 0;

        for (int key1 = 0; key1 < 52; key1++) {
            for (int key2 = 0; key2 < 52; key2++) {
                String decryptedText = decryptWithTwoKeys(encryptedText, key1, key2);
                int commonWordCount = countCommonWords(decryptedText);

                if (commonWordCount > maxCommonWords) {
                    maxCommonWords = commonWordCount;
                    bestDecryption = decryptedText;
                    bestKey1 = key1;
                    bestKey2 = key2;
                }
            }
        }

        System.out.println("Best Guess Decryption:");
        System.out.println(bestDecryption);
        System.out.println("Keys found: Key1 = " + bestKey1 + ", Key2 = " + bestKey2);
    }

    public static void main(String[] args) {
        CaesarCipherCracker cracker = new CaesarCipherCracker();
        String encryptedText = "Akag tjw Xibhr awoa aoee xakex znxag xwko"; // Example encrypted text

        cracker.bruteForceDecrypt(encryptedText);
    }
}
