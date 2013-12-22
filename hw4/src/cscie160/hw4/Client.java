package cscie160.hw4;
 
/**{@link cscie160.hw4.Client} class.
 * Connects to the server using Java networking and communicates with the server using a defined protocol.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW4. */
public class Client 
{
	/**@param args Command line arguments. Takes localhost, port.*/
	public static void main(String[] args) 
	{
		try 
		{
			// Parse command line arguments
            String host = args[0];
            
            int port = Integer.parseInt(args[1]);
            
            ATM atm = new ATMProxy(host, port);
            
            // Get initial account balance
            System.out.println("Balance: "+atm.getBalance());
            
            // Make $1000 deposit and get new balance    
            System.out.println(" Depositing: 1000");
            atm.deposit(1000);
            System.out.println("Balance: "+atm.getBalance());
            
            // Make $250 withdrawal and get new balance
            System.out.println(" Withdrawing: 250");
            atm.withdraw(250);
            System.out.println("Balance: "+atm.getBalance());
            
            // Make $750 withdrawal and get new balance
            System.out.println(" Withdrawing: 750");
            atm.withdraw(750);
            System.out.println("Balance: "+atm.getBalance());
		} 
		catch (ATMException ae) 
		{
            System.out.println("An exception occurred while communicating with the ATM");
            ae.printStackTrace();
		}
	}
}