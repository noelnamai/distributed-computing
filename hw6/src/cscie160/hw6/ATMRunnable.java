package cscie160.hw6;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * {@link cscie160.hw6.ATMRunnable} class.
 * Class representing a work order for a client request to be picked up by worker thread.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW6.  
 */
public class ATMRunnable implements Runnable
{		
	private String commandLine;
	private Socket clientConnection;
	private OutputStream outputStream;
	private PrintStream printStream;
	private ATMImplementation atmImplementation;
	
	/**
	 * Default constructor of the {@link cscie160.hw6.ATMRunnable} class.
	 * @param clientConnection Socket connection to the Client.
	 * @param atmImplementation Object of type {@link cscie160.hw6.ATMImplementation}.
	 * @param commandLine String read from the Socket connection. 
	 */
	public ATMRunnable(Socket clientConnection, ATMImplementation atmImplementation, String commandLine)
	{
		this.commandLine = commandLine;
		this.clientConnection = clientConnection;
		this.atmImplementation = atmImplementation;
	}
	
	/**
	 * {@link cscie160.hw6.ATMRunnable#run()} method for the {@link cscie160.hw6.ATMRunnable} class.
	 * @see java.lang.Runnable#run() 
	 */
	public void run()
	{
		try
		{
			// Arrange to write result across Socket back to client
			outputStream = clientConnection.getOutputStream();
			printStream = new PrintStream(outputStream);
			
			Float result = executeCommand(commandLine);
			
			//Only BALANCE command returns non-null
			if (result != null) 
			{ 
				//Write it back to the client
				printStream.println(result);  
			}
		} 
		catch (ATMException atmex) 
		{
			System.out.println("ERROR: " + atmex);
		} 
		catch (IOException io)
		{
			io.printStackTrace();
		}
	}
	
	/**
	 * The logic here is specific to our protocol.  We parse the string according to that protocol.
	 * @param commandLine String of type command, amount.
	 * @return The balance if the command is BALANCE or null for others.
	 * @throws ATMException 
	 */
	private Float executeCommand(String commandLine) throws ATMException 
	{
		//Break out the command line into String[]
		StringTokenizer tokenizer = new StringTokenizer(commandLine);
		String commandAndParam[] = new String[tokenizer.countTokens()];
		
		int index = 0;
		
		while (tokenizer.hasMoreTokens()) 
		{
			commandAndParam[index++] = tokenizer.nextToken();
		}
		
		String command = commandAndParam[0];
		
		//Dispatch BALANCE request without further ado.
		if (command.equalsIgnoreCase(Commands.BALANCE.toString())) 
		{
			return atmImplementation.getBalance();
		}
		
		//Must have 2nd arg for amount when processing DEPOSIT/WITHDRAW commands
		if (commandAndParam.length < 2) 
		{
			throw new ATMException("Missing amount for command \"" + command + "\"");
		}
		try 
		{
			float amount = Float.parseFloat(commandAndParam[1]);
			
			if (command.equalsIgnoreCase(Commands.DEPOSIT.toString())) 
			{
				atmImplementation.deposit(amount);	  
			}
			else if (command.equalsIgnoreCase(Commands.WITHDRAW.toString())) 
			{
				atmImplementation.withdraw(amount);
			} 
			else 
			{
				throw new ATMException("Unrecognized command: " + command);
			}
		} 
		catch (NumberFormatException nfe) 
		{
			throw new ATMException("Unable to make float from input: " + commandAndParam[1]);
		}
		
		//BALANCE command returned result above. Other commands return null.
		return null;
	}
}