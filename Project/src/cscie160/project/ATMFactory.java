package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**Remote interface. Extends {@link Remote}.
 * The {@link cscie160.project.ATMFactory} is a remote object that is responsible for providing reference to a remote {@link cscie160.project.ATM} instance.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public interface ATMFactory extends Remote 
{
	/**{@link cscie160.project.ATMFactory#getATM()} method.
	 * @return {@link cscie160.project.ATM} object.
	 * @throws RemoteException. */
	public ATM getATM() throws RemoteException;
}