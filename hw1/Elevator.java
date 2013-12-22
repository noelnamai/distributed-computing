package cscie160.hw1;

import java.util.ArrayList;
import java.util.Collections;

public class Elevator
{
	private int currentFloor;
	private int nextFloor; 
	private int passengersOnboard;
	private int passengersDestinedToFloor;
	private String directionOfTravel;		
	final static int MAXIMUM_CAPACITY = 10;
	final static int NUMBER_OF_FLOORS = 7;	
	
	ArrayList<Integer> destinationRequests = new ArrayList<Integer>(NUMBER_OF_FLOORS);	
	
	public Elevator() 
	{
		currentFloor = 0;
		nextFloor = 0;
		passengersOnboard = 0;
		passengersDestinedToFloor = 0;
		directionOfTravel = "UP";
	}
	
	public Elevator(int currentFloor, int nextFloor, int passengersOnboard, int passengersDestinedToFloor, String directionOfTravel)
	{
		this.currentFloor = currentFloor;
		this.nextFloor = nextFloor;
		this.passengersOnboard = passengersOnboard;
		this.passengersDestinedToFloor = passengersDestinedToFloor;
		this.directionOfTravel = directionOfTravel;
	}
	
	public String toString()
	{
		return "Current Floor: " + currentFloor + ".\n"
				+ "Currently " + passengersOnboard + " passengers onboard.\n" 
				+ "Direction of Travel: " + directionOfTravel + ".\n"
				+ "Next Stop is: " + nextFloor + " floor.\n"
				+ "Number of passengers destined to " + nextFloor + " is: " + passengersDestinedToFloor + "\n\n";
	}
	
	public int getCurrentFloor()
	{
		return currentFloor;
	}
	
	public int getPassengersOnboard()
	{
		return passengersOnboard = destinationRequests.size();
	}
	
	public int getNextFloor()
	{
		if (destinationRequests.isEmpty() == true)
		{
			nextFloor = 0;
		}
		else
		{
			nextFloor = (directionOfTravel == "UP") ? Collections.min(destinationRequests) : 
			Collections.max(destinationRequests);	
		}
		return nextFloor;
	}
	
	public String getDirectionOfTravel()
	{
		return directionOfTravel = ((nextFloor - currentFloor) >= 1) ? "UP" : "DOWN";
	}
	
	public int getPassengersDestinedToFloor(int i)
	{
		return passengersDestinedToFloor = Collections.frequency(destinationRequests, i);
	}
	
	public void boardPassanger(int floor)
	{
		destinationRequests.add(floor);
		passengersOnboard ++;
		getCurrentFloor();
		getNextFloor();
		getPassengersDestinedToFloor(nextFloor);
		getDirectionOfTravel();
	}
	
	public void move()
	{
		if (destinationRequests.contains(currentFloor))
		{
			stop();
		}
				
		if (directionOfTravel == "UP")
		{
			currentFloor++;
		}		
		else 
		{
			currentFloor--;
		}
	}
	
	public void stop()
	{			
		System.out.println("* * * * * STOPPING * * * * *");
		
		while (destinationRequests.contains(currentFloor) == true) 
		{
			destinationRequests.remove(destinationRequests.indexOf(currentFloor));
		}
		
		getCurrentFloor();
		getNextFloor();
		getPassengersOnboard();
		getPassengersDestinedToFloor(nextFloor);
		getDirectionOfTravel();
		
		System.out.println(this);		
	}
	
	public static void main(String[] args)
	{	
		Elevator myElevator = new Elevator();
								
		myElevator.boardPassanger(2);
		myElevator.boardPassanger(2);
		myElevator.boardPassanger(3);
		
		System.out.println(myElevator);
		
		for (int i = 0; i < 21; i++)
		{
			myElevator.move();
		}
	}
}