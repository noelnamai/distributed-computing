package cscie160.hw5;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**{@link cscie160.hw5.ATMFactory} Interface.
 * @author Namai Noel
 * @version Harvard CSCIE160: Java for Distributed Computing - HW5.*/
public interface ATMFactory extends Remote 
{
	/**Method to get an {@link cscie160.hw5.ATM} instance.
	 * @return {@link cscie160.hw5.ATM} instance.
	 * @throws RemoteException*/
	public ATM getATM() throws RemoteException;
}