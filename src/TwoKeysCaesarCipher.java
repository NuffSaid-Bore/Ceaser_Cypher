import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TwoKeysCaesarCipher {
    public static String decryptTwoKeys(String encrypted, int key1, int key2) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            int key = (i % 2 == 0) ? key1 : key2;
            decrypted.append(decryptChar(ch, key));
        }
        return decrypted.toString();
    }

    private static char decryptChar(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) ((ch - base - key + 26) % 26 + base);
        }
        return ch;
    }

    public static int getKey(String encrypted, int start) {
        int[] freqs = new int[26];
        for (int i = start; i < encrypted.length(); i += 2) {
            char ch = Character.toLowerCase(encrypted.charAt(i));
            if (Character.isLetter(ch)) {
                freqs[ch - 'a']++;
            }
        }
        int maxIndex = 0;
        for (int i = 1; i < 26; i++) {
            if (freqs[i] > freqs[maxIndex]) {
                maxIndex = i;
            }
        }
        int key = maxIndex - ('e' - 'a');
        if (key < 0) key += 26;
        return key;
    }

    public static void main(String[] args) throws IOException {
        String encrypted = Files.readString(Path.of("./src/mysteryTwoKeysPractice.txt"));
        int key1 = getKey(encrypted, 0);
        int key2 = getKey(encrypted, 1);
        String decrypted = decryptTwoKeys(encrypted, key1, key2);
        
        System.out.println("Decryption keys: " + key1 + ", " + key2);
        System.out.println("First five words: " + String.join(" ", decrypted.split(" ", 5)));
    }
}
