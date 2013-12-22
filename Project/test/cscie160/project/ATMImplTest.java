package cscie160.project;

import static org.junit.Assert.*;

import java.rmi.Naming;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ATMImplTest
{
	private ATMImpl atm;
	private int expected;
	private int actual;
	private Security security;
	private AccountInfo accountInfo, accountInfoFrom, accountInfoTo;

	@Before
	public void setUpBeforeClass() throws Exception 
	{
		atm = new ATMImpl();
		
		security = (Security) Naming.lookup("//localhost/security");
	}

	@After
	public void tearDownAfterClass() throws Exception 
	{
		atm = null;
	}

	@Test
	public void testDeposit()
	{
		try
		{
			accountInfo = security.getAccountInfo(1, 1234);
			expected = 500;
			atm.deposit(accountInfo, 500);
			actual = atm.getBalance(accountInfo);
			assertEquals(String.format("deposit()", expected, actual), expected, actual, 0);
			
			accountInfo = security.getAccountInfo(2, 2345);
			expected = 800;
			atm.deposit(accountInfo, 700);
			actual = atm.getBalance(accountInfo);
			assertEquals(String.format("deposit()", expected, actual), expected, actual, 0);
		} 
		catch (RemoteException e)
		{
			fail("ATMException thrown.");
		}
		
		try
		{
			accountInfo = security.getAccountInfo(3, 3456);
			atm.deposit(accountInfo, 200);
			
			fail("ATMException Not Thrown.");		
		} 
		catch (RemoteException e)
		{
			//do nothing
		}
	}

	@Test
	public void testWithdraw()
	{
		try
		{
			accountInfo = security.getAccountInfo(1, 1234);
			expected = 300;
			atm.withdraw(accountInfo, 200);
			actual = atm.getBalance(accountInfo);
			assertEquals(String.format("withdraw()", expected, actual), expected, actual, 0);
			
			accountInfo = security.getAccountInfo(3, 3456);
			expected = 300;
			atm.withdraw(accountInfo, 200);
			assertEquals(String.format("withdraw()", expected, actual), expected, actual, 0);
		} 
		catch (RemoteException e)
		{
			fail("ATMException thrown.");
		}
		
		try
		{
			accountInfo = security.getAccountInfo(2, 2345);
			atm.withdraw(accountInfo, 200);	
			
			fail("ATMException Not Thrown.");	
		} 
		catch (RemoteException e)
		{
			//do nothing
		}
	}

	@Test
	public void testTransfer()
	{
		try
		{
			accountInfoFrom = security.getAccountInfo(1, 1234);
			accountInfoTo = security.getAccountInfo(2, 2345);
			
			atm.transfer(accountInfoFrom, accountInfoTo, 100);
			
			expected = 200;
			actual = atm.getBalance(accountInfoFrom);
			assertEquals(String.format("transfer()", expected, actual), expected, actual, 0);
			
			expected = 900;
			actual = atm.getBalance(accountInfoTo);
			assertEquals(String.format("transfer()", expected, actual), expected, actual, 0);
		}
		catch (RemoteException e)
		{
			fail("ATMException thrown.");
		}
		
		try
		{
			accountInfoFrom = security.getAccountInfo(2, 2345);
			accountInfoTo = security.getAccountInfo(3, 3456);
			
			atm.transfer(accountInfoFrom, accountInfoTo, 200);
			
			fail("ATMException Not Thrown.");
		}
		catch (RemoteException e)
		{
			//do nothing
		}
	}
}
