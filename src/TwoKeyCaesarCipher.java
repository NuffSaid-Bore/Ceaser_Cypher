import java.io.*;
import java.nio.file.*;

public class TwoKeyCaesarCipher {
    public static void main(String[] args) throws IOException {
        String filePath = "./src/mysteryTwoKeysPractice.txt"; // Update with actual file path
        String encryptedText = Files.readString(Paths.get(filePath));
        
        String decryptedText = decryptTwoKeys(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
        
        // Print first five words
        System.out.println("First 5 Words: " + String.join(" ", decryptedText.split(" ", 5)));
    }

    public static String decryptTwoKeys(String input) {
        String oddChars = getEveryOtherCharacter(input, 0);
        String evenChars = getEveryOtherCharacter(input, 2);
        
        int key1 = getKey(oddChars);
        int key2 = getKey(evenChars);
        
        System.out.println("Detected Keys: " + key1 + ", " + key2);
        
        return decryptWithTwoKeys(input, key1, key2);
    }

    public static String getEveryOtherCharacter(String text, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < text.length(); i += 2) {
            sb.append(text.charAt(i));
        }
        return sb.toString();
    }

    public static int getKey(String text) {
        int[] letterCounts = new int[26];
        
        
        for (char ch : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                letterCounts[ch - 'a']++;
            }
        }
        
        int maxIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (letterCounts[i] > letterCounts[maxIndex]) {
                maxIndex = i;
            }
        }

        int shift = maxIndex - 4; // Assuming 'e' is the most common letter
        if (shift < 0) shift += 26;
        
        return shift;
    }

    public static String decryptWithTwoKeys(String input, int key1, int key2) {
        StringBuilder decrypted = new StringBuilder();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            boolean isLower = Character.isLowerCase(ch);
            char upperCh = Character.toUpperCase(ch);
            
            int idx = alphabet.indexOf(upperCh);
            if (idx != -1) {
                char newCh = (i % 2 == 0) ? shiftedAlphabet1.charAt(idx) : shiftedAlphabet2.charAt(idx);
                decrypted.append(isLower ? Character.toLowerCase(newCh) : newCh);
            } else {
                decrypted.append(ch);
            }
        }
        return decrypted.toString();
    }
}
