import java.util.*;

public class M5 {
    static void printFilteredWordFrequency(String feedback) {
        feedback = feedback.toLowerCase();
        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");

        String[] words = feedback.split("\\s+");

        HashSet<String> stopWords = new HashSet<>();
        stopWords.add("the");
        stopWords.add("was");
        stopWords.add("and");
        stopWords.add("a");
        stopWords.add("is");
        stopWords.add("of");
        stopWords.add("in");

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            if (!stopWords.contains(word)) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(frequency.entrySet());

        entries.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String feedback = sc.nextLine();

        printFilteredWordFrequency(feedback);
    }
}