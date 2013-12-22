package cscie160.project;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**The {@link cscie160.project.BankServer} starts up the rmiregistry and creates a {@link cscie160.project.Bank} instance and a {@link cscie160.project.Security} instance. 
 * It then registers each with the naming service rmiregistry to make each accessible by remote lookup.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class BankServer
{	
	/**{@link cscie160.project.BankServer#main(String[])} method.
	 * @param args Command line parameters. */
    public static void main(String[] args)
    {
        try
        {
        	try
        	{
        		//Try to create registry if none exists. If one exists in the port, an exception will be thrown.
	        	LocateRegistry.createRegistry(1099);
	        	System.out.println("RMI registry ready."); 
        	}
        	catch (Exception e) 
        	{
        		//Locates the existing registry and uses it.
				LocateRegistry.getRegistry(1099);
				System.out.println("RMI registry ready."); 
			}
        	finally
        	{
	            BankImpl bankObj = BankImpl.getInstance();
	            Naming.rebind("//localhost/bank", bankObj);
	            System.out.println("Bank bound in registry");
	            
	            SecurityImpl securityObj = SecurityImpl.getInstance();
	            Naming.rebind("//localhost/security", securityObj);
	            System.out.println("Security bound in registry");
        	}
        } 
        catch (Exception e)
        {
            System.out.println("Bank error: " + e.getMessage());
        }
    }
}