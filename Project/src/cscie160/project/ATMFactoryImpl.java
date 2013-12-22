package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**Extends {@link UnicastRemoteObject}, implements {@link cscie160.project.ATMFactory}.
 * The {@link cscie160.project.ATMFactoryImpl} is an object that is responsible for providing reference to a remote {@link cscie160.project.ATM} instance.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT. */
public class ATMFactoryImpl extends UnicastRemoteObject implements ATMFactory 
{
	/**Default serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**{@link cscie160.project.ATMFactoryImpl} default constructor.
	 * Calls the super class.
	 * @throws RemoteException. */
	protected ATMFactoryImpl() throws RemoteException 
	{
		super();
	}

	/**{@link cscie160.project.ATMFactoryImpl#getATM()} method.
	 * Creates a new {@link cscie160.project.ATMImpl}.
	 * @return A new {@link cscie160.project.ATMImpl} object. */
	public ATM getATM() throws RemoteException 
	{
		return new ATMImpl();
	}
}