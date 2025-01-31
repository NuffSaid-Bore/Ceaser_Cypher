public class DecryptTwoKeys {

    // Function to decrypt a message using two keys for the Caesar cipher
    public static String decrypt(String encryptedText, int key1, int key2) {
        StringBuilder decrypted = new StringBuilder();
        
        // Decrypt each character using the two keys
        for (int i = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            
            // Handle alphabetic characters
            if (Character.isLetter(c)) {
                char shiftChar;
                int shift = (i % 2 == 0) ? key1 : key2;  // Use key1 for even indices, key2 for odd
                
                if (Character.isLowerCase(c)) {
                    shiftChar = (char) ((c - 'a' - shift + 26) % 26 + 'a');
                } else {
                    shiftChar = (char) ((c - 'A' - shift + 26) % 26 + 'A');
                }
                decrypted.append(shiftChar);
            } else {
                decrypted.append(c); // Preserve non-alphabetic characters (spaces, punctuation)
            }
        }
        
        return decrypted.toString();
    }

    // Function to find the two keys used to encrypt the text
    public static void findKeysAndDecrypt(String encryptedText) {
        boolean found = false;

        // Try all key pairs (key1 and key2 between 1 and 25)
        for (int key1 = 1; key1 < 26; key1++) {
            for (int key2 = 1; key2 < 26; key2++) {
                // Decrypt the text with the current key pair
                String decryptedText = decrypt(encryptedText, key1, key2);
                
                // Display results for each attempt
                System.out.println("Attempting keys: " + key1 + ", " + key2);
                System.out.println("Decrypted text (first 5 words): " + decryptedText);
                
                // You could add further validation here if the decrypted text looks reasonable (e.g., checking for spaces, or common word patterns)
                if (decryptedText.contains("the") || decryptedText.contains("and")) {
                    System.out.println("Potential match with keys: " + key1 + ", " + key2);
                    found = true;
                }
            }
        }
        
        if (!found) {
            System.out.println("No valid key pair found.");
        }
    }

    public static void main(String[] args) {
        // Encrypted text provided
        String encryptedText = "Uybi Gfqgykii Jgziegv Uigeixdiex Smiizzin";
        
        // Try to find the two keys and decrypt the file
        findKeysAndDecrypt(encryptedText);
    }
}
