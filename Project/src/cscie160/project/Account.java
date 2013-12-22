package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**Remote interface. Extends {@link Remote}.
 * Provides methods for adding and subtracting funds from the {@link cscie160.project.Account}.
 * It verifies the appropriate funds are available in the {@link cscie160.project.Account}.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public interface Account extends Remote
{
	/**{@link cscie160.project.Account#setBalance(Commands, int)} method.
	* Setter method for {@link cscie160.project.AccountImpl#accountBalance}.
	* @param ops {@link cscie160.project.Commands} value of the transaction.
	* @param amount int value of the transaction amount.
	* @throws RemoteException. */
	public void setBalance(Commands ops, int amount) throws RemoteException;
	
	/**{@link cscie160.project.Account#getAccountNumber()} method.
	 * Getter method for {@link cscie160.project.AccountImpl#accountNumber}.
	 * @return Integer value of the {@link cscie160.project.AccountImpl#accountNumber}. 
	 * @throws RemoteException. */
    public int getAccountNumber() throws RemoteException;
    
    /**{@link cscie160.project.Account#getBalance()} method.
     * Getter method for {@link cscie160.project.AccountImpl#accountBalance}.
	 * @return Integer value of the {@link cscie160.project.AccountImpl#accountBalance}.
     * @throws RemoteException. */
    public int getBalance() throws RemoteException;
}