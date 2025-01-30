public class CaesarCipher {
    private final String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    // Encrypt with two keys: key1 for even indices, key2 for odd indices
    public String encrypt(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder();
        int charSetLength = allCharacters.length();

        // Loop through each character in the input string
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int index = allCharacters.indexOf(currentChar);

            // Only process valid characters in the character set
            if (index != -1) {
                int newIndex;
                if (i % 2 == 0) {
                    // Apply key1 for even positions
                    newIndex = (index + key1) % charSetLength;
                    if (newIndex < 0) newIndex += charSetLength;  // Wrap around if negative
                } else {
                    // Apply key2 for odd positions
                    newIndex = (index + key2) % charSetLength;
                    if (newIndex < 0) newIndex += charSetLength;  // Wrap around if negative
                }

                encrypted.append(allCharacters.charAt(newIndex));
            } else {
                // If not in the set (e.g., space or symbol), append it unchanged
                encrypted.append(currentChar);
            }
        }
        return encrypted.toString();
    }

    // Decrypt using the inverse of the two keys: -key1 for even indices, -key2 for odd indices
    public String decrypt(String input, int key1, int key2) {
        StringBuilder decrypted = new StringBuilder();
        int charSetLength = allCharacters.length();

        // Loop through each character in the encrypted message
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int index = allCharacters.indexOf(currentChar);

            // Only process valid characters in the character set
            if (index != -1) {
                int newIndex;
                if (i % 2 == 0) {
                    // Reverse the shift by applying -key1 for even positions
                    newIndex = (index - key1) % charSetLength;
                    if (newIndex < 0) newIndex += charSetLength;  // Wrap around if negative
                } else {
                    // Reverse the shift by applying -key2 for odd positions
                    newIndex = (index - key2) % charSetLength;
                    if (newIndex < 0) newIndex += charSetLength;  // Wrap around if negative
                }

                decrypted.append(allCharacters.charAt(newIndex));
            } else {
                // If not in the set, append unchanged
                decrypted.append(currentChar);
            }
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();

        // Test with two keys (2 and 20)
        String originalMessage = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println("Original message: " + originalMessage);

        // String encryptedMessage = cipher.encrypt(originalMessage, 17, 23);
        // System.out.println("Encrypted message: " + encryptedMessage);

        String decryptedMessage = cipher.decrypt(originalMessage, 2, 20);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
