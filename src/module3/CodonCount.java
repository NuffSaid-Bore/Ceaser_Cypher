package module3;
import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> codonMap;

    // Constructor
    public CodonCount() {
        codonMap = new HashMap<>();
    }

    // Builds the codon map for a given reading frame
    public void buildCodonMap(int start, String dna) {
        codonMap.clear(); // clear previous map
        
        // Traverse the DNA strand from the start index
        for (int i = start; i + 3 <= dna.length(); i += 3) {
            String codon = dna.substring(i, i + 3).toUpperCase();
            codonMap.put(codon, codonMap.getOrDefault(codon, 0) + 1);
        }
    }

    // Get the most common codon
    public String getMostCommonCodon() {
        int maxCount = 0;
        String mostCommonCodon = "";
        
        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);
            if (count > maxCount) {
                maxCount = count;
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }

    // Print codons with counts in a specified range
    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);
            if (count >= start && count <= end) {
                System.out.println(codon + "    " + count);
            }
        }
    }
}