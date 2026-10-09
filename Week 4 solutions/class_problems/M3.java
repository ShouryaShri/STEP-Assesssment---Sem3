class FeeAccount {
    String regNo;
    double totalFee;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    final double calculateLateFee(int daysLate) {
        return totalFee * daysLate / 100.0;
    }

    final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            System.out.println(regNo + " | Total Fee: Rs " + totalFee +
                    " | Late Fee: Rs " + calculateLateFee(daysLate));
        }
    }
}

public class M3 {
    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000, 150000, 180000, 220000};
        int[] daysLate = {10, 0, -2, 5};

        FeeAccount[] accounts = new FeeAccount[regNos.length];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new FeeAccount(regNos[i], totalFees[i]);

            if (daysLate[i] <= 0) {
                System.out.println(regNos[i] + " - On time, no late fee");
            } else {
                accounts[i].printSummary(daysLate[i]);
            }
        }
    }
}