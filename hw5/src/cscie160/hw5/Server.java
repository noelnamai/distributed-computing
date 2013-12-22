package cscie160.hw5;

import java.rmi.Naming;

/**{@link cscie160.hw5.Server} Class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW4.*/
public class Server 
{
	/**The {@link cscie160.hw5.Server#main(String[])} method of the {@link cscie160.hw5.Server} Class.
	 * @param args Command line {@link String} arguments to be taken by the {@link cscie160.hw5.Server#main(String[])}.*/
	public static void main(String args[]) 
	{
		try 
		{
			ATMFactoryImpl obj = new ATMFactoryImpl();
			Naming.rebind("//localhost/atmfactory", obj);
			System.out.println("ATMFactory bound in registry");
		} 
		catch (Exception e) 
		{
			System.out.println("ATMFactoryImpl err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}