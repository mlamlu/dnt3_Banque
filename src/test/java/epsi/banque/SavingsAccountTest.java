package epsi.banque;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SavingsAccountTest {
    SavingsAccount account;
    double delta=0.001;

    @BeforeEach
    void setUp() throws Exception {
        account= new SavingsAccount();
        account.setName("John");
        account.setBalance(5000);
        account.setAccountNumber(1234);
    }
    @AfterEach
    void tearDown() throws Exception {
        account=null;
    }
    @Test
    void DepositTest() {
        account.deposit(500);
        assertEquals(account.getBalance(),55000,delta,"Deposit Test");
    }

    @Test
    void withdrawTest() {
        account.withdraw(500);
        assertEquals(account.getBalance(),4500.0,delta,"Withdraw Test");
    }

    @Test
    void interestTest() {
        double interest = account.calculateInterest();
        assertEquals(interest,750.0,delta,"Interest Test");
    }
}