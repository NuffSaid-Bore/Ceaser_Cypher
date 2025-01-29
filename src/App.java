public class App {
    public static void main(String[] args) throws Exception {
        CeaserCypher cypher = new CeaserCypher();
        WordPlay wp = new WordPlay();

        RandomNumbers rNumbers = new RandomNumbers();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 10;
        String encryptedText = cypher.encrypt(message, key);
        String decryptedText = cypher.decrypt(encryptedText, key);

        System.out.println("============WORD PLAY===========");
        System.out.println(wp.isVowel('K')); // true
        System.out.println(wp.isVowel('E')); // false

        // Test replaceVowels
        System.out.println(wp.replaceVowels("Kamohelo", '*'));

        // Test emphasize
        System.out.println(wp.emphasize("Bore", 'o')); 
        System.out.println("==========END OF WORD PLAY=========");

        System.out.println("\n============CEASER CYPHER===========");
        System.out.println("The Encrypted Text is: "+encryptedText );
        System.out.println("The Decrypted Text is: "+decryptedText);

        rNumbers.SimpleSimulate(100);
        
    }
}
