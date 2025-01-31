import java.util.*;
import java.io.*;

public class GladLib {
    private ArrayList<String> nounList, adjList,animaList,colorList, countryList,nameList, timeFrameList, verbList, numberList;
    private int replacementCount;

    public GladLib() {
        nounList = new ArrayList<>();
        adjList = new ArrayList<>();
        animaList = new ArrayList<>();
        colorList = new ArrayList<>();
        countryList = new ArrayList<>();
        nameList = new ArrayList<>();
        timeFrameList = new ArrayList<>();
        verbList = new ArrayList<>();
        numberList = new ArrayList<>();
        replacementCount = 0;
        initializeFromSource();
    }

    private void initializeFromSource() {
        nounList = readFromFile("./src/data/noun.txt");
        adjList = readFromFile("./src/data/adjective.txt");
        animaList = readFromFile("./src/data/animal.txt");
        colorList = readFromFile("./src/data/color.txt");
        countryList = readFromFile("./src/data/country.txt");
        nameList = readFromFile("./src/data/name.txt");
        timeFrameList = readFromFile("./src/data/timeframe.txt");
        verbList = readFromFile("./src/data/verb.txt");
        numberList = readFromFile("./src/data/number.txt");
    }

    private ArrayList<String> readFromFile(String filename) {
        ArrayList<String> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        return list;
    }

    private String getSubstitute(String label) {
        if (label.equals("<noun>")) {
            return getRandomFromList(nounList);
        } else if (label.equals("<adjective>")) {
            return getRandomFromList(adjList);
        } else if (label.equals("<animal>")) {
            return getRandomFromList(animaList);
        } else if (label.equals("<color>")) {
            return getRandomFromList(colorList);
        }  else if (label.equals("<country>")) {
            return getRandomFromList(countryList);
        }  else if (label.equals("<name>")) {
            return getRandomFromList(nameList);
        }  else if (label.equals("<timeframe>")) {
            return getRandomFromList(timeFrameList);
        }  else if (label.equals("<verb>")) {
            return getRandomFromList(verbList);
        } else if (label.equals("<number>")) {
            return getRandomFromList(numberList);
        }
        return label;
    }

    private String getRandomFromList(ArrayList<String> list) {
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        String word = list.get(index);
        list.remove(index); 
        return word;
    }

    private void makeStory() {
        String templateFile = "./src/data/madtemplate.txt";
        String story = processTemplate(templateFile);
        System.out.println(story);
        System.out.println("Total replacements made: " + replacementCount);
    }

    private String processTemplate(String templateFile) {
        StringBuilder story = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(templateFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.contains("<")) {
                        String substitute = getSubstitute(word);
                        story.append(substitute).append(" ");
                        replacementCount+=1;
                    } else {
                        story.append(word).append(" ");
                    }
                }
                story.append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading template file: " + templateFile);
        }
        return story.toString();
    }

    public static void main(String[] args) {
        GladLib gladLib = new GladLib();
        gladLib.makeStory();
    }
}
