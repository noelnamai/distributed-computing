package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**Implements {@link cscie160.project.Security}.
 * Provides methods for authenticating {@link cscie160.project.AccountInfo} objects and for authorizing specific operations on individual {@link cscie160.project.Account}.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class SecurityImpl extends UnicastRemoteObject implements Security
{	
	/**Default serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**Static {@link cscie160.project.SecurityImpl} object. */
	private static SecurityImpl instance = null;
	/**{@link Map} to hold {@link cscie160.project.AccountInfo} objects. */
	private Map<Integer, AccountInfo> mapAccountInfo;
	
	/**Static {@link cscie160.project.SecurityImpl#getInstance()} method.
	 * Creates a {@link cscie160.project.Security} instance if one is not yet available.
	 * @return {@link cscie160.project.SecurityImpl} instance.
	 * @throws RemoteException. */
	public static SecurityImpl getInstance() throws RemoteException
    {
		if (instance == null)
		{
			instance = new SecurityImpl();
		}
		
		return instance;
    }
	
	/**{@link cscie160.project.SecurityImpl} default constructor.
	 * Creates {@link cscie160.project.SecurityImpl#mapAccountInfo} to hold objects of type {@link cscie160.project.AccountInfo}.
	 * @throws RemoteException. */
	private SecurityImpl() throws RemoteException
	{		
		mapAccountInfo = new HashMap<Integer, AccountInfo>();
	}
	
	/**@see cscie160.project.Security#getAccountInfo(int, int) */
	public AccountInfo getAccountInfo(int accountNumber, int PIN) throws RemoteException 
	{
		if (mapAccountInfo.isEmpty() == true)
		{
			throw new ATMException("No Account Information Available");
		}
		else
		{
			AccountInfo dummyAccountInfo = mapAccountInfo.get(accountNumber);
			
			if (Integer.parseInt(dummyAccountInfo.getPIN()) == PIN)
			{
				return dummyAccountInfo;
			}
			else
			{
				throw new ATMException("Wrong PIN.");
			}
		}
	}
	
	/**@see cscie160.project.Security#setAccess(int, String, boolean, boolean, boolean) */
	public void setAccess(int accountNumber, String PIN, boolean depositAccess, boolean withdrawalAccess, boolean balanceAccess) throws RemoteException
	{
		AccountInfo accountInfo = new AccountInfo(accountNumber, PIN, depositAccess, withdrawalAccess, balanceAccess);
		
		mapAccountInfo.put(accountNumber, accountInfo);
	}
	
	/**@see cscie160.project.Security#checkPermission(int, String) */
	public boolean checkPermission(int accountNumber, String PIN) throws RemoteException
	{
		boolean access = false;
		
		AccountInfo dummyAccountInfo = mapAccountInfo.get(accountNumber);
		
		if (dummyAccountInfo.getPIN() == PIN)
		{
			access = true;
		}
		else
		{
			access = false;
			
			throw new ATMException("Wrong PIN");
		}
		
		return access;		
	}
	
	/** @see cscie160.project.Security#checkPermission(int, Commands) */
	public boolean checkPermission(int accountNumber, Commands cmd) throws RemoteException
	{
		boolean access = false;
		
		AccountInfo dummyAccountInfo = mapAccountInfo.get(accountNumber);
		
		if (cmd == Commands.DEPOSIT)
		{
			access = dummyAccountInfo.getDepositAccess();
		}		
		else if (cmd == Commands.WITHDRAW)
		{
			access = dummyAccountInfo.getWithdrawalAccess();
		}
		else if (cmd == Commands.BALANCE)
		{
			access = dummyAccountInfo.getBalanceAccess();
		}
		else
		{
			access = false;
			
			throw new ATMException("The Command is not defined.");
		}
		
		return access;
	}
}