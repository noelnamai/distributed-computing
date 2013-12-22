package cscie160.hw4;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**JUnit test for {@link cscie160.hw4.ATMProxy} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW4.
 */
public class ATMProxyTest 
{
	/**Adds functionality to another output stream.*/
    PrintStream  printStream;
    /**Reads text from a character-input stream.*/
    BufferedReader inputReader;	
    /**Client socket.*/
	static Socket myServerSocket;
	/**Host name. Default localhost.*/
    static String host = "localhost";
    /**Port number. Default #1099.*/
	static int port = 1099;
	/**{@link cscie160.hw4.ATM} instance used in the {@link cscie160.hw4.ATMProxyTest} class.*/
    private static ATM atm;
    /**Dummy float value for the initial account balance.*/
    private float balance; 
    /**Float amount for the transaction.*/
    private float amount;
    /**Expected float value after the transaction.*/
    private float expected;
    /**Actual float value after the transaction.*/
    private float actual;    
    
    /**Starts up the server to be used for the test.
     * @throws Exception
     */
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
	
    /**Delays the tests to allow the server to start and connect to the {@link cscie160.hw4.ATMProxyTest}.
     * @throws Exception */
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
        
	/**Test method for {@link cscie160.hw4.ATMProxy#deposit(float)}.
	 * Fail if actual is not equals to expected i.e. 100 for this case.
	 * @throws ATMException
	 */
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

	/**Test method for {@link cscie160.hw4.ATMProxy#withdraw(float)}
	 * Fail if actual is not equals to expected i.e. 0 for this case.
	 * @throws ATMException
	 */
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

	/**Test method for {@link cscie160.hw4.ATMProxy#getBalance()}
	 * Fail if actual is not equals to expected i.e. 0 for this case.
	 * @throws ATMException
	 */
	@Test
	public final void testGetBalance() throws ATMException 
	{
		balance = atm.getBalance();
	    expected = balance; 
		actual = atm.getBalance();			
	    assertEquals(String.format("Error: expected %f, got %f", expected, actual), expected, actual, 0);
	}
}