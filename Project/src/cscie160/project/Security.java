package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**Remote interface. Extends {@link Remote}.
 * Remote interface. Provides methods for the {@link cscie160.project.SecurityImpl} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. 
 */
public interface Security extends Remote
{	
	/**{@link cscie160.project.Security#getAccountInfo(int, int)} method.
	 * @param accountNumber accountNumber Integer value of the {@link cscie160.project.AccountImpl#accountNumber}.
	 * @param PIN PIN String value of the {@link cscie160.project.AccountInfo#PIN}.
	 * @return {@link cscie160.project.AccountInfo} object.
	 * @throws RemoteException. */
	public AccountInfo getAccountInfo(int accountNumber, int PIN) throws RemoteException;
	
	/**{@link cscie160.project.Security#setAccess(int, String, boolean, boolean, boolean)} method.
	 * @param accountNumber Integer value of the {@link cscie160.project.AccountImpl#accountNumber}.
	 * @param PIN String value of the {@link cscie160.project.AccountInfo#PIN}.
	 * @param depositAccess Boolean value of {@link cscie160.project.AccountInfo#depositAccess}.
	 * @param withdrawalAccess Boolean value of {@link cscie160.project.AccountInfo#withdrawalAccess}.
	 * @param balanceAccess Boolean value of {@link cscie160.project.AccountInfo#balanceAccess}.
	 * @throws RemoteException. */
	public void setAccess(int accountNumber, String PIN, boolean depositAccess, boolean withdrawalAccess, boolean balanceAccess) throws RemoteException;

	/**{@link cscie160.project.Security#checkPermission(int, String)} method.
	 * @param accountNumber Integer value of the {@link cscie160.project.AccountImpl#accountNumber}.
	 * @param PIN String value of the {@link cscie160.project.AccountInfo#PIN}.
	 * @return Boolean. true if permission is granted, otherwise false.
	 * @throws RemoteException. */
	public boolean checkPermission(int accountNumber, String PIN) throws RemoteException;

	/**{@link cscie160.project.Security#checkPermission(int, Commands)} method.
	 * @param accountNumber Integer value of the {@link cscie160.project.AccountImpl#accountNumber}.
	 * @param cmd {@link cscie160.project.Commands} value of the transaction.
	 * @return Boolean. true if permission is granted, otherwise false.
	 * @throws RemoteException. */
	public boolean checkPermission(int accountNumber, Commands cmd) throws RemoteException;
}