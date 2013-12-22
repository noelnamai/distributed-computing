package cscie160.hw2;

import java.util.ArrayList;
import java.util.List;

/**
 * <ul><li>Represents a moving and stopping elevator.</li></ul>
 * @author Namai Were Noel
 * @version Harvard CSCIE 160 - HW2
 */
public class Elevator
{
	private int currentFloor;
	private int passengersOnboard;
	private directions directionOfTravel;	
	private enum directions {UP, DOWN, STOPPED};
	final static int MAXIMUM_CAPACITY = 10;
	final static int NUMBER_OF_FLOORS = 7;	
	static Floor[] myFloor = new Floor[NUMBER_OF_FLOORS + 1];
	static List<Integer> pickUpRequests = new ArrayList<Integer>(NUMBER_OF_FLOORS);
	List<Integer> destinationRequests = new ArrayList<Integer>(NUMBER_OF_FLOORS);	
				
	/**
	 * <ul><li>No-argument Elevator constructor.</li></ul>
	 */
	public Elevator() 
	{
		currentFloor = 1;
		passengersOnboard = 0;
		directionOfTravel = directions.UP;
	}
	
	/**
	 * <ul><li>Elevator constructor to set up the Elevator state.</li></ul>
	 * @param currentFloor An int value of the current floor.
	 * @param passengersOnboard An int value of the passengers onboard.
	 * @param directionOfTravel A value of type directions representing the direction of travel.
	 */
	public Elevator(int currentFloor, int passengersOnboard, directions directionOfTravel)
	{
		this.currentFloor = currentFloor;
		this.passengersOnboard = passengersOnboard;
		this.directionOfTravel = directionOfTravel;
	}
	
  	/**
 	 * <ul><li>Exposes the hard-coded, specific view of the internal state of your object instance. which is useful in 	debuging.</li></ul>
 	 * @return Returns a String summarizing all pertinent values in the elevator.
 	 */
	public String toString()
	{
		return "Stopping on: " + currentFloor + "\n"
				+ "Currently " + passengersOnboard + " passengers onboard\n" 
				+ "Direction of Travel: " + directionOfTravel + "\n"
				+ "Current floor " + currentFloor + "\n";
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
	 * <ul><li>Gets the int value of the passengers onboard.</li></ul>
	 * @return An int value of the passengers onboard.
	 */
	public int getPassengersOnboard()
	{
		return passengersOnboard = destinationRequests.size();
	}
	
 	/**
 	 * <ul><li>Gets the value of the direction of travel of the elevator.</li>
 	 * <li>We asume that elevator sweeps all the floors before changing direction i.e. till the last floor</li></ul>
 	 * @return A value of type directions representing the direction of travel.
 	 */
	public directions getDirectionOfTravel()
	{
		if ((getPassengersOnboard() == 0) && (pickUpRequests.size() == 0))
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
	 * <li>If the elevator is full, it throws an excemption</li></ul>
	 * @throws ElevatorFullException
	 */
	public void boardPassanger(int floor) throws ElevatorFullException
	{
		if (passengersOnboard == MAXIMUM_CAPACITY)
		{
			throw new ElevatorFullException();
		}
		
		destinationRequests.add(floor);
		
		getPassengersOnboard();
		getCurrentFloor();
		getDirectionOfTravel();
	}
	
 	/**
 	 * <ul><li>Increments or decrements the values of the current floor.</li>
 	 * <li>Calls stop() method if there are passengers onboard or passengers waiting to board or the last floor.</li>
 	 * <li>Uses an if-else statement to check the value of the directionOfTravel.</li></ul>
 	 */
	public void move()
	{
		if (destinationRequests.contains(currentFloor) || pickUpRequests.contains(currentFloor) || currentFloor == NUMBER_OF_FLOORS)
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
	public static void registerRequest(int floor)
	{
		pickUpRequests.add(floor);
	}
	
	/**
	 * <ul><li>Creats the Elevator object.</li>
	 * <li>Creats Floor objects.</li>
	 * <li>Adds passengersWaiting to respective floors i.e. myElevator.myFloor[floorNumber] = new Floor(floorNumber, passengersWaiting)</li>
	 * <li>Boards respective passengers onboard i.e. myElevator.boardPassanger(floorNumber).</li>
	 * <li>Calls the move() method if there are passengers onboard or passengers waiting to board the elevator.</li></ul>
	 * @param args Doesnt take any command line arguments.
	 * @throws ElevatorFullException
	 */
	public static void main(String[] args) throws ElevatorFullException
	{	
		Elevator myElevator = new Elevator();
		
		for (int i = 0; i < myFloor.length; i++)
		{
			myFloor[i] = new Floor();
		}		
			
		Elevator.myFloor[2] = new Floor(2,8);
		Elevator.myFloor[4] = new Floor(4,9);;
		Elevator.myFloor[6] = new Floor(6,4);

														
		myElevator.boardPassanger(2);
		myElevator.boardPassanger(3);
		myElevator.boardPassanger(7);
		
		System.out.println(myElevator);
		
		while ((myElevator.destinationRequests.isEmpty() == false) || (Elevator.pickUpRequests.isEmpty() == false))
		{
			myElevator.move();
		}
	}
}