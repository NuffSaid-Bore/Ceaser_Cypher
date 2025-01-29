public class CeaserCypher {
    private final String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{},.<>?/\\|`~;:'\" ";

    public String encrypt(String input, int key) {
        return transform(input, key); // Encrypt with forward shift
    }

    public String decrypt(String input, int key) {
        return transform(input, -key); // Decrypt by shifting backward
    }

    private String transform(String input, int key) {
        StringBuilder result = new StringBuilder(input);
        int charSetLength = allCharacters.length();
        key = (key % charSetLength + charSetLength) % charSetLength; // Ensure key wraps properly

        for (int i = 0; i < result.length(); i++) {
            char currentChar = result.charAt(i);
            int index = allCharacters.indexOf(currentChar);

            if (index != -1) {
                int newIndex = (index + key) % charSetLength; // Shift characters within bounds
                result.setCharAt(i, allCharacters.charAt(newIndex));
            }
        }
        return result.toString();
    }
}
