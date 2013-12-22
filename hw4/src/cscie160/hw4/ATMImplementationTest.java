package cscie160.hw4;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**JUnit test for {@link cscie160.hw4.ATMImplementation} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW4. */
public class ATMImplementationTest
{
	/**{@link cscie160.hw4.ATM} instance used for each individual test.*/
	private ATM atm;	
	/**Float amount for the transaction.*/
	private float amount;	
	/**Dummy float value for the initial account balance.*/
	private float balance;	
	/**Expected float value after the transaction.*/
	private float expected;	
	/**Actual float value after the transaction.*/
	private float actual;

	/**Sets up {@link cscie160.hw4.ATM} instance used for each individual test.
	 * @throws Exception */
	@Before
	public void setUp() throws Exception 
	{
		atm = new ATMImplementation();
	}

	/**Tears down {@link cscie160.hw4.ATM} instance by setting it to null.
	 * @throws Exception */
	@After
	public void tearDown() throws Exception 
	{
		atm = null;
	}

	/**Test method for {@link cscie160.hw4.ATMImplementation#getBalance()}.
	 * Fail if actual is not equals to expected i.e. 0 for a new account.
	 * @throws ATMException */
	@Test
	public final void testGetBalance() throws ATMException 
	{
		float expected = 0;
		float actual = atm.getBalance();
		assertEquals(String.format("GetBalance() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
	}

	/**Test method for {@link cscie160.hw4.ATMImplementation#deposit(float)}.
	 * Fail if actual is not equals to expected i.e. 1000 for this case.
	 * @throws ATMException */
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

	/**Test method for {@link cscie160.hw4.ATMImplementation#deposit(float)}.
	 * Fail if actual is not equals to expected i.e. 0 for this case.
	 * @throws ATMException */
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