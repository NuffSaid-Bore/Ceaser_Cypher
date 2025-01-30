public class TwoKeyDecrypt {
    private final String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String decrypt(String input, int key1, int key2) {
        return transform(input, -key1, -key2); // Decrypt by shifting backward
    }

    private String transform(String input, int key1, int key2) {
        StringBuilder result = new StringBuilder(input);
        int charSetLength = allCharacters.length();

        // Ensure keys wrap properly
        key1 = (key1 % charSetLength + charSetLength) % charSetLength;
        key2 = (key2 % charSetLength + charSetLength) % charSetLength;

        // Apply alternating decryption for each character
        for (int i = 0; i < result.length(); i++) {
            char currentChar = result.charAt(i);

            // If it's a space, just leave it unchanged
            if (currentChar == ' ') {
                continue;
            }

            int index = allCharacters.indexOf(currentChar);
            if (index != -1) {
                // Alternate between key1 and key2
                int newIndex;
                if (i % 2 == 0) {
                    newIndex = (index - key1 + charSetLength) % charSetLength; // Apply key1 for even indices (backward shift)
                } else {
                    newIndex = (index - key2 + charSetLength) % charSetLength; // Apply key2 for odd indices (backward shift)
                }
                result.setCharAt(i, allCharacters.charAt(newIndex));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        TwoKeyDecrypt cipher = new TwoKeyDecrypt();

        String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";  // Encrypted message

        // Try all possible key combinations for key1 and key2 (brute-force)
        for (int key1 = 1; key1 <= 26; key1++) {
            for (int key2 = 1; key2 <= 26; key2++) {
                String decrypted = cipher.decrypt(encrypted, key1, key2);
                System.out.println("Key1: " + key1 + ", Key2: " + key2 + " -> Decrypted message: " + decrypted);
            }
        }
    }
}
