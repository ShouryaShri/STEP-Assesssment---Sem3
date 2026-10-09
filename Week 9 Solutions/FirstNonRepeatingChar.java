import java.util.HashMap;
import java.util.Scanner;

public class FirstNonRepeatingChar {

    // Returns the first character with frequency 1, or '\0' if none exists
    static char findFirstNonRepeatingChar(String text) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char ch : text.toCharArray())
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

        for (char ch : text.toCharArray())
            if (freq.get(ch) == 1) return ch;   // early exit

        return '\0';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a word or sentence: ");
        String text = sc.nextLine();

        char result = findFirstNonRepeatingChar(text);
        if (result == '\0')
            System.out.println("No Non-Repeating Character Found");
        else
            System.out.println("First Non-Repeating Character: '" + result + "'");
    }
}
