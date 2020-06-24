package epsi.banque;

public class SavingsAccount extends Account implements epsi.banque.IAccount {
    private final double INTERESTRATE = 0.15;
    public SavingsAccount(String name,int accountNumber, double initBalance) {
        super(name,accountNumber, initBalance);
    }

    public SavingsAccount() {
    }
    @Override
    public void withdraw(double amount) {
        double newBalance = getBalance() - amount;
        if(newBalance<0){
            System.out.println("Withdraw not permitted");
            return;
        }else{
            System.out.println("Amount "+amount+"withdrawn successfully");
        }
        setBalance(newBalance);
    }
    @Override
    public void deposit(double amount) {
        System.out.println("Amount "+amount+" deposited successfully");
        double newBalance = getBalance() + amount;
        setBalance(newBalance);
    }
    @Override
    public int getAccountNumber() {
        return super.getAccountNumber();
    }
    @Override
    public double getBalance() {
        return super.getBalance();
    }
    @Override
    public String getName() {
        return super.getName();
    }
    public double calculateInterest() {
        if(getBalance() > 0 ) {
            return getBalance()*INTERESTRATE;
        }
        else {
            return 0.0;
        }
    }
}