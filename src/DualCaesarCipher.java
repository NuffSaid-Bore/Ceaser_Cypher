public class DualCaesarCipher {
    public static String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            int key = (i % 2 == 0) ? key1 : key2;
            char encryptedChar = encryptChar(currChar, key);
            encrypted.setCharAt(i, encryptedChar);
        }
        
        return encrypted.toString();
    }
    
    private static char encryptChar(char c, int key) {
        if (Character.isLetter(c)) {
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            return (char) ((c - base + key) % 26 + base);
        }
        return c;
    }
    
    public static void main(String[] args) {
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key1 = 21;
        int key2 = 8;
        String encrypted = encryptTwoKeys(input, key1, key2);
        System.out.println(encrypted);
    }
}
