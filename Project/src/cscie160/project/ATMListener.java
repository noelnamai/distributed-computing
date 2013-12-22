package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**Remote interface. Extends {@link Remote}.
 * The {@link cscie160.project.ATMImpl} interface includes a method to receive TransactionNotification messages from the {@link cscie160.project.ATM}. 
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public interface ATMListener extends Remote
{
	/**{@link cscie160.project.ATMListener#doStuff()} method.
	 * The {@link cscie160.project.ATMListener#doStuff()} method process calls to the rmiregistry to get a reference to the {@link cscie160.project.ATMFactory}. 
	 * It is that object that creates the {@link cscie160.project.ATM} and returns a reference to it for the {@link cscie160.project.Client} to use.
	 * @throws RemoteException. */
	public void doStuff() throws RemoteException;
	
	/**{@link cscie160.project.ATMListener#handler(TransactionNotification)} method.
	 * @param transactionNotification {@link cscie160.project.TransactionNotification} object. Includes pertinent information about a particular transaction including target accounts and amounts.
	 * @throws RemoteException. */
    public void handler(TransactionNotification transactionNotification) throws RemoteException;
}
