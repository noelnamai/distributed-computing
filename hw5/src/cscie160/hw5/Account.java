package cscie160.hw5;

/**{@link cscie160.hw5.Account} Class. 
 * @author Namai Noel
 * @version Harvard CSCIE160: Java for Distributed Computing - HW5.
 */
public class Account 
{
	/** Int value of the {@link cscie160.hw5.Account}} number. */
	private int accountNumber;

	/** Float value of the {@link cscie160.hw5.Account} balance. */
	private float accountBalance;

	/**{@link cscie160.hw5.Account#getAccountNumber()} method to get account number. 
	 * @return Int value of the {@link cscie160.hw5.Account#accountNumber}.*/
	public int getAccountNumber() 
	{
		return accountNumber;
	}

	/**{@link cscie160.hw5.Account#getBalance()} method to get {@link cscie160.hw5.Account#accountBalance}.
	 * @return Float value of the {@link cscie160.hw5.Account#accountBalance}.*/
	public Float getBalance() 
	{
		return accountBalance;
	}

	/**{@link cscie160.hw5.Account#setAccountNumber(int)} method to set {@link cscie160.hw5.Account#accountNumber}.
	 * @param accountNumber Int value of the {@link cscie160.hw5.Account#accountNumber}.*/
	public void setAccountNumber(int accountNumber) 
	{
		this.accountNumber = accountNumber;
	}

	/**{@link cscie160.hw5.Account#setBalance(Commands, Float)} method to set {@link cscie160.hw5.Account#accountBalance}. Adds or subtracts the amount in {@link cscie160.hw5.Commands#DEPOSIT} or {@link cscie160.hw5.Commands#WITHDRAW} respectively.
	 * @param ops The operation requested i.e {@link cscie160.hw5.Commands#DEPOSIT} or {@link cscie160.hw5.Commands#WITHDRAW}.
	 * @param amount The amount for the operation requested.*/
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