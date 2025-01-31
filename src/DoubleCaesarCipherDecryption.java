public class DoubleCaesarCipherDecryption {

    public static String decrypt(String text, int key1, int key2) {
        StringBuilder decryptedText = new StringBuilder();
        boolean useKey1 = true;  // Alternate between key1 and key2
        
        for (int i = 0; i < text.length(); i++) {
            char charAt = text.charAt(i);
            
            if (Character.isLetter(charAt)) {
                int key = useKey1 ? key1 : key2;  // Use key1 or key2 depending on position
                char base = Character.isLowerCase(charAt) ? 'a' : 'A';
                decryptedText.append((char) (base + (charAt - base - key + 26) % 26));  // Subtract the key
                useKey1 = !useKey1;  // Alternate between key1 and key2
            } else {
                decryptedText.append(charAt);  // Preserve spaces and punctuation
            }
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String text = "Hfs cpwewloj loks cd Hoto kyg Cyy.";  // Encrypted message
        int key1 = 14;  // First key
        int key2 = 24;  // Second key

        String decryptedText = decrypt(text, key1, key2);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
