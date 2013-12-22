package cscie160.hw6;

/**
 * {@link cscie160.hw6.ATMImplementation} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW6. 
 */
public class ATMImplementation implements ATM
{
	/**{@link cscie160.hw6.Account} instance used in the {@link cscie160.hw6.ATMImplementation} class. */
	private Account myAccount;
	
	/**Default constructor for {@link cscie160.hw6.ATMImplementation}. */
	public ATMImplementation()
	{			
		 myAccount = new Account();
	}
	
	/**@see cscie160.hw6.ATM#getBalance() */
	public Float getBalance() throws ATMException
	{
		return myAccount.getBalance();
	}

	/**@see cscie160.hw6.ATM#deposit(float) */
	public void deposit(float amount) throws ATMException 
	{
		if (amount <= 0)
		{
			throw new ATMException("No Deposit Made.");  
		}
		else
		{
			myAccount.setBalance(Commands.DEPOSIT, amount);            
		}
	}

	/**@see cscie160.hw6.ATM#withdraw(float) */
	public void withdraw(float amount) throws ATMException 
	{
		if (amount > myAccount.getBalance())
		{
			throw new ATMException("Insufficient Funds.");  
		}
		else
		{
			myAccount.setBalance(Commands.WITHDRAW, amount);             
		}
	}
}