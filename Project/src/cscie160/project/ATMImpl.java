package cscie160.project;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

/**Extends {@link UnicastRemoteObject}. Implements {@link cscie160.project.ATM} interface.
 * The {@link cscie160.project.ATMImpl} is an object that provides various {@link cscie160.project.Bank} operations.
 * Each operation takes an {@link cscie160.project.AccountInfo} object as its first parameter along with operation specific parameters. 
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class ATMImpl extends UnicastRemoteObject implements ATM
{
	/**Default serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**Integer value of the {@link cscie160.project.ATMImpl#cashOnHand}. We assume that the {@link cscie160.project.ATM} begins with $500 cash. */
	private int cashOnHand = 500;
	/**{@link Set} to hold {@link cscie160.project.ATMListener} objects. */
	private Set<ATMListener> listeners;
	/**{@link cscie160.project.Bank} object. */
	private Bank bank;
	
	/**{@link cscie160.project.ATMImpl} default constructor.
	 * Calls the super class and looks up for an {@link cscie160.project.Bank} object in the registry.
	 * @throws RemoteException. */
	public ATMImpl() throws RemoteException
	{
		super();	
		
		listeners = new HashSet<ATMListener>();
		
		try
		{
			bank = (Bank) Naming.lookup("//localhost/bank");
		} 
		catch (MalformedURLException | NotBoundException e) 
		{
			e.getMessage(); e.printStackTrace();
		}
	}
	
	/**@see cscie160.project.ATM#getCashOnHand() */
	public int getCashOnHand() throws RemoteException
	{
		return cashOnHand;		
	}
	
	/**@see cscie160.project.ATM#addListener(ATMListener) */
	public void addListener(ATMListener listener) throws RemoteException
	{
		listeners.add(listener);
	}
	
	/**@see cscie160.project.ATM#removeListener(ATMListener) */
	public void removeListener(ATMListener listener) throws RemoteException
	{
		listeners.remove(listener);
	}
	
	/**@see cscie160.project.ATM#getBalance(AccountInfo) */
	public int getBalance(AccountInfo accountInfoFrom) throws RemoteException 
	{
		Account dummyAccountFrom = bank.getAccount(accountInfoFrom.getAccountNumber());
		
		if (accountInfoFrom.getBalanceAccess() == true)
		{
			notifyListeners(Commands.BALANCE, 0, accountInfoFrom, null);
			
			return dummyAccountFrom.getBalance();
		}
		else
		{
			throw new ATMException("Check Balance Access Denied by the Bank.");
		}
	}

	/**@see cscie160.project.ATM#deposit(AccountInfo, int) */
	public void deposit(AccountInfo accountInfoTo, int amount) throws RemoteException 
	{
		Account dummyAccountTo = bank.getAccount(accountInfoTo.getAccountNumber());
						
		if (accountInfoTo.getIsItTransfer() != true)
		{
			notifyListeners(Commands.DEPOSIT, amount, null, accountInfoTo);
		}
		
		if (amount > 0)  
		{
			if (accountInfoTo.getDepositAccess() == true)
			{
				dummyAccountTo.setBalance(Commands.DEPOSIT, amount);
			}
			else
			{
				throw new ATMException("Deposit Access Denied by the Bank.");
			}
		}
		else
		{
			throw new ATMException("No deposit made. Amount less than or equals to 0.");
		}
	}

	/**@see cscie160.project.ATM#withdraw(AccountInfo, int) */
	public void withdraw(AccountInfo accountInfoFrom, int amount) throws RemoteException 
	{
		Account dummyAccountFrom = bank.getAccount(accountInfoFrom.getAccountNumber());
		
		if (accountInfoFrom.getIsItTransfer() != true)
		{
			notifyListeners(Commands.WITHDRAW, amount, accountInfoFrom, null);
		}
		
		if ((amount <= cashOnHand) || ((accountInfoFrom.getIsItTransfer() == true) && (amount > cashOnHand)))
		{
			if (accountInfoFrom.getWithdrawalAccess() == true)
			{
				dummyAccountFrom.setBalance(Commands.WITHDRAW, amount);
				
				cashOnHand = cashOnHand - amount;
			}
			else
			{
				throw new ATMException("Withdrawal Access Denied by the Bank.");
			}
		}
		else
		{
			throw new ATMException("Insurficient Funds in the ATM. Can't support a withdrawal.");
		}
	}

	/**@see cscie160.project.ATM#transfer(AccountInfo, AccountInfo, int) */
	public void transfer(AccountInfo accountInfoFrom, AccountInfo accountInfoTo, int amount) throws RemoteException
	{		
		try
		{ 
			accountInfoFrom.itIsTransfer(1);

			withdraw(accountInfoFrom, amount);
				
			try
			{
				accountInfoTo.itIsTransfer(1);
				
				deposit(accountInfoTo, amount);
						
				notifyListeners(Commands.TRANSFER, amount, accountInfoFrom, accountInfoTo);
			}
			catch (ATMException e) 
			{
				throw new ATMException(e.getMessage());
			}
		}
		catch (ATMException e) 
		{
			throw new ATMException(e.getMessage());
		}
	}

	/**@see cscie160.project.ATM#notifyListeners(Commands, int, AccountInfo, AccountInfo) */
	public void notifyListeners(Commands command, int amount, AccountInfo accountInfoFrom, AccountInfo accountInfoTo) throws RemoteException
	{
		int accountNumberTo = 0;
		int accountNumberFrom = 0;
		
		TransactionNotification transactionNotification = null;
		
		if ((accountInfoFrom == null) && (accountInfoTo == null))
		{
			transactionNotification = new TransactionNotification("Session Finished.");
		}
		else if ((accountInfoFrom != null) && (accountInfoTo != null))
		{
			accountNumberTo = accountInfoTo.getAccountNumber();
			accountNumberFrom = accountInfoFrom.getAccountNumber();
			
			transactionNotification = new TransactionNotification(command, amount, accountNumberFrom, accountNumberTo);
		}
		else if ((accountInfoFrom != null) || (accountInfoTo != null))
		{
			if (accountInfoTo != null)
			{
				accountNumberTo = accountInfoTo.getAccountNumber();
			}
			else if (accountInfoFrom != null)
			{
				accountNumberFrom = accountInfoFrom.getAccountNumber();
			}
			
			transactionNotification = new TransactionNotification(command, amount, accountNumberFrom, accountNumberTo);
		}
		
		for (ATMListener listener : listeners)
        {
            listener.handler(transactionNotification);
        }
	}
	
	/**@see cscie160.project.ATM#endSession() */
	public void endSession() throws RemoteException
	{
		Commands command = null;
		int amount = 0;
		AccountInfo accountInfoFrom = null;
		AccountInfo accountInfoTo = null;
		
		notifyListeners(command, amount, accountInfoFrom, accountInfoTo);
  	}
}