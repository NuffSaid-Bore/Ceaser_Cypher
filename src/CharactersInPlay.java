import java.io.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterCounts;

    // Constructor
    public CharactersInPlay() {
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

    // Method to print characters with speaking parts between num1 and num2
    public void charactersWithNumPartsInRange(int num1, int num2) {
        for (int i = 0; i < characterNames.size(); i++) {
            int count = characterCounts.get(i);
            if (count >= num1 && count <= num2) {
                System.out.println(characterNames.get(i) + "\t" + count);
            }
        }
    }

    // Tester method
    public void tester(String filename) {
        findAllCharacters(filename);
        System.out.println("Characters with between 10 and 15 speaking parts:");
        charactersWithNumPartsInRange(10, 15);
    }

    public static void main(String[] args) {
        CharactersInPlay characterInPlay = new CharactersInPlay();
        characterInPlay.tester("./src/likeit.txt");  // Make sure the file is in the correct directory
    }
}
