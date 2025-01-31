public class TwoKeyCaesarCipherDecryption {

    // Method to decrypt the text with two keys alternately
    public static String decryptTwoKeyCaesar(String text, int key1, int key2) {
        StringBuilder decryptedText = new StringBuilder();
        
        // Loop through each character in the encrypted text
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            
            if (Character.isLetter(c)) {
                // Select the correct key based on whether the index is even or odd
                int key = (i % 2 == 0) ? key1 : key2;
                
                // Decrypt the character
                char decryptedChar = (Character.isLowerCase(c)) ? 
                    (char) ((c - 'a' - key + 26) % 26 + 'a') :
                    (char) ((c - 'A' - key + 26) % 26 + 'A');
                    
                decryptedText.append(decryptedChar);
            } else {
                // Non-letter characters remain unchanged
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

    // Method to attempt all key combinations (1-25) for both keys and check for meaningful words
    public static void bruteForceDecrypt(String encryptedText) {
        String[] commonWords = {"the", "and", "to", "of", "in", "that", "it", "was", "with"};
        
        // Try different key combinations (shift values between 1 and 25 for both keys)
        for (int key1 = 1; key1 <= 25; key1++) {
            for (int key2 = 1; key2 <= 25; key2++) {
                String decrypted = decryptTwoKeyCaesar(encryptedText, key1, key2);
                
                // Print the decrypted text along with the keys used
                System.out.println("Key1: " + key1 + ", Key2: " + key2 + " -> " + decrypted);
                
                // Check if any common words appear in the decrypted text
                for (String word : commonWords) {
                    if (decrypted.toLowerCase().contains(word)) {
                        System.out.println("Potential Decryption Found: " + decrypted);
                        System.out.println("With Keys: Key1 = " + key1 + ", Key2 = " + key2);
                        return; // Stop once we find a potential decryption
                    }
                }
            }
        }
        System.out.println("No valid decryption found.");
    }

    public static void main(String[] args) {
        String encryptedText = "Uybi Gfqgykii Jgziegv Uigeixdiex Smiizzin";
        
        // Attempt brute force decryption
        bruteForceDecrypt(encryptedText);
    }
}
