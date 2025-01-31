public class CaeserCiphers {
    // Method to decrypt the message with two keys
    public static String decrypt(String text, int evenShift, int oddShift) {
        StringBuilder decryptedText = new StringBuilder();

        // Loop through each character in the encrypted text
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            // Check if the character is a letter
            if (Character.isLetter(currentChar)) {
                // Use the appropriate shift based on the position (even or odd)
                int shift = (i % 2 == 0) ? evenShift : oddShift;

                // Determine whether the character is uppercase or lowercase
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                // Decrypt the character by applying the shift
                char decryptedChar = (char) ((currentChar - base - shift + 26) % 26 + base);
                decryptedText.append(decryptedChar);
            } else {
                // If it's not a letter, just append it as is (like spaces or punctuation)
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        // Even position shift: -17 (U -> D, b -> k)
        int evenShift = 17; // We apply a shift of 17 to even positions (0-based index)
        
        // Odd position shift: -4 (y -> u, i -> e)
        int oddShift = 4;   // We apply a shift of 4 to odd positions (0-based index)

        // Encrypted message to be decrypted
        String encryptedMessage = "Sei sw klv deec lrpcqrvbw sw fyi jytgvwj yej sivr jiyzxwyc tscprffvrxzsew edsek hzjwiiiex kisltj nmklzr xyi hvtrvkqvrk, azxy iijirvtl kisltj zr sklvv hvtrvkqvrkw ek Uybi, nmkl sklvv mewkmkykij, eeh azxy zruyjxic.";

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, evenShift, oddShift);

        // Print the decrypted message
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}