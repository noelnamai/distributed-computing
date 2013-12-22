package cscie160.project;

import java.rmi.RemoteException;

/**Extends {@link RemoteException}.
 * Calls the super class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class ATMException extends RemoteException 
{
	/**Default serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**{@link cscie160.project.ATMException} default constructor.
	 * @param msg The exception message.	 */
	public ATMException(String msg) 
	{
		super(msg);
	}
}