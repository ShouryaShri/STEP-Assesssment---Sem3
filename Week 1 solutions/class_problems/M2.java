class MessWallet{
    private double balance;
    public MessWallet(double openingBalance){
        if (openingBalance < 0){
            balance = 0;
            System.out.println("Warning! Negative balance entered");
        }
        else{
            balance = openingBalance;
        }
    }
    public void topUp(double amount){
        if (amount <= 0){
            System.out.println("Enter correct amount greater than 0");
        }
        else{
            amount+=balance;
            System.out.print("Balance after top up: "+amount);
        }
        }
    public void deduct(double amount){
        if (amount > balance){
            System.out.println("Deduct rejected: insufficient balance");
        }
        else{
            balance-=amount;
            System.out.println("Final balance: "+balance);
        }
    }
    public double getBalance(){
        return balance;
    }
    }
public class M2 {
    public static void main(String[] args){
        MessWallet A1= new MessWallet(500);
        A1.topUp(200);
        A1.deduct(2000);
        A1.getBalance();
    }
}
