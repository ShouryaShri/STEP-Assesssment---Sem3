import java.util.Random;

public class BMICalculator {

    static String getBmiStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }

    static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("-".repeat(65));
        System.out.printf("%-8s | %-10s | %-11s | %-6s | %-12s%n",
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("-".repeat(65));
        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            System.out.printf("%-8d | %-10.2f | %-11.1f | %-6.2f | %-12s%n",
                    i + 1, heights[i], weights[i], bmi, getBmiStatus(bmi));
        }
        System.out.println("-".repeat(65));
    }

    public static void main(String[] args) {
        final int TEAM_SIZE = 10;
        Random rand = new Random();
        double[] heights = new double[TEAM_SIZE];
        double[] weights = new double[TEAM_SIZE];

        for (int i = 0; i < TEAM_SIZE; i++) {
            heights[i] = 1.50 + rand.nextDouble() * 0.45;   // 1.50 - 1.95 m
            weights[i] = 45 + rand.nextDouble() * 65;       // 45 - 110 kg
        }
        printWellnessReport(heights, weights);
    }
}
