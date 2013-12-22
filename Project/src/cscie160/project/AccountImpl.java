package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**Extends {@link UnicastRemoteObject}. Implements {@link cscie160.project.Account} interface.
 * Implements the remote {@link cscie160.project.Account} interface. 
 * Provides methods to obtain reference to the {@link cscie160.project.Account} variables.
 * It verifies the appropriate funds are available in the {@link cscie160.project.Account}.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class AccountImpl extends UnicastRemoteObject implements Account
{
	/**Default serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**Integer value of the {@link cscie160.project.AccountImpl#accountNumber}. */
	private int accountNumber;
	/**Integer value of the {@link cscie160.project.AccountImpl#accountBalance}. */
	private int accountBalance;
	
	/**{@link cscie160.project.AccountImpl} class default constructor.
	 * @param accountNumber Integer value of the {@link cscie160.project.AccountImpl#accountNumber}.
	 * @param accountBalance Integer value of the {@link cscie160.project.AccountImpl#accountBalance}.
	 * @throws RemoteException. */
	protected AccountImpl(int accountNumber, int accountBalance) throws RemoteException
	{
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}

	/**@see cscie160.project.Account#setBalance(Commands, int). */
	public void setBalance(Commands ops, int amount) throws RemoteException
	{
		if (ops == Commands.DEPOSIT) 
		{
			accountBalance = accountBalance + amount;
		}
		else if (ops == Commands.WITHDRAW) 
		{
			if(amount <= accountBalance)
			{
				accountBalance = accountBalance - amount;
			}
			else
			{
				throw new ATMException("Insurficient Balance in the Account. Can not surport a Withdrawal.");
			}
		}
	}
	
	/**@see cscie160.project.Account#getAccountNumber(). */
	public int getAccountNumber() throws RemoteException
	{
		return accountNumber;
	}

	/**@see cscie160.project.Account#getBalance(). */
	public int getBalance() throws RemoteException
	{
		return accountBalance;
	}
}