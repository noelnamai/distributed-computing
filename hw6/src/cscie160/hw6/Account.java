package cscie160.hw6;

/**
 * {@link cscie160.hw6.Account} class.
 * The class object is synchronized to avoid thread interference and memory consistency errors.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW6. 
 */
public class Account
{
	/**Float value for the Account Balance. */
	private float accountBalance;
	
	/**
	 * Getter method for the Account class.
	 * The class object is synchronized to avoid thread interference and memory consistency errors.
	 * @return Account balance.
	 */
	public Float getBalance()
	{
		synchronized (this) 
		{
			return accountBalance;
		}
	}
	
	/**
	 * Setter method for the Account class. Adds or subtracts amount from {@link cscie160.hw6.Account#accountBalance}.
	 * The class object is synchronized to avoid thread interference and memory consistency errors.
	 * @param ops Transaction operation i.e WITHDRAW, DEPOSIT or BALANCE.
	 * @param amount Transaction amount. 
	 */
	public void setBalance(Commands ops, Float amount)
	{
		synchronized (this) 
		{		
			if (ops == Commands.DEPOSIT)
			{
				accountBalance = getBalance() + amount;
			}
				
			if (ops == Commands.WITHDRAW)
			{
				accountBalance = getBalance() - amount;
			}
		}
    }
}