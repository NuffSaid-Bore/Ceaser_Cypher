import java.util.*;

public class EnhancedCaesarCipherCracker {
    private final String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    // Expanded common words dictionary for better accuracy
    private final Set<String> commonWords = new HashSet<>(Arrays.asList(
        "the", "and", "is", "to", "in", "that", "it", "he", "was", "for", "you", "with", "on", "this", 
        "hello", "there", "have", "they", "his", "be", "at", "one", "we", "all", "so", "up", "out", 
        "do", "what", "when", "where", "why", "which", "how", "can", "if", "my", "me", "your", "like",
        "good", "day", "yes", "no", "will", "not", "just", "time", "people", "year", "know", "take",
        "man", "woman", "child", "first", "see", "now", "think", "more", "make", "way", "look", "go",
        "back", "come", "work", "well", "even", "down", "after", "again", "love", "life", "story",
        "Emily", "Eren", "evil", "green", "eerie", "ears"
    ));

    public String decryptWithTwoKeys(String input, int key1, int key2) {
        StringBuilder result = new StringBuilder(input);
        int charSetLength = allCharacters.length();

        for (int i = 0; i < result.length(); i++) {
            char currentChar = result.charAt(i);

            if (Character.isWhitespace(currentChar)) continue;

            int key = (i % 2 == 0) ? key1 : key2; // Alternate keys
            int index = allCharacters.indexOf(currentChar);

            if (index != -1) {
                int newIndex = (index - key + charSetLength) % charSetLength;
                result.setCharAt(i, allCharacters.charAt(newIndex));
            }
        }
        
        return fixCapitalization(result.toString());
    }

    private String fixCapitalization(String text) {
        String[] words = text.split(" ");
        StringBuilder fixedText = new StringBuilder();

        for (String word : words) {
            if (word.length() > 1) {
                fixedText.append(Character.toUpperCase(word.charAt(0)))
                         .append(word.substring(1).toLowerCase()).append(" ");
            } else {
                fixedText.append(word).append(" ");
            }
        }

        return fixedText.toString().trim();
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

        for (int key1 = 20; key1 <= 24; key1++) {  // Adjusting range around the first best guess (22)
            for (int key2 = 17; key2 <= 21; key2++) {  // Adjusting range around the second best guess (19)
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
        System.out.println("Refined Keys found: Key1 = " + bestKey1 + ", Key2 = " + bestKey2);
    }

    public static void main(String[] args) {
        EnhancedCaesarCipherCracker cracker = new EnhancedCaesarCipherCracker();
        String encryptedText = "Xifqvximt tsdtlxzrx iijirvtl ek Uybi afvbw yehvv xyi gfqdse iekmfrrpzdrxzse fj xyi jzich sw tsdtlxrxzseec xifqvxic"; // Replace with actual encrypted text

        cracker.bruteForceDecrypt(encryptedText);
    }
}
