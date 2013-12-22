package cscie160.hw5;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**{@link cscie160.hw5.ATM} Interface.
 * @author Namai Noel
 * @version Harvard CSCIE160: Java for Distributed Computing - HW5.*/
public interface ATM extends Remote 
{
	/**Method {@link cscie160.hw5.ATM#deposit(int, float)} facilitates deposits.
	 * @param accountNumber Account number for the transaction.
	 * @param amount Amount for the transaction.
	 * @throws RemoteException "No Deposit Made."*/
	public void deposit(int accountNumber, float amount) throws RemoteException;

	/**Method {@link cscie160.hw5.ATM#withdraw(int, float)} facilitates withdrawals.
	 * @param accountNumber Account number for the transaction.
	 * @param amount Amount for the transaction.
	 * @throws RemoteException "Insufficient Funds."*/
	public void withdraw(int accountNumber, float amount) throws RemoteException;

	/**Method {@link cscie160.hw5.ATM#getBalance(int)} gets account balance.
	 * @param accountNumber Account number for the transaction.
	 * @return Float value for the Account Balance.
	 * @throws RemoteException*/
	public Float getBalance(int accountNumber) throws RemoteException;
}