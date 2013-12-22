package cscie160.project;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**Extends {@link UnicastRemoteObject}, implements {@link cscie160.project.Bank}.
 * Implements the remote {@link cscie160.project.Bank} interface. 
 * Provides methods to obtain reference to {@link cscie160.project.Account}.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class BankImpl extends UnicastRemoteObject implements Bank
{
	/**Default serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**Static {@link cscie160.project.BankImpl} instance. */
	private static BankImpl instance = null;
	/**{@link Map} to hold {@link cscie160.project.Account} objects. */
	private Map<Integer, Account> accountsHashMap;	
	/**{@link Array} of the predetermined {@link cscie160.project.Account} balances.*/
	private int[] balances = {0, 0, 100, 500};
	/**{@link cscie160.project.SecurityImpl} object. */
	private SecurityImpl security;	
	
	/**Static {@link cscie160.project.BankImpl#getInstance()} method.
	 * Creates a {@link cscie160.project.BankImpl} instance if one is not yet available.
	 * @return {@link cscie160.project.BankImpl} instance.
	 * @throws RemoteException. */
	public static BankImpl getInstance() throws RemoteException
	{
		if (instance == null)
		{
			instance = new BankImpl();
		}
		else
		{
			throw new ATMException("Could not load the Bank");
		}
		
		return instance;
	}
	
	/**{@link cscie160.project.BankImpl} constructor.
	 * Calls the super() method.
	 * Creates {@link cscie160.project.Account} objects and puts them in the {@link cscie160.project.BankImpl#accountsHashMap}.
	 * Gets an instance of the {@link cscie160.project.Security} object.
	 * Sets predetermined security access values.
	 * @throws RemoteException. */
	public BankImpl() throws RemoteException
	{	
		super();
		
		//Hashmap to hold Account objects.
		accountsHashMap = new HashMap<Integer, Account>();
		
		for (int i = 1; i <= 3; i++) 
		{
			Account myAccount = new AccountImpl(i, balances[i]);
				
			accountsHashMap.put(i, myAccount);
		}
		
		security = SecurityImpl.getInstance();
		
		security.setAccess(1, "1234", true, true, true);
		security.setAccess(2, "2345", true, false, true);
		security.setAccess(3, "3456", false, true, true);
	}

	/**@see cscie160.project.Bank#getAccount(int) */
	public Account getAccount(int accountNumber) throws RemoteException
	{
		return accountsHashMap.get(accountNumber);
	}

	/**@see cscie160.project.Bank#getSecurity() */
	public SecurityImpl getSecurity() throws RemoteException
	{
		return SecurityImpl.getInstance();
	}
}