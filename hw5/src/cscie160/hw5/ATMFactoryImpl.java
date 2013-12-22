package cscie160.hw5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**{@link cscie160.hw5.ATMFactoryImpl} class.
 * @author Namai Noel
 * @version Harvard CSCIE160: Java for Distributed Computing - HW5.*/
public class ATMFactoryImpl extends UnicastRemoteObject implements ATMFactory 
{
	/**Default float value for
	 * {@link cscie160.hw5.ATMException#serialVersionUID}.*/
	private static final long serialVersionUID = 1L;

	/**Default constructor of {@link cscie160.hw5.ATMFactoryImpl} class.
	 * @throws RemoteException*/
	protected ATMFactoryImpl() throws RemoteException 
	{
		super();
	}

	/** @see cscie160.hw5.ATMFactory#getATM().*/
	@Override
	public ATM getATM() throws RemoteException 
	{
		return new ATMImpl();
	}
}