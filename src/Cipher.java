// Caeser Cypher Using Object Oriented Approach.
public class Cipher {

  public static String encrypt(String text, int key) {
    StringBuilder encryptedText = new StringBuilder();
    
    for (int i = 0; i < text.length(); i++) {
        char charAt = text.charAt(i);
        
        if (Character.isLetter(charAt)) {
            char base = Character.isLowerCase(charAt) ? 'a' : 'A';
            encryptedText.append((char) (base + (charAt - base + key) % 26));
        } else {
            encryptedText.append(charAt);  // Preserve punctuation and spaces
        }
    }
    return encryptedText.toString();
}

public static void main(String[] args) {
    String text = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
    int key = 15;

    String encryptedText = encrypt(text, key);
    System.out.println("Encrypted text: " + encryptedText);
  }
}

