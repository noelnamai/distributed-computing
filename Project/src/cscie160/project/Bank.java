package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**Extends {@link Remote}.
 * The Bank is a remote object that hosts remote {@link cscie160.project.Account} objects.
 * Provides methods to obtain reference to remote {@link cscie160.project.Account} objects.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public interface Bank extends Remote
{
	/**{@link cscie160.project.BankImpl#getAccount} method.
	 * Getter method for {@link cscie160.project.AccountImpl#accountNumber}.
	 * @param accountNumber Integer value of the {@link cscie160.project.AccountImpl#accountNumber}.
	 * @return {@link cscie160.project.Account} object from the {@link cscie160.project.BankImpl#accountsHashMap}. */
    public Account getAccount(int accountNumber) throws RemoteException;

    /**{@link cscie160.project.BankImpl#getSecurity} method.
     * Getter method for {@link cscie160.project.SecurityImpl} instance.
	 * @return {@link cscie160.project.Security} instance. */
    public SecurityImpl getSecurity() throws RemoteException;
}