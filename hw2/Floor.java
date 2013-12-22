package cscie160.hw2;

/**
 * <ul><li>The Floor class. Represents floors in a building</li></ul>
 * @author Namai Were Noel
 * @version Harvard CSCIE 160 - HW2
 */
public class Floor
{
	private int floorNumber;
	private int passengersWaiting;
	
	public Floor() 	
	{
		
	}
	
	/**
	 * <ul><li>Floor constructor to set up the floor state.</li>
	 * <li>The Floor registers a request when passengers are added to its waiting queue.</li></ul> 
	 * @param floorNumber The int value of the floor number in the building.
	 * @param passengersWaiting The int value of the number of passengers waiting at a floor.
	 */
	public Floor(int floorNumber, int passengersWaiting)
	{
		this.floorNumber = floorNumber;
		this.passengersWaiting = passengersWaiting;
		
		Elevator.registerRequest(floorNumber);
	}
	
	/**
	 * <ul><li>Gets the int value of the floor number.</li></ul>
	 * @return An int value of the floor number.
	 */
	public int getFloorNumber()
	{
		return floorNumber;
	}
	
	/**
	 * <ul><li>Gets the int value of the passengers waiting.</li></ul>
	 * @return An int value of the passengers waiting.
	 */
	public int getPassengersWaiting()
	{
		return passengersWaiting;
	}
	
	/**
	 * <ul><li>Unloads passengers on board when they reach their respective destinations</li>
	 * <li>Calls the boardPassenger method of the Elevator for each passenger waiting on the floor to board.</li>
	 * <li>Trys and catches ElevatorFullException exception</li>
	 * @param myElevator Elevator object.
	 */
	public void unLoadPassengers(Elevator myElevator)
	{	
		try
		{
			if (myElevator.destinationRequests.contains(myElevator.getCurrentFloor()))
			{
				while (myElevator.destinationRequests.contains(myElevator.getCurrentFloor()) == true) 
				{
					myElevator.destinationRequests.remove(myElevator.destinationRequests.indexOf(myElevator.getCurrentFloor()));
				}
			}
		
			if (Elevator.pickUpRequests.contains(myElevator.getCurrentFloor()))
			{
				while (Elevator.myFloor[myElevator.getCurrentFloor()].getPassengersWaiting() != 0)
				{
					myElevator.boardPassanger(1);
					Elevator.myFloor[myElevator.getCurrentFloor()].passengersWaiting--;
				}
				Elevator.pickUpRequests.remove(Elevator.pickUpRequests.indexOf(myElevator.getCurrentFloor()));
			}
		}
		
		catch (ElevatorFullException elf) 
		{
			elf.elevatorFullException();
		}
		
		finally
		{				
			myElevator.getPassengersOnboard();
			myElevator.getCurrentFloor();
			myElevator.getDirectionOfTravel();
		
			System.out.println(myElevator);	
		}
	}
}
