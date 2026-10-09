import java.util.Scanner;

public class ReverseCustomerName {

    static String reverseCustomerName(String customerName) {
        char[] chars = customerName.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--)
            sb.append(chars[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();

        String reversed = reverseCustomerName(customerName);
        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversed);
    }
}
