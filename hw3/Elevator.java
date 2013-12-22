package cscie160.hw3;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Namai Were Noel
 * @version Harvard CSCIE 160 - HW3
 * @param currentFloor int value of the current floor.
 * @param directionOfTravel represents the dirrection of travel of type directions.
 * @param MAXIMUM_CAPACITY int constant value of the elevator capacity.
 * @param NUMBER_OF_FLOORS int constant value of the number of floors in the building.
 * @param pickUpRequestsList Set of integer values of pickup request. This avoids duplicate requests to the same floor.
 * @param myFloor Array of Floor objects.
 * @param destinationRequestsList List of integer value of destination requests. Sorting is important for the elevator motion and stops.
 * @param passengersBoardedList List of integer value of passengers on board. It's of type CopyOnWriteArrayList to allow looping and editing the List.
 * <ul><li>Represents a moving and stopping elevator.</li></ul>
 */
public class Elevator
{
	private int currentFloor;
	private directions directionOfTravel;	
	enum directions {UP, DOWN, STOPPED};
	final static int MAXIMUM_CAPACITY = 10;
	final static int NUMBER_OF_FLOORS = 7;	
	static Set<Integer> pickUpRequestsList = new HashSet<Integer>();
	Floor[] myFloor = new Floor[Elevator.NUMBER_OF_FLOORS + 1];	
	List<Integer> destinationRequestsList = new ArrayList<Integer>();	
	List<Passenger> passengersBoardedList = new CopyOnWriteArrayList<Passenger>();	

	/**
	 * <ul><li>No-argument Elevator constructor.</li></ul>
	 */
	public Elevator() 
	{
		currentFloor = 0;
		directionOfTravel = directions.UP;
	}

	/**
 	 * <ul><li>Exposes the hard-coded, specific view of the internal state of your object instance. which is useful in 	debuging.</li></ul>
 	 * @return Returns a String summarizing all pertinent values in the elevator.
 	 */
	public String toString()
	{
		return "Stopping on: " + getCurrentFloor() + "\n"	
				+ "Direction of Travel: " + getDirectionOfTravel() + "\n"
				+ "Currently " + passengersBoardedList.size() + " passengers onboard.\n" 
				+ "Floor Tenants " + myFloor[getCurrentFloor()].getPassengersHanging() + ", "
				+ "Waiting for Up " + myFloor[getCurrentFloor()].getPassengersWaitingForUp() + ", "
				+ "Waiting for Down " + myFloor[getCurrentFloor()].getPassengersWaitingForDown() + "\n"
				+ "Destinations of the Passengers Onboard " + getInfoOfPassengersOnboard() + ".\n";
		}

	/**
	 * <ul><li>Gets the int value of the current floor.</li></ul>
	 * @return An int value of the current floor.
	 */
	public int getCurrentFloor()
	{
		return currentFloor;
	}
	
	/**
	 * <ul><li>Gets the destination floors of all the passengers onboard.</li></ul>
	 * @return An String array of the destination floors of all the passengers onboard.
	 */
	public String getInfoOfPassengersOnboard()
	{
		destinationRequestsList.clear();
		
		Iterator<Passenger> itr = passengersBoardedList.iterator(); 
		
		while(itr.hasNext()) 
		{
			Passenger pass = itr.next(); 
			destinationRequestsList.add(pass.getDestinationFloor());
		} 
		
		Collections.sort(destinationRequestsList);
		
		return destinationRequestsList.toString();
	}

	/**
 	 * <ul><li>Gets the value of the direction of travel of the elevator.</li></ul>
 	 * @return A value of type directions representing the direction of travel.
 	 */
	public directions getDirectionOfTravel()
	{
		if ((passengersBoardedList.size() == 0) && (pickUpRequestsList.size() == 0))
		{
			directionOfTravel = directions.STOPPED;
		}
		else
		{
			if (currentFloor == 1)
			{
				directionOfTravel = directions.UP;
			}
			else if (currentFloor == NUMBER_OF_FLOORS)
			{
				directionOfTravel = directions.DOWN;
			}
		}
		
		return directionOfTravel;
	}
	
	/**
	 * <ul><li>Boards a passenger destined for a particular floor.</li>
	 * <li>If the elevator is full, it throws an excemption, else adds the passenger to passengersBoardedList</li></ul>
	 * @throws ElevatorFullException(String)
	 */
	public void boardPassanger(Passenger passenger) throws ElevatorFullException
	{
		if (passengersBoardedList.size() == MAXIMUM_CAPACITY)
		{
			throw new ElevatorFullException();
		}		
		passengersBoardedList.add(passenger);
	}

	/**
	 * <ul><li>Increments or decrements the values of the current floor.</li>
	 * <li>Calls stop() method if there are passengers onboard or passengers waiting to board.</li>
	 * <li>Uses an if-else statement to check the value of the directionOfTravel.</li></ul>
	 */
	public void move()
	{	
		if (destinationRequestsList.contains(getCurrentFloor()) || pickUpRequestsList.contains(getCurrentFloor()))
		{
			stop();
		}
				
		if (directionOfTravel == directions.UP)
		{
			currentFloor++;
		}		
		else 
		{
			currentFloor--;
		}
	}

	/**
	 * <ul><il>stop() passes control to unLoadPassengers(Elevator e)</il></ul>
	 */
	public void stop()
	{				
		myFloor[currentFloor].unLoadPassengers(this);	
	}
	
	/**
	 * <ul><il>Method for Floor to register a request when passengers are added to its waiting queue.</li>
	 * <li>Adds the floor to the pickUpRequests arraylist</li>
	 * <li>It's made static. Assuming that there are more than elevators in the building</li></ul>
	 * @param floor The floor of which a passenger is waiting for the elevator.
	 */
	public static void registerPickUpRequest(int floor)
	{
		pickUpRequestsList.add(floor);
	}
}