package epsi.calc;

public abstract class Account {
    private String name;
    private int accountNumber;
    private double balance;
    public Account(String name,int accountNumber, double initBalance){
        this.name=name;
        this.accountNumber = accountNumber;
        this.balance = initBalance;
    }

    //no-arg constructor
    public Account(){

    }
    public String getName() {
        return name;
    }
    public void setName(String str) {
        this.name = str;
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public void setAccountNumber(int number) {
        this.accountNumber = number;
    }
}