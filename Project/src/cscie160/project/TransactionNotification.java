package cscie160.project;

import java.io.Serializable;
import java.rmi.RemoteException;

/**Implements {@link Serializable}.
 * A data class that includes pertinent information about a particular transaction including target accounts and amounts. 
 * It has a toString() method that returns a String describing the transaction that can be easily printed by any listener. 
 * Instances are passed by value between processes.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class TransactionNotification implements Serializable
{
	/**Default serialVersionUID. */
	private static final long serialVersionUID = 1L;	
	/**{@link cscie160.project.Commands} value of the transaction. */
	private Commands command;
	/**{@link cscie160.project.TransactionNotification#amount} of the transaction. */
	private int amount = 0;
	/**Integer value of the {@link cscie160.project.AccountImpl#accountNumber} of the requesting {@link cscie160.project.Account}. */
	private Integer accountNumberFrom = 0;
	/**Integer value of the {@link cscie160.project.AccountImpl#accountNumber} for the target {@link cscie160.project.Account}. */
	private Integer accountNumberTo = 0;
	/**{@link cscie160.project.TransactionNotification#message} to be sent about the transaction. */
	private String message;
	
	/**{@link cscie160.project.TransactionNotification} default constructor.
	 * @param message {@link cscie160.project.TransactionNotification#message} to be sent about the transaction.*/
	public TransactionNotification(String message)
	{
    	this.message = message;
	}
	
	/**{@link cscie160.project.TransactionNotification} default constructor.
	 * @param command {@link cscie160.project.Commands} value of the transaction.
	 * @param amount Amount of the transaction.
	 * @param accountNumberFrom Integer value of the {@link cscie160.project.AccountImpl#accountNumber} of the requesting {@link cscie160.project.Account}.
	 * @param accountNumberTo Integer value of the {@link cscie160.project.AccountImpl#accountNumber} for the target {@link cscie160.project.Account}.
	 * @throws RemoteException. */
	public TransactionNotification(Commands command, int amount, int accountNumberFrom, int accountNumberTo) throws RemoteException
    {
		this.accountNumberFrom = (accountNumberFrom != 0) ? accountNumberFrom : 0;
        this.accountNumberTo = (accountNumberTo != 0) ? accountNumberTo : 0;
        this.amount = amount;
        this.command = command;
    }

	/**{link cscie160.project.TransactionNotification#getMessage()} method.
	 * Getter method for {@link cscie160.project.TransactionNotification#message}.
	 * @throws RemoteException. */
	public void getMessage() throws RemoteException
    {      	
    	if (command == Commands.BALANCE)
    	{
    		System.out.println("GETTING " + command.toString() + " OF " + accountNumberFrom);
    	}
    	else if (command == Commands.DEPOSIT) 
    	{
    		System.out.println(command.toString() + "ING " + amount + " TO " + accountNumberTo);
    	}
    	else if (command == Commands.WITHDRAW)
    	{
    		System.out.println(command.toString() + "ING " + amount + " FROM " + accountNumberFrom);
    	}
    	else if (command == Commands.TRANSFER)
    	{
    		System.out.println(command.toString() + "ING " + amount + " FROM " + accountNumberFrom + " to " + accountNumberTo);
    	}
    	else
    	{
    		System.out.println(this.message);
    	}
    }
}