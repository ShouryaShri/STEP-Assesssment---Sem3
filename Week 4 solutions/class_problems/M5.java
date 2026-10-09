class FeeAccount {
    void processPayment(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    @Override
    void processPayment(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class M5 {
    static int hostelCount = 0;
    static int dayScholarCount = 0;

    static void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            account.processPayment(amount);
            hostelCount++;
        } else {
            account.processPayment(amount);
            dayScholarCount++;
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        double amount = 60000;

        for (FeeAccount account : accounts) {
            processPayment(account, amount);
        }

        System.out.println("Hostel accounts processed: " + hostelCount +
                " | Day-scholar accounts processed: " + dayScholarCount);
    }
}