package cscie160.project;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**Implements {@link cscie160.project.ATMListener} interface.
 * Obtains a connection to an {@link cscie160.project.ATM} using an {@link cscie160.project.ATMFactory}.
 * It then registers it self with {@link cscie160.project.ATM} as an {@link cscie160.project.ATMListener}.
 * Simulates various scenarios.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class Client extends UnicastRemoteObject implements ATMListener 
{
	/**Default serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**Static {@link cscie160.project.Security} object. */
	private static Security security;

	/**Default constructor for {@link cscie160.project.Client} class.
	 * Calls the super class.
	 * @throws RemoteException. */
	protected Client() throws RemoteException
    {
        super();
    }

	/**{@link cscie160.project.Client#main(String[])} method. 
	 * @param args Command line arguments. */
    public static void main(String[] args)
    {
        try
        {
            Client myClient = new Client();
            
            myClient.doStuff();
        } 
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }
    
    /**@see cscie160.project.ATMListener#doStuff() */
	public void doStuff() throws RemoteException
    {
        ATM atm = null;
        
        try
        {
        	//Looks up for the ATMFactory in the registry.
            ATMFactory factory = (ATMFactory) Naming.lookup("//localhost/atmfactory");
            
            //Looks up for the Security in the registry.
            security = (Security) Naming.lookup("//localhost/security");
            
            atm = factory.getATM();      
            
            if (atm != null)
            {
                try
                { 
                	if(true)
                	{
    	                atm.addListener(this);
    		                
    	                testATM(atm);
    	                        
    	                atm.removeListener(this);
                	}
                } 
                catch (RemoteException e)
                {
                    e.printStackTrace(); System.out.println(e.getMessage() + " An exception occurred while communicating with the ATM");
                }
            }
        }
        catch (Exception e)
        {
           	e.printStackTrace(); System.out.println(e.getMessage() + " An exception occurred while communicating with the ATM");
        }        
        finally
        {
        	atm.endSession();
            atm = null;
        }
    }
    
	/**@see cscie160.project.ATMListener#handler(TransactionNotification) */
    public void handler(TransactionNotification notification) throws RemoteException
    {
        notification.getMessage();
    }
	
    /**Static {@link cscie160.project.Client#testATM(ATM)} method.
     * Simulates various scenarios by calling various test methods.
     * @param atm {@link cscie160.project.ATM} object. */
    public static void testATM(ATM atm) 
	{
		if (atm != null) 
		{
			printBalances(atm);
	        performTestOne(atm);
	        performTestTwo(atm);
	        performTestThree(atm);
	        performTestFour(atm);
	        performTestFive(atm);
	        performTestSix(atm);
	        performTestSeven(atm);
	        performTestEight(atm);
	        performTestNine(atm);
	        printBalances(atm);
		}
	}        
	
    /**Static {@link cscie160.project.Client#printBalances(ATM)} method.
     * Prints {@link cscie160.project.AccountImpl#accountBalance} through the System.out.
     * @param atm {@link cscie160.project.ATM} object. */
	public static void printBalances(ATM atm) 
	{        
		try 
		{
			System.out.println("Balance(0000001): "+ atm.getBalance(getAccountInfo(0000001, 1234)));
	        System.out.println("Balance(0000002): "+ atm.getBalance(getAccountInfo(0000002, 2345)));
	        System.out.println("Balance(0000003): "+ atm.getBalance(getAccountInfo(0000003, 3456)));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**Static {@link cscie160.project.Client#getAccountInfo(int, int)} method.
	 * @param accountNumber Integer value of the {@link cscie160.project.AccountImpl#accountNumber}.
	 * @param PIN String value of the {@link cscie160.project.AccountInfo#PIN}.
	 * @return {@link cscie160.project.AccountInfo} object.
	 * @throws RemoteException. */
	private static AccountInfo getAccountInfo(int accountNumber, int PIN) throws RemoteException
	{
		if (security != null)
		{
			AccountInfo dummyAcountInfo = security.getAccountInfo(accountNumber, PIN);
			
			return dummyAcountInfo;
		}
		else
		{
	       	throw new RemoteException ("An exception occurred while communicating with the Server.");
		}
	}

	/**{@link cscie160.project.Client#performTestOne(ATM)} method.
	 * @param atm {@link cscie160.project.ATM} object. */
	public static void performTestOne(ATM atm) 
	{       
		try 
		{
			atm.getBalance(getAccountInfo(0000001, 5555));
		} 
		catch (Exception e) 
		{
			System.out.println("Failed as expected: " + e);
		}
	}
	 
	/**{@link cscie160.project.Client#performTestTwo(ATM)} method.
	 * @param atm {@link cscie160.project.ATM} object. */
	public static void performTestTwo(ATM atm) 
	{       
		try 
		{
			atm.withdraw(getAccountInfo(0000002, 2345), 500);
		} 
		catch (Exception e) 
		{
			System.out.println("Failed as expected: " + e);
		}
	}
	
	
	/**{@link cscie160.project.Client#performTestThree(ATM)} method.
	 * @param atm {@link cscie160.project.ATM} object. */
	public static void performTestThree(ATM atm) 
	{        
		try 
		{
			atm.withdraw(getAccountInfo(0000001, 1234), 50);
		} 
		catch (Exception e) 
		{
			System.out.println("Failed as expected: "+ e);
		}
	}
	
	/**{@link cscie160.project.Client#performTestFour(ATM)} method.
	 * @param atm {@link cscie160.project.ATM} object. */
	public static void performTestFour(ATM atm) 
	{         
		try 
		{
			atm.deposit(getAccountInfo(0000001, 1234), 500);
		} 
		catch (Exception e) 
		{
			System.out.println("Unexpected error: "+ e);
		}
	}
	 
	/**{@link cscie160.project.Client#performTestFive(ATM)} method.
	 * @param atm {@link cscie160.project.ATM} object. */
	public static void performTestFive(ATM atm) 
	{         
		try 
		{
			atm.deposit(getAccountInfo(0000002, 2345), 100);
		} 
		catch (Exception e) 
		{
			System.out.println("Unexpected error: "+ e);
		}
	}
	 
	/**{@link cscie160.project.Client#performTestSix(ATM)} method.
	 * @param atm {@link cscie160.project.ATM} object. */
	public static void performTestSix(ATM atm) 
	{       
		try 
		{
			atm.withdraw(getAccountInfo(0000001, 1234), 100);
		} 
		catch (Exception e) 
		{
			System.out.println("Unexpected error: " + e);
		}
	}
	 
	/**{@link cscie160.project.Client#performTestSeven(ATM)} method.
	 * @param atm {@link cscie160.project.ATM} object. */
	public static void performTestSeven(ATM atm) 
	{        
		try 
		{
			atm.withdraw(getAccountInfo(0000003, 3456), 300);
		} 
		catch (Exception e) 
		{
			System.out.println("Unexpected error: " + e);
		}
	}
	 
	/**{@link cscie160.project.Client#performTestEight(ATM)} method.
	 * @param atm {@link cscie160.project.ATM} object. */
	public static void performTestEight(ATM atm) 
	{        
		try 
		{
			atm.withdraw(getAccountInfo(0000001, 1234), 200);
		} 
		catch (Exception e) 
		{
			System.out.println("Failed as expected: " + e);
		}
	}
	 
	/**{@link cscie160.project.Client#performTestNine(ATM)} method.
	 * @param atm {@link cscie160.project.ATM} object. */
	public static void performTestNine(ATM atm) 
	{        
		try 
		{
			atm.transfer(getAccountInfo(0000001, 1234), getAccountInfo(0000002, 2345), 100);
		} 
		catch (Exception e) 
		{
			System.out.println("Unexpected error: " + e);
	    }
	}

	/**{@link cscie160.project.Client#register(ATM)} method.
	 * Registers {@link cscie160.project.ATM} object as an {@link cscie160.project.ATMListener}
	 * @param atm {@link cscie160.project.ATM} object. 
	 * @return false. */
	public boolean register(ATM atm)
	{
		return false;
	}
}
