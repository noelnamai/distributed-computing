package cscie160.hw6;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**JUnit test for {@link cscie160.hw6.Account} class
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW6 */
public class AccountTest 
{
    private Account account;    
	private float amount;	
	private float expected;	
	private float actual;
	
	@Before
	public void setUp() throws Exception 
	{
        account = new Account();
	}

	@After
	public void tearDown() throws Exception
	{
        account = null;
	}

	@Test
	public final void testGetBalance() 
	{
		expected = 0;
		actual = account.getBalance(); 
		assertEquals(String.format("GetBalance() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
	}

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