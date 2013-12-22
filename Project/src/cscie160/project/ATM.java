package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**Remote interface. Extends {@link Remote}.
 * The {@link cscie160.project.ATM} is a remote object that provides various {@link cscie160.project.Bank} operations.
 * Each operation takes an {@link cscie160.project.AccountInfo} object as its first parameter along with operation specific parameters. 
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public interface ATM extends Remote 
{
	/**{@link cscie160.project.ATM#getCashOnHand} method.
	 * Getter method for {@link cscie160.project.ATMImpl#cashOnHand}.
	 * @return Integer value for {@link cscie160.project.ATMImpl#cashOnHand}
	 * @throws RemoteException. */
	public int getCashOnHand() throws RemoteException;
	
	/**{@link cscie160.project.ATM#addListener(ATMListener)} method.
	 * Adds an {@link cscie160.project.ATMListener} object to the {@link cscie160.project.ATMImpl#listeners} list.
	 * @param listener Object of {@link cscie160.project.ATMListener}.
	 * @throws RemoteException. */
	public void addListener(ATMListener listener) throws RemoteException;

	/**{@link cscie160.project.ATMImpl#removeListener(ATMListener)} method.
	 * Removes an {@link cscie160.project.ATMListener} object from the {@link cscie160.project.ATMImpl#listeners} list.
	 * @param listener Object of {@link cscie160.project.ATMListener}.
	 * @throws RemoteException. */
	public void removeListener(ATMListener listener) throws RemoteException;
	
	/**{@link cscie160.project.ATM#deposit(AccountInfo, int)} method.
	 * Provides for the mechanism to carry out a deposit.
	 * Checks with the {@link cscie160.project.Security} for {@link cscie160.project.AccountInfo#depositAccess}.
	 * @param accountInfo Object of {@link cscie160.project.AccountInfo}.
	 * @param amount amount of the transaction.
	 * @throws RemoteException. */
	public void deposit(AccountInfo accountInfo, int amount) throws RemoteException;

	/**{@link cscie160.project.ATM#withdraw(AccountInfo, int)} method.
	 * Provides for the mechanism to carry out a withdrawal.
	 * Checks with the {@link cscie160.project.Security} for {@link cscie160.project.AccountInfo#withdrawalAccess}.
	 * @param accountInfo Object of {@link cscie160.project.AccountInfo}.
	 * @param amount amount of the transaction.
	 * @throws RemoteException. */
	public void withdraw(AccountInfo accountInfo, int amount) throws RemoteException;

	/**{@link cscie160.project.ATM#getBalance(AccountInfo)} method.
	 * Provides for the mechanism to carry out a balance inquiry.
	 * Checks with the {@link cscie160.project.Security} for {@link cscie160.project.AccountInfo#balanceAccess}.
	 * @param accountInfo Object of {@link cscie160.project.AccountInfo}.
	 * @return Integer value of the {@link cscie160.project.AccountImpl#accountBalance}.
	 * @throws RemoteException. */
	public int getBalance(AccountInfo accountInfo) throws RemoteException;
	
	/**{@link cscie160.project.ATM#transfer(AccountInfo, AccountInfo, int)} method.
	 * Provides for the mechanism to carry out funds transfer.
	 * @param accountInfo1 Object of {@link cscie160.project.AccountInfo}.
	 * @param accountInfo2 Object of {@link cscie160.project.AccountInfo}.
	 * @param amount amount of the transaction.
	 * @throws RemoteException. */
	public void transfer(AccountInfo accountInfo1, AccountInfo accountInfo2, int amount) throws RemoteException;
	
	/**{@link cscie160.project.ATM#notifyListeners(Commands, int, AccountInfo, AccountInfo)} method.
	 * @param command {@link cscie160.project.Commands} value of the transaction.
	 * @param amount amount of the transaction.
	 * @param accountInfoFrom Object of {@link cscie160.project.AccountInfo}.
	 * @param accountInfoTo Object of {@link cscie160.project.AccountInfo}.
	 * @throws RemoteException. */
	public void notifyListeners(Commands command, int amount, AccountInfo accountInfoFrom, AccountInfo accountInfoTo) throws RemoteException;

	/**{@link cscie160.project.ATM#endSession()} method.
	 * Ends the current session and notifies all listeners of the same.
	 * @throws RemoteException. */
	public void endSession() throws RemoteException;
}