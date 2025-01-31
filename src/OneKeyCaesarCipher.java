public class OneKeyCaesarCipher {
    // Decrypt a single character with the given key
    public static char decryptChar(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) ((ch - base - key + 26) % 26 + base);
        }
        return ch;
    }

    // Decrypt the text using two keys
    public static String decryptTwoKeys(String encrypted, int key1, int key2) {
        StringBuilder decrypted = new StringBuilder();
        
        for (int i = 0; i < encrypted.length(); i++) {
            int key = (i % 2 == 0) ? key1 : key2; // Use key1 for even index, key2 for odd index
            decrypted.append(decryptChar(encrypted.charAt(i), key));
        }
        
        return decrypted.toString();
    }

    public static void main(String[] args) {
        String encryptedText = "Top ncmy qkff vi vguv vbg ycpx";
        int key1 = 2, key2 = 20;

        String decryptedMessage = decryptTwoKeys(encryptedText, key1, key2);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}

