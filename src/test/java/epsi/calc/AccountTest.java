package epsi.calc;

import junit.framework.TestCase;

public class AccountTest extends TestCase {
	
	public void testDeposit() {
		SavingsAccount account = new SavingsAccount();

		account.setName("John");
		account.setBalance(5000);
		account.setAccountNumber(1234);

		assertEquals(account.getBalance(),5000);

	}

}
