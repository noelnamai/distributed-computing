package cscie160.project;

import java.io.Serializable;
import java.rmi.RemoteException;

/**Implements {@link Serializable}.
 * The {@link cscie160.project.AccountInfo} object is a data class that includes an {@link cscie160.project.AccountImpl#accountNumber} and a {@link cscie160.project.AccountInfo#PIN}. 
 * Instances are passed by value between processes. 
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class AccountInfo implements Serializable
{
	/**Default serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**Integer value of the {@link cscie160.project.AccountInfo#accountNumber}. */
	private int accountNumber = 0;
	/**String value of the {@link cscie160.project.AccountInfo#PIN}. */
	private String PIN = null;
	/**Boolean value of {@link cscie160.project.AccountInfo#depositAccess}. */
	private boolean depositAccess = false;
	/**Boolean value of {@link cscie160.project.AccountInfo#withdrawalAccess}. */
	private boolean withdrawalAccess = false;
	/**Boolean value of {@link cscie160.project.AccountInfo#balanceAccess}. */
	private boolean balanceAccess = false;
	/**Boolean value of {@link cscie160.project.AccountInfo#itIs}. */
	private boolean itIs;
	
	/**{@link cscie160.project.AccountInfo} class default constructor.
	 * @param accountNumber Integer value of the {@link cscie160.project.AccountImpl#accountNumber}.
	 * @param PIN String value of the {@link cscie160.project.AccountInfo#PIN}.
	 * @param depositAccess Boolean value of {@link cscie160.project.AccountInfo#depositAccess}.
	 * @param withdrawalAccess Boolean value of {@link cscie160.project.AccountInfo#withdrawalAccess}.
	 * @param balanceAccess Boolean value of {@link cscie160.project.AccountInfo#balanceAccess}. */
	public AccountInfo(int accountNumber, String PIN, boolean depositAccess, boolean withdrawalAccess, boolean balanceAccess)
	{
		this.accountNumber = accountNumber;
		this.PIN = PIN;
		this.withdrawalAccess = withdrawalAccess;
		this.depositAccess = depositAccess;
		this.balanceAccess = balanceAccess;
	}
	
	/**{@link cscie160.project.AccountInfo#itIsTransfer(int)} method.
	 * Sets the difference between regular transactions and transfer transactions.
	 * @param i Integer value for {@link cscie160.project.AccountInfo#itIs}, 1 for true otherwise false. */
	public void itIsTransfer(int i)
	{
		itIs = (i == 1) ? true : false ;
	}
	
	/**{@link cscie160.project.AccountInfo#getIsItTransfer()} method.
	 * @return Boolean value for {@link cscie160.project.AccountInfo#itIs}.
	 * @throws RemoteException. */
	public boolean getIsItTransfer() throws RemoteException
	{
		return itIs;
	}
	
	/**{@link cscie160.project.AccountInfo#getPIN()} method.
	 * @return String value for {@link cscie160.project.AccountInfo#PIN}.
	 * @throws RemoteException. */
	public String getPIN() throws RemoteException
	{
		return PIN;
	}
	
	/**{@link cscie160.project.AccountInfo#getAccountNumber()} method.
	 * @return String value for {@link cscie160.project.AccountInfo#accountNumber}.
	 * @throws RemoteException. */
	public int getAccountNumber() throws RemoteException
	{
		return accountNumber;
	}
	
	/**{@link cscie160.project.AccountInfo#getDepositAccess()} method.
	 * @return Boolean value for {@link cscie160.project.AccountInfo#depositAccess}.
	 * @throws RemoteException. */
	public boolean getDepositAccess() throws RemoteException
	{
		return depositAccess;
	}
	
	/**{@link cscie160.project.AccountInfo#getWithdrawalAccess()} method.
	 * @return Boolean value for {@link cscie160.project.AccountInfo#withdrawalAccess}.
	 * @throws RemoteException. */
	public boolean getWithdrawalAccess() throws RemoteException
	{
		return withdrawalAccess;
	}
	
	/**{@link cscie160.project.AccountInfo#getBalanceAccess()} method.
	 * @return Boolean value for {@link cscie160.project.AccountInfo#balanceAccess}.
	 * @throws RemoteException. */
	public boolean getBalanceAccess() throws RemoteException
	{
		return balanceAccess;
	}
}