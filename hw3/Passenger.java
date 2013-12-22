package cscie160.hw3;

/**
 * @author Namai Were Noel
 * @version Harvard CSCIE 160 - HW3
 * @param currentFloor int value of the passenger's current floor.
 * @param destinationFloor int value of the passenger's destination floor.
 */
public class Passenger
{
	private int currentFloor;
	private int destinationFloor;
	
	/**
	 * <ul><li>Passenger costructor.</li>
	 * <li>Sets up the values of currentFloor and destinationFloor</li></ul>
	 * @param currentFloor int value of the passenger's current floor.
	 * @param destinationFloor int value of the passenger's destination floor.
	 * @return An int value of the passengers waiting for up service.
	 */
	public Passenger(int currentFloor, int destinationFloor)
	{
		this.currentFloor = currentFloor;
		this.destinationFloor = destinationFloor;
	}
	
	/**
	 * <ul><li>Gets the int value of the current floor of a passenger.</li></ul>
	 * @return An int value of the current floor of a passenger.
	 */
	public int getCurrentFloor()
	{
		return currentFloor;
	}
	
	/**
	 * <ul><li>Gets the int value of the destinatio floor of a passenger.</li></ul>
	 * @return An int value of the destinatio floor of a passenger.
	 */
	public int getDestinationFloor()
	{
		return destinationFloor;
	}
	
	/**
	 * <ul><li>Updates the Passenger's state upon arrival.</li></ul>
	 * @param pass passenger who has arrived.
	 */
	public void arrive(Passenger pass)
	{
		pass.currentFloor = currentFloor;
		pass.destinationFloor = currentFloor;
	}
}