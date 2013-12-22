package cscie160.hw5;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**{@link cscie160.hw5.ATMImpl} class.
 * @author Namai Noel
 * @version Harvard CSCIE160: Java for Distributed Computing - HW5.*/
public class ATMImpl extends UnicastRemoteObject implements ATM 
{
	/**Default float value for
	 * {@link cscie160.hw5.ATMException#serialVersionUID}.*/
	private static final long serialVersionUID = 1L;

	/**{@link HashMap} to hold <int accountNumber, {@link cscie160.hw5.Account} account>.*/
	private HashMap<Integer, Account> accountsHashMap;

	/**
	 * @throws RemoteException
	 */
	public ATMImpl() throws RemoteException 
	{
		accountsHashMap = new HashMap<Integer, Account>();

		float[] balances = { 0, 100, 200, 300, 400, 500, 600, 700, 800, 900 };

		for (int accountNumber = 1; accountNumber <= 10; accountNumber++) 
		{
			Account myAccount = new Account();

			myAccount.setAccountNumber(accountNumber);

			myAccount.setBalance(Commands.DEPOSIT, balances[accountNumber - 1]);

			accountsHashMap.put(accountNumber, myAccount);
		}
	}

	/**@see cscie160.hw5.ATM#deposit(int, float).*/
	public void deposit(int accountNumber, float amount) throws ATMException 
	{
		Account dummyAccount = accountsHashMap.get(accountNumber);

		if (amount <= 0) 
		{
			throw new ATMException("No deposit made.");
		} 
		else 
		{
			dummyAccount.setBalance(Commands.DEPOSIT, amount);
		}
	}

	/** @see cscie160.hw5.ATM#withdraw(int, float). */
	public void withdraw(int accountNumber, float amount) throws ATMException 
	{
		Account dummyAccount = accountsHashMap.get(accountNumber);

		if (amount > dummyAccount.getBalance()) 
		{
			throw new ATMException("Inssurficient Amount. Can't support a withdrawal.");
		} 
		else 
		{
			dummyAccount.setBalance(Commands.WITHDRAW, amount);
		}
	}

	/** @see cscie160.hw5.ATM#getBalance(int). */
	public Float getBalance(int accountNumber) throws ATMException 
	{
		Account dummyAccount = accountsHashMap.get(accountNumber);

		if (dummyAccount.getBalance() < 0)
		{
			throw new ATMException("Balance is ZERO.");
		}
		else
		{
			return dummyAccount.getBalance();
		}
	}
}