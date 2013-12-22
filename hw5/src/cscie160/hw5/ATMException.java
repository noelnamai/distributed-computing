package cscie160.hw5;

import java.rmi.RemoteException;

/**{@link cscie160.hw5.ATMException} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW5.*/
public class ATMException extends RemoteException 
{
	/**Default float value for
	 * {@link cscie160.hw5.ATMException#serialVersionUID}.*/
	private static final long serialVersionUID = 1L;

	/**Default constructor for the {@link cscie160.hw5.ATMException} class. Invokes the super class i.e. {@link RemoteException}.
	 * @param msg Exception message to be displayed.*/
	public ATMException(String msg) 
	{
		super(msg);
	}
}