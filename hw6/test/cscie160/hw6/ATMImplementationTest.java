package cscie160.hw6;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**JUnit test for {@link cscie160.hw6.ATMImplementation} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW6. */
public class ATMImplementationTest
{
	private ATM atm;	
	private float amount;	
	private float balance;	
	private float expected;	
	private float actual;

	@Before
	public void setUp() throws Exception 
	{
		atm = new ATMImplementation();
	}

	@After
	public void tearDown() throws Exception 
	{
		atm = null;
	}

	@Test
	public final void testGetBalance() throws ATMException 
	{
		float expected = 0;
		float actual = atm.getBalance();
		assertEquals(String.format("GetBalance() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
	}

	@Test
	public final void testDeposit() throws ATMException 
	{
		amount = 1000;
		balance = atm.getBalance();
		expected = balance + amount;
		atm.deposit(amount);
		actual = atm.getBalance();
		assertEquals(String.format("Deposit() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
	}

	@Test
	public final void testWithdraw() throws ATMException 
	{
		atm.deposit(1000);
		balance = atm.getBalance();
		amount = 1000;
		expected = balance - amount;
		atm.withdraw(amount);
		actual = atm.getBalance();
		assertEquals(String.format("Withdraw() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
	}
}