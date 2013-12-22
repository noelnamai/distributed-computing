package cscie160.hw4;

/**{@link cscie160.hw4.Account} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW4.
 */
public class Account
{
	/**Float value for the Account Balance.*/
	private float accountBalance;
	
	/**Getter method for the Account class.
	 * @return Account balance.*/
	public Float getBalance()
	{
		return accountBalance;
	}
	
	/**Setter method for the Account class. Adds or subtracts amount from {@link cscie160.hw4.Account#accountBalance}.
	 * @param ops Transaction operation i.e WITHDRAW, DEPOSIT or BALANCE.
	 * @param amount Transaction amount.*/
	public void setBalance(Commands ops, Float amount)
	{
		if (ops == Commands.DEPOSIT)
		{
			accountBalance = accountBalance + amount;
		}
		
		if (ops == Commands.WITHDRAW)
		{
			accountBalance = accountBalance - amount;
		}
	}
}