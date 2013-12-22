package cscie160.hw2;

/**
 * <ul><li>The Exception class</li>
 * @author Namai Were Noel
 * @version Harvard CSCIE 160 - HW2
 */
public class ElevatorFullException extends Exception 
{	
	private static final long serialVersionUID = 1L;
	/**
	 * <ul><li>When it's called, prints a message that the elevator is full.</li></ul>
	 */
	public void elevatorFullException() 
	{
		System.out.println("- SORRY ELEVATOR FULL -");
	}
}
