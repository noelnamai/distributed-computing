package cscie160.hw6;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**JUnit test for {@link cscie160.hw6.ATMProxy} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW6. */
public class ATMProxyTest 
{
    PrintStream  printStream;
    BufferedReader inputReader;	
	static Socket myServerSocket;
    static String host = "localhost";
	static int port = 1099;
    private static ATM atm;
    private float balance; 
    private float amount;
    private float expected;
    private float actual;    

    @BeforeClass
	public static void setUpBeforeClass() throws Exception
	{	
    	Thread serverThread = new Thread() 
    	{
            public void run() 
            {
                myServerSocket = null;
                
                try 
                {
                	Server myServer = new Server(port);
        			myServer.serviceClient();
                } 
                catch (IOException e) 
                {
                	e.printStackTrace();
                }
            }
    	};
    	
    	serverThread.start();
    	
        atm = new ATMProxy(host, port);
	}
	
    @Before
	public void setUp() throws Exception 
	{
    	synchronized (this) 
    	{
    		try 
    		{
    			wait(1000);
            } 
    		catch (InterruptedException e) 
    		{
    			// do nothing.
            }
      	}
    }
        
	@Test
	public final void testDeposit() throws ATMException 
	{
	    balance = atm.getBalance();
	    amount = 100;
	    expected = balance + amount; 
		atm.deposit(amount);
	    actual = atm.getBalance();	    	
	    assertEquals(String.format("Error: expected %f, got %f", expected, actual), expected, actual, 0);
	}

	@Test
	public final void testWithdraw() throws ATMException 
	{
	    balance = atm.getBalance();
	    amount = 100;
	    expected = balance - amount; 
	    atm.withdraw(amount);
	    actual = atm.getBalance();	    	
	    assertEquals(String.format("Error: expected %f, got %f", expected, actual), expected, actual, 0);
	}

	@Test
	public final void testGetBalance() throws ATMException 
	{
		balance = atm.getBalance();
	    expected = balance; 
		actual = atm.getBalance();			
	    assertEquals(String.format("Error: expected %f, got %f", expected, actual), expected, actual, 0);
	}
}