import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    static final String[] MOVES = {"Rock", "Paper", "Scissors"};

    // Returns "Player Wins", "Computer Wins" or "Draw"
    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) return "Draw";
        boolean playerWins =
                (playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
                (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
                (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"));
        return playerWins ? "Player Wins" : "Computer Wins";
    }

    static String normalize(String m) {
        for (String s : MOVES) if (s.equalsIgnoreCase(m.trim())) return s;
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        final int N = 5;

        System.out.print("Enter 1 for manual input, 2 for demo mode: ");
        boolean manual = sc.nextLine().trim().equals("1");
        String[] demo = {"Rock", "Paper", "Scissors", "Rock", "Paper"};

        String[] player = new String[N], computer = new String[N], result = new String[N];
        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < N; i++) {
            String p = null;
            if (manual) {
                while (p == null) {
                    System.out.print("Round " + (i + 1) + " - Enter Rock/Paper/Scissors: ");
                    p = normalize(sc.nextLine());
                    if (p == null) System.out.println("Invalid move, try again.");
                }
            } else {
                p = demo[i];
            }
            String c = MOVES[rand.nextInt(3)];
            String r = playRound(p, c);

            player[i] = p; computer[i] = c; result[i] = r;
            System.out.println("Round " + (i + 1) + " - Player: " + p + ", Computer: " + c + " -> " + r);

            if (r.equals("Player Wins")) wins++;
            else if (r.equals("Computer Wins")) losses++;
            else draws++;
        }

        System.out.println("\n" + "-".repeat(55));
        System.out.printf("%-8s | %-12s | %-13s | %-13s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("-".repeat(55));
        for (int i = 0; i < N; i++)
            System.out.printf("%-8d | %-12s | %-13s | %-13s%n", i + 1, player[i], computer[i], result[i]);
        System.out.println("-".repeat(55));

        double winPct = wins * 100.0 / N;
        System.out.println("Wins: " + wins + " | Losses: " + losses + " | Draws: " + draws
                + " | Win % = " + String.format("%.1f", winPct) + "%");
    }
}
