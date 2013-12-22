package cscie160.project;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**Remote interface.
 * {@link cscie160.project.ATMServer} starts up and registers the {@link cscie160.project.ATMFactory} in the rmiregistry to make it accessible by remote lookup.. 
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class ATMServer
{
	/**{@link cscie160.project.ATMServer#main(String[])} method.
	 * @param args Command line parameters. */
	public static void main(String[] args)
    {
		try
		{  
			LocateRegistry.getRegistry(1099);
			System.out.println("RMI registry ready."); 
			
			ATMFactoryImpl obj = new ATMFactoryImpl();
            Naming.rebind("//localhost/atmfactory", obj);
            System.out.println("ATMFactoryImpl bound in registry");
		} 
        catch (Exception e)
        {
        	System.out.println("ATMFactoryImpl error: " + e.getMessage());
        }
    }
}