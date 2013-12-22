package cscie160.hw5;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;

/**{@link cscie160.hw5.Client} class.
 * @author Namai Noel
 * @version Harvard CSCIE160: Java for Distributed Computing - HW5.*/
public class Client 
{
	/**{@link cscie160.hw5.Client#main(String[])} method for the {@link cscie160.hw5.Client} class.
	 * @param args Default input for the Main() method.*/
	public static void main(String[] args) 
	{
		ATM atm = null;

		
		try 
		{
			ATMFactory factory = (ATMFactory) Naming.lookup("//localhost/atmfactory");
			atm = factory.getATM();
		} 
		catch (MalformedURLException mue) 
		{
			mue.printStackTrace();
		} 
		catch (NotBoundException nbe) 
		{
			nbe.printStackTrace();
		} 
		catch (UnknownHostException uhe) 
		{
			uhe.printStackTrace();
		} 
		catch (RemoteException re) 
		{
			re.printStackTrace();
		}

		if (atm != null) 
		{
			try {
				// Get initial account balance.
				System.out.println("Initial Balances");

				for (int i = 1; i <= 10; i++) 
				{
					System.out.println("Balance(000000" + i + "): " + atm.getBalance(i));
				}
				System.out.println();

				// Make $1000 deposit in account 0000001 and get new balance
				System.out.println("Depositting(0000001): 1000 ");
				atm.deposit(0000001, 1000);
				System.out.println("Balance(0000001): " + atm.getBalance(0000001));

				// Make $100 withdrawal from account 0000002 and get new balance
				System.out.println("Withdrawing(0000002): 100 ");
				atm.withdraw(0000002, 100);
				System.out.println("Balance(0000002): " + atm.getBalance(0000002));

				// Make $500 deposit in account 0000003 and get new balance
				System.out.println("Depositting(0000003): 500 ");
				atm.deposit(0000003, 500);
				System.out.println("Balance(0000003): " + atm.getBalance(0000003));

				// Make $1000 deposit in account 0000004 and get new balance
				System.out.println("Depositting(0000004): 1000 ");
				atm.deposit(0000004, 1000);
				System.out.println("Balance(0000004): " + atm.getBalance(0000004));

				// Make $100 withdrawal from account 0000005 and get new balance
				System.out.println("Withdrawing(0000005): 100 ");
				atm.withdraw(0000005, 100);
				System.out.println("Balance(0000005): " + atm.getBalance(0000005));

				// Make $500 deposit in account 0000006 and get new balance
				System.out.println("Depositting(0000006): 500 ");
				atm.deposit(0000006, 500);
				System.out.println("Balance(0000006): " + atm.getBalance(0000006));

				// Make $1000 deposit in account 0000007 and get new balance
				System.out.println("Depositting(0000007): 1000 ");
				atm.deposit(0000007, 1000);
				System.out.println("Balance(0000007): " + atm.getBalance(0000007));

				// Get final account balance
				System.out.println();
				for (int i = 1; i <= 10; i++) 
				{
					System.out.println("Balance(000000" + i + "): " + atm.getBalance(i));
				}
			} 
			catch (RemoteException re) 
			{
				System.out.println("An exception occurred while communicating with the ATM");
				re.printStackTrace();
			}
		}
	}
}