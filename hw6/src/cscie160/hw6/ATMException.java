package cscie160.hw6;

/**
 * {@link cscie160.hw6.ATMException} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW6. 
 */
public class ATMException extends Exception 
{
    /**Default float value for serialVersionUID*/
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for {@link cscie160.hw6.ATMException}.
	 * @param msg Exception message to be displayed. 
	 */
	public ATMException(String msg) 
	{
		super(msg);
    }
}