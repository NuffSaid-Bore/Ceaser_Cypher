public class DoubleCaesarCipher {

    public static String encrypt(String text, int key1, int key2) {
        StringBuilder encryptedText = new StringBuilder();
        boolean useKey1 = true;  // Alternate between key1 and key2
        
        for (int i = 0; i < text.length(); i++) {
            char charAt = text.charAt(i);
            
            if (Character.isLetter(charAt)) {
                int key = useKey1 ? key1 : key2;
                char base = Character.isLowerCase(charAt) ? 'a' : 'A';
                encryptedText.append((char) (base + (charAt - base + key) % 26));
                useKey1 = !useKey1;  // Alternate keys
            } else {
                encryptedText.append(charAt);  // Preserve punctuation and spaces
            }
        }
        return encryptedText.toString();
    }

    public static void main(String[] args) {
        String text = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key1 = 21;
        int key2 = 8;

        String encryptedText = encrypt(text, key1, key2);
        System.out.println("Encrypted text: " + encryptedText);
    }
}
