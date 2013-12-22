package cscie160.hw4;

/**{@link cscie160.hw4.ATMException} class.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW4. */
public class ATMException extends Exception 
{
    /**Default float value for serialVersionUID*/
	private static final long serialVersionUID = 1L;

	/**Default constructor for ATMException class.
	 * @param msg Exception message to be displayed. */
	public ATMException(String msg) 
	{
		super(msg);
    }
}