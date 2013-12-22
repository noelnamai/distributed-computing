package cscie160.hw5;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**{@link cscie160.hw5.AccountTest} Class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW5.*/
public class AccountTest 
{
	private static Account account;
	private float amount;
	private float expected;
	private float actual;

	@Before
	public void setUp() throws Exception 
	{
		account = new Account();
		amount = 1000;
		account.setAccountNumber(000001);
		account.setBalance(Commands.DEPOSIT, amount);
	}

	@After
	public void tearDown() throws Exception 
	{
		account = null;
	}

	@Test
	public void testGetAccountNumber() 
	{
		expected = 000001;
		actual = account.getAccountNumber();
		assertEquals(String.format("AccountNumber() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);	
	}

	@Test
	public void testGetBalance() 
	{
		expected = 1000;
		actual = account.getBalance();
		assertEquals(String.format("GetBalance() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
	}

	@Test
	public void testSetAccountNumber() 
	{
		expected = 000001;
		account.setAccountNumber(000001);
		actual = account.getAccountNumber();
		assertEquals(String.format("AccountNumber() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);

		expected = 000002;
		account.setAccountNumber(000002);
		actual = account.getAccountNumber();
		assertEquals(String.format("AccountNumber() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
	}

	@Test
	public void testSetBalance() 
	{
		amount = 100;
		account.setBalance(Commands.DEPOSIT, amount);
		expected = 1100;
		actual = account.getBalance();
		assertEquals(String.format("SetBalance() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);

		amount = 500;
		account.setBalance(Commands.WITHDRAW, amount);
		expected = 600;
		actual = account.getBalance();
		assertEquals(String.format("SetBalance() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
	}
}