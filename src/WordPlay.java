public class WordPlay {
    public boolean isVowel(char ch) {
        // Check if the character is a vowel (both lowercase and uppercase)
        return "AEIOUaeiou".indexOf(ch) != -1;
    }

    public String replaceVowels(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < result.length(); i++) {
            if (isVowel(result.charAt(i))) {
                result.setCharAt(i, ch); // Replace the vowel with the given character
            }
        }
        return result.toString();
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == ch) {
                // If at an odd index, replace with '*', else replace with '+'
                if (i % 2 == 0) {
                    result.setCharAt(i, '*');
                } else {
                    result.setCharAt(i, '+');
                }
            }
        }
        return result.toString();
    }
}
