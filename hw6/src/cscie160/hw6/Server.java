package cscie160.hw6;

import java.net.*;
import java.util.LinkedList;
import java.io.*;

/**
 * {@link cscie160.hw6.Server} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW6. 
 */
public class Server 
{	
	/**Waits for requests to come in over the network. It performs some operation based on that request, and then possibly returns a result to the requester.*/
	private ServerSocket serverSocket;
	
    /**Reads text from a character-input stream.*/
    private BufferedReader bufferedReader;
    
    /**String of type command, amount. */
	private String commandLine;
	
	/**int constant for the number of threads in the worker threadpool. */
    private final static int nThreads = 5; 
 
    /**Array of type ATMThreads. Acts as the worker threadpool. */
	private ATMThreads[] threads;
	
	/**LinkedList of type ATMRunnable.  Acts as a queue for the runnables.*/
	public LinkedList<ATMRunnable> queue;
	
	/**{@link cscie160.hw6.ATMImplementation} instance used in the {@link cscie160.hw6.Server} class.*/
	ATMImplementation atmImplementation = new ATMImplementation();
    
    /**
     * Creates a thread pool and sets their names.
     * Calls the start() method of the threads created.
     * @param port int value of the connection port.
     * @throws java.io.IOException     
     */
    public Server(int port) throws java.io.IOException 
	{
		serverSocket = new ServerSocket(port);
		queue = new LinkedList<ATMRunnable>();
		threads = new ATMThreads[nThreads];
		
		for(int j = 0; j < nThreads; j ++)
		{
			threads[j] = new ATMThreads(queue);
			threads[j].setName("" + j);
			threads[j].start();
		}
    }
	
    /**
     * {@link cscie160.hw6.Server#serviceClient()} accepts a client connection and reads lines from the socket. 
     * Creates an new {@link cscie160.hw6.ATMRunnable} object.
     * The transaction string that the server read from the Socket, a reference to the ATM object, and an output object onto which it can write the result back to the client are passed into the {@link cscie160.hw6.ATMRunnable} object.    
     * @throws java.io.IOException  
     */
    public void serviceClient() throws java.io.IOException 
	{  
    	try
    	{ 	
	    	System.out.println("Accepting clients now");
			Socket clientConnection = serverSocket.accept();
				
			// Arrange to read input from the Socket	
			InputStream inputStream = clientConnection.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				
			System.out.println("Client acquired on port #" + serverSocket.getLocalPort() + ", reading from socket");
				
			while ((commandLine = bufferedReader.readLine()) != null)
			{	
				ATMRunnable atmRunnable = new ATMRunnable(clientConnection, atmImplementation, commandLine);
				
				// Adds a runnable to the queue and calls notify() on the request queue.
				synchronized(queue) 
				{	
					queue.addLast(atmRunnable);
					queue.notify();
				}
			}
			//Close Socket connection.
			clientConnection.close();
    	}
    	catch (SocketException se) 
    	{
    		//Client has stopped sending commands. Exit gracefully.
    		System.exit(0);
    	}
	}

    /**@param argv Command-line arguments. Takes int value for the port number. */
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
			
			//Prints Client serviced once the process is complete.
			System.out.println("Client serviced");
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
}