package cscie160.hw4;

import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

/**{@link cscie160.hw4.Server} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW4. */
public class Server 
{	
	/**Waits for requests to come in over the network. It performs some operation based on that request, and then possibly returns a result to the requester.*/
    private ServerSocket serverSocket;
    /**{@link cscie160.hw4.ATMImplementation} instance used in the {@link cscie160.hw4.Server} class.*/
    private ATMImplementation atmImplementation;
    /**Reads text from a character-input stream.*/
    private BufferedReader bufferedReader;
    
    /**Constructor for {@link cscie160.hw4.Server} class.
     * @param port Port number.
     * @throws java.io.IOException  */
    public Server(int port) throws java.io.IOException 
	{
		serverSocket = new ServerSocket(port);
		
		atmImplementation = new ATMImplementation();
    }
	
    /**{@link cscie160.hw4.Server#serviceClient()} accepts a client connection and reads lines from the socket. 
     * Each line is handed to executeCommand for parsing and execution.
     * @throws java.io.IOException  */
    public void serviceClient() throws java.io.IOException 
	{
		System.out.println("Accepting clients now");
		Socket clientConnection = serverSocket.accept();
		
		// Arrange to read input from the Socket	
		InputStream inputStream = clientConnection.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		// Arrange to write result across Socket back to client
		OutputStream outputStream = clientConnection.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
		
		System.out.println("Client acquired on port #" + serverSocket.getLocalPort() + ", reading from socket");
		
		try
		{
			String commandLine;
			
			while ((commandLine = bufferedReader.readLine()) != null) 
			{
				try 
				{
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
				
			}
			
		clientConnection.close();
			
		}
		catch (SocketException sException)
		{
			//Client has stopped sending commands. Exit gracefully.			
			System.out.println("done");
		}
	}
	
	/**The logic here is specific to our protocol.  We parse the string according to that protocol.
	 * @param commandLine String of type command, amount.
	 * @return The balance if the command is BALANCE or null for others.
	 * @throws ATMException */
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
	
	/**@param argv Commandline arguments. Takes int value for the port number. */
	public static void main(String argv[]) 
	{
		/**Default port number set to #1099.*/
		int port = 1099;
		
		if(argv.length > 0) 
		{
			try 
			{
				port = Integer.parseInt(argv[0]);
			} 
			catch (Exception e) 
			{ 
				e.printStackTrace(); 
			}
		}
		
		try 
		{
			Server server = new Server(port);
			server.serviceClient();
			System.out.println("Client serviced");
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		
	}
}