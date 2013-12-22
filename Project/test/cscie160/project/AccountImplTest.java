package cscie160.project;

import static org.junit.Assert.*;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountImplTest
{
	private Account account1, account2, account3;    
	private int expected;	
	private int actual;
	
	@Before
	public void setUp() throws Exception 
	{
        account1 = new AccountImpl(1, 100);
        account2 = new AccountImpl(2, 200);
        account3 = new AccountImpl(3, 300);        
	}

	@After
	public void tearDown() throws Exception
	{
        account1 = null;
        account2 = null;
        account3 = null;
	}
	
	@Test
	public void testSetBalance()
	{
		try
		{
			account1.setBalance(Commands.DEPOSIT, 100);
			expected = 200;
			actual = account1.getBalance();
			assertEquals(String.format("SetBalance()", expected, actual), expected, actual, 0);
			
			account2.setBalance(Commands.WITHDRAW, 100);
			expected = 100;
			actual = account2.getBalance(); 
			assertEquals(String.format("SetBalance()", expected, actual), expected, actual, 0);
			
			try
			{
				account3.setBalance(Commands.WITHDRAW, 400);
				
				fail("No RemoteException thrown.");
			}
			catch (RemoteException e)
			{
				
			}
		} 
		catch (RemoteException e)
		{
			fail("ATMException thrown.");
		}		
	}

	@Test
	public void testGetAccountNumber()
	{
		try
		{
			expected = 1;
			actual = account1.getAccountNumber();
			assertEquals(String.format("GetAccountNumber()", expected, actual), expected, actual, 0);
			
			expected = 2;
			actual = account2.getAccountNumber();
			assertEquals(String.format("GetAccountNumber()", expected, actual), expected, actual, 0);
			
			expected = 3;
			actual = account3.getAccountNumber();
			assertEquals(String.format("GetAccountNumber()", expected, actual), expected, actual, 0);
		} 
		catch (RemoteException e)
		{
			fail("ATMException thrown.");
		}
	}

	@Test
	public void testGetBalance()
	{
		try
		{
			expected = 100;
			actual = account1.getBalance();
			assertEquals(String.format("GetBalance()", expected, actual), expected, actual, 0);
			
			expected = 200;
			actual = account2.getBalance();
			assertEquals(String.format("GetBalance()", expected, actual), expected, actual, 0);
			
			expected = 300;
			actual = account3.getBalance();
			assertEquals(String.format("GetBalance()", expected, actual), expected, actual, 0);
		} 
		catch (RemoteException e)
		{
			fail("ATMException thrown.");
		}
	}

}
