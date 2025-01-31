public class KeyCaesarCipher {
    // Method to decrypt the message
    public static String decrypt(String text, int key1, int key2) {
        StringBuilder decryptedText = new StringBuilder();
        
        // Alternate between the two keys for each character
        int[] keys = {key1, key2};
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int key = keys[i % 2];
            
            // Decrypt letters
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                char decryptedChar = (char) ((c - base - key + 26) % 26 + base);
                decryptedText.append(decryptedChar);
            } else {
                // If it's not a letter, just append the character (space, punctuation)
                decryptedText.append(c);
            }
        }
        
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        // The encrypted message
        String encryptedMessage = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        
        // The two keys
        int key1 = 14;
        int key2 = 24;
        
        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, key1, key2);
        
        // Output the decrypted message
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}