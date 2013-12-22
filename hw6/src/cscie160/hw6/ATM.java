package cscie160.hw6;

/**
 * {@link cscie160.hw6.ATM} Interface.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW6. 
 */
public interface ATM 
{
	/**
	 * Method {@link cscie160.hw6.ATM#deposit(float)} facilitates deposits.
	 * @param amount Amount for the transaction.
	 * @throws ATMException ("No Deposit Made."). 
	 */
	public void deposit(float amount) throws ATMException;
	
	/**
	 * Method {@link cscie160.hw6.ATM#withdraw(float)} facilitates withdrawals.
	 * @param amount Amount for the transaction.
	 * @throws ATMException ("Insufficient Funds."). 
	 */
    public void withdraw(float amount) throws ATMException;
    
    /**
     * Method {@link cscie160.hw6.ATM#getBalance()} gets account balance.
     * @return Float value for the Account Balance.
     * @throws ATMException  
     */
    public Float getBalance() throws ATMException;
}