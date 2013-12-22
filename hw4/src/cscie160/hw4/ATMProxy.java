package cscie160.hw4;

import java.net.*;
import java.io.*;

/**{@link cscie160.hw4.ATMProxy} Class.
 * Manages the server <--> client connections and requests. 
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW4. */
public class ATMProxy implements ATM 
{
    /**Client socket. */
    Socket socket;    
    /**Adds functionality to output stream. */
    PrintStream  printStream;    
    /**Reads text from a character-input stream. */
    BufferedReader inputReader;
	
    /**Default constructor for {@link cscie160.hw4.ATMProxy}. Establishes a socket when called.
     * @param host Host name. Default localhost.
     * @param port Port number. Default #1099. */
    public ATMProxy(String host, int port) 
	{
    	try
    	{
			socket = new Socket(host, port);
			OutputStream outputStream = socket.getOutputStream();
			printStream = new PrintStream(outputStream);
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			inputReader = new BufferedReader(inputStreamReader);
    	}
    	catch (java.io.IOException io)
    	{
    		io.printStackTrace();
    	}
	}
	
    /**@see cscie160.hw4.ATM#deposit(float) */
    public void deposit(float amount) throws ATMException 
	{
		printStream.println(Commands.DEPOSIT + " " + amount);
    }
	
    /**@see cscie160.hw4.ATM#withdraw(float) */
    public void withdraw(float amount) throws ATMException 
	{
		printStream.println(Commands.WITHDRAW +  " " +  amount);
    }
	
    /**@see cscie160.hw4.ATM#getBalance() */
    public Float getBalance() throws ATMException 
	{
		printStream.println(Commands.BALANCE);
		try 
		{
			String response = inputReader.readLine();
			if (response != null)
			{
				return Float.parseFloat(response.trim());
			} 
			else 
			{
				throw new ATMException("ATMProxy: Unexpected end of stream reading commands in getBalance()");
			}
				
		} 
		catch (Exception ex) 
		{
			throw new ATMException(ex.toString());
		}
    }
}
