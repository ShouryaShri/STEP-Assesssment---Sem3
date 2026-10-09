import java.util.Scanner;

public class PalindromeChecker {

    // Keeps only letters/digits, lower-cased, so phrases like "A man, a plan..." also work
    static String clean(String text) {
        return text.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    }

    // 1) Iterative: two pointers moving toward the middle
    static boolean isPalindromeIterative(String text) {
        String s = clean(text);
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    // 2) Recursive: compare first and last, then shrink the substring
    static boolean isPalindromeRecursive(String text) {
        return recurse(clean(text));
    }

    private static boolean recurse(String s) {
        if (s.length() <= 1) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return recurse(s.substring(1, s.length() - 1));
    }

    // 3) Array reversal: reverse a char array and compare with the original
    static boolean isPalindromeArrayReversal(String text) {
        char[] original = clean(text).toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++)
            reversed[i] = original[original.length - 1 - i];
        return java.util.Arrays.equals(original, reversed);
    }

    static String label(boolean b) {
        return b ? "Palindrome" : "Not Palindrome";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        boolean a = isPalindromeIterative(text);
        boolean b = isPalindromeRecursive(text);
        boolean c = isPalindromeArrayReversal(text);

        System.out.println("Iterative: " + label(a) + " | Recursive: " + label(b)
                + " | Array Reversal: " + label(c));
        System.out.println(a == b && b == c ? "All three approaches agree." : "Mismatch detected!");
    }
}
