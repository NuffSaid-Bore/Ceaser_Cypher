import java.io.*;
import java.util.*;

public class CharactersInPlays {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterCounts;

    // Constructor
    public CharactersInPlays() {
        characterNames = new ArrayList<>();
        characterCounts = new ArrayList<>();
    }

    // Method to update the character's count
    public void update(String person) {
        person = person.trim();
        int index = characterNames.indexOf(person);
        if (index == -1) {
            characterNames.add(person);
            characterCounts.add(1);
        } else {
            characterCounts.set(index, characterCounts.get(index) + 1);
        }
    }

    // Method to read the file and process the characters
    public void findAllCharacters(String filename) {
        characterNames.clear();
        characterCounts.clear();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                int periodIndex = line.indexOf(".");
                if (periodIndex != -1) {
                    String character = line.substring(0, periodIndex).trim();
                    character = character.replaceAll("[^a-zA-Z ]", "").toUpperCase();  // Remove extra punctuation and normalize
                    if (!character.isEmpty()) {
                        update(character);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to find the character with the most speaking parts
    public String findCharacterWithMostSpeakingParts() {
        int maxIndex = 0;
        for (int i = 1; i < characterCounts.size(); i++) {
            if (characterCounts.get(i) > characterCounts.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return characterNames.get(maxIndex);
    }

    // Method to get the number of speaking parts of the character with most parts
    public int getMostSpeakingParts() {
        int maxIndex = 0;
        for (int i = 1; i < characterCounts.size(); i++) {
            if (characterCounts.get(i) > characterCounts.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return characterCounts.get(maxIndex);
    }

    // Tester method
    public void tester(String filename) {
        findAllCharacters(filename);
        
        // Find the character with the most speaking parts
        String mostFrequentCharacter = findCharacterWithMostSpeakingParts();
        int speakingParts = getMostSpeakingParts();

        System.out.println("The character with the most speaking parts is: " + mostFrequentCharacter);
        System.out.println("They have " + speakingParts + " speaking parts.");
    }

    public static void main(String[] args) {
        CharactersInPlays characterInPlay = new CharactersInPlays();
        characterInPlay.tester("./src/likeit.txt");  // Make sure the file is in the correct directory
    }
}