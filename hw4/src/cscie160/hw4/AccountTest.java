package cscie160.hw4;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**JUnit test for {@link cscie160.hw4.Account} class
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW4
 */
public class AccountTest 
{
    /**{@link cscie160.hw4.Account} instance used for each individual test.*/
    private Account account;    
    /**Float amount for the transaction.*/
	private float amount;	
	/**Expected float value after the transaction.*/
	private float expected;	
	/**Actual float value after the transaction.*/
	private float actual;
	
	/**Sets up {@link cscie160.hw4.Account} instance used for each individual test.
	 * @throws Exception */
	@Before
	public void setUp() throws Exception 
	{
        account = new Account();
	}

	/**Tears down {@link cscie160.hw4.Account} instance by setting it to null.
	 * @throws Exception */
	@After
	public void tearDown() throws Exception
	{
        account = null;
	}

	/**Test method for {@link cscie160.hw4.Account#getBalance}.
	 * Fail if actual is not equals to expected i.e. 0 for a new account. */
	@Test
	public final void testGetBalance() 
	{
		expected = 0;
		actual = account.getBalance(); 
		assertEquals(String.format("GetBalance() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
	}

	/**Test method for {@link cscie160.hw4.Account#getBalance}.
	 * Adds 100 to balance and then subtracts 100 from the balance.
	 * Fail if actual is not equals to expected i.e. 100 for Deposit and 0 for Withdrawal. */
	@Test
	public final void testSetBalance() 
	{
		amount = 100;
		account.setBalance(Commands.DEPOSIT, amount);
		expected = 100;
		actual = account.getBalance(); 
		assertEquals(String.format("SetBalance() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
		
		account.setBalance(Commands.WITHDRAW, amount);
		expected = 0;
		actual = account.getBalance(); 
		assertEquals(String.format("SetBalance() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
	}
}