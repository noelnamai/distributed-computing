package cscie160.hw5;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**{@link cscie160.hw5.ATMImplTest} Class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW5.*/
public class ATMImplTest 
{
	private ATMImpl atm;
	private float expected;
	private float actual;
	private int accountNumber;
	private float amount;
	private Float balance;

	@Before
	public void setUp() throws Exception 
	{
		atm = new ATMImpl();
		atm.deposit(000001, 1000);
	}

	@After
	public void tearDown() throws Exception 
	{
		atm = null;
	}

	@Test
	public void testDeposit() 
	{
		try
		{
			amount = 1000;
			accountNumber = 000001;
			balance = atm.getBalance(accountNumber);
			expected = balance + amount;
			atm.deposit(accountNumber, amount);
			actual = atm.getBalance(accountNumber);
			assertEquals(String.format("Deposit() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
		}
		catch (ATMException e)
		{
			fail("ATMException thrown.");
		}
		
		try
		{
			amount = -500;
			accountNumber = 000001;
			atm.deposit(accountNumber, amount);
			atm.getBalance(accountNumber);
			fail("ATMException not thrown.");
		}
		catch (ATMException e)
		{
			
		}
	}

	@Test
	public void testWithdraw() throws ATMException 
	{
		try
		{
			amount = 200;
			accountNumber = 000001;
			atm.deposit(accountNumber, amount);
			balance = atm.getBalance(accountNumber);
			expected = balance - amount;
			atm.withdraw(accountNumber, amount);
			actual = atm.getBalance(accountNumber);
			assertEquals(String.format("Deposit() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
		}
		catch (ATMException e)
		{
			fail("ATMException thrown.");
		}
		
		try
		{
			amount = 5000;
			accountNumber = 000001;
			atm.withdraw(accountNumber, amount);
			atm.getBalance(accountNumber);
			fail("ATMException not thrown.");
		}
		catch (ATMException e)
		{
			
		}
	}

	@Test
	public void testGetBalance() throws ATMException 
	{
		try
		{
			accountNumber = 000001;
			expected = 1000;
			actual = atm.getBalance(accountNumber);
			assertEquals(String.format("GetBalance() - Expected: %f, Found: %f", expected, actual), expected, actual, 0);
		}
		catch (ATMException e)
		{
			fail("ATMException thrown.");
		}
		
		try
		{
			accountNumber = 000001;
			atm.withdraw(00001, 10000);
			atm.getBalance(accountNumber);
			fail("ATMException not thrown.");
		}
		catch (ATMException e)
		{
			
		}
	}
}