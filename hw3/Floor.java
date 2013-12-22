package cscie160.hw3;

import java.util.*;
import cscie160.hw3.Elevator.directions;

/**
 * @author Namai Were Noel
 * @version Harvard CSCIE 160 - HW3
 * @param floorNumber int value of the current floor.
 * @param floorTenantList List of passengers who are hanging around on a particular floor.
 * @param queuedUpList List of passengers who are waiting for up service on a particular floor.
 * @param queuedDownList List of passengers who are waiting for down service on a particular floor.
 */
public class Floor
{
	private int floorNumber;
	
	List<Passenger> floorTenantList = new ArrayList<Passenger>();
	List<Passenger> queuedUpList = new ArrayList<Passenger>();
	List<Passenger> queuedDownList = new ArrayList<Passenger>();
	
	/*
	 * <ul><li>Floor constructor. Stores areference to floorNumber.</li>
	 * <li>Adds 1 to avoid having a floor 0.</li></ul>
	 */
	public Floor(int floorNumber)
	{
		this.floorNumber = floorNumber + 1;
	}
	
	public void setFloorPassengers(Passenger passenger) 
	{
		if (passenger.getDestinationFloor() > passenger.getCurrentFloor())
		{
			queuedUpList.add(passenger);
			floorTenantList.add(passenger);
			Elevator.registerPickUpRequest(passenger.getCurrentFloor());
		}
		else if (passenger.getDestinationFloor() < passenger.getCurrentFloor())
		{
			queuedDownList.add(passenger);
			floorTenantList.add(passenger);
			Elevator.registerPickUpRequest(passenger.getCurrentFloor());
		}
		else if (passenger.getDestinationFloor() == passenger.getCurrentFloor())
		{
			floorTenantList.add(passenger);
		}
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
	 * <ul><li>Gets the int value of the passengers waiting for up service.</li></ul>
	 * @return An int value of the passengers waiting for up service.
	 */
	public int getPassengersWaitingForUp()
	{
		return queuedUpList.size();
	}
	
	/**
	 * <ul><li>Gets the int value of the passengers waiting for down service.</li></ul>
	 * @return An int value of the passengers waiting for down service.
	 */
	public int getPassengersWaitingForDown()
	{
		return queuedDownList.size();
	}
	
	/**
	 * <ul><li>Gets the int value of the passengers hanging around.</li></ul>
	 * @return An int value of the passengers hanging around.
	 */
	public int getPassengersHanging()
	{
		return floorTenantList.size();
	}

	/**
	 * <ul><li>Unloads passengers on board when they reach their respective destinations</li>
	 * <li>Calls the boardPassenger method of the Elevator for each passenger waiting on the floor to board.</li>
	 * <li>Makes nessesary updates to the various queued passengers for up or down or floor passengers.</li>
	 * <li>Trys and catches ElevatorFullException exception</li></ul>
	 * @param myElevator Elevator object.
	 */
	public void unLoadPassengers(Elevator elevator)
	{
		try
		{
			for (Passenger pass : elevator.passengersBoardedList) 
			{
				if (pass.getDestinationFloor() == elevator.getCurrentFloor())
				{
					pass.arrive(pass);
					elevator.destinationRequestsList.remove(elevator.destinationRequestsList.indexOf(elevator.getCurrentFloor()));
					this.floorTenantList.add(pass);
					elevator.passengersBoardedList.remove(pass);
				}
			}
			
			if (elevator.getDirectionOfTravel() == directions.UP)
			{
				while (this.getPassengersWaitingForUp() != 0)
				{ 
					Passenger pass = this.queuedUpList.get(0);
				
					elevator.boardPassanger(pass);
					this.floorTenantList.remove(pass);
					this.queuedUpList.remove(pass);
					Elevator.pickUpRequestsList.remove(elevator.getCurrentFloor());
				}
			}
			
			if (elevator.getDirectionOfTravel() == directions.DOWN)
			{
				while (this.getPassengersWaitingForDown() != 0)
				{
					Passenger pass = this.queuedDownList.get(0);
					
					elevator.boardPassanger(pass);
					this.floorTenantList.remove(pass);
					this.queuedDownList.remove(pass);
					Elevator.pickUpRequestsList.remove(elevator.getCurrentFloor());
				}
			}
		}
		
		catch (ElevatorFullException elf) 
		{
			System.out.println("SORRY ELEVATOR FULL!");

			/**
			 * <ul><li>Re-registers the floor request if the elevator is full.</li></ul>
			 */
			if (this.queuedUpList.size() != 0 || this.queuedDownList.size() != 0)
			{
				Elevator.pickUpRequestsList.add(elevator.getCurrentFloor());
			}
		}
		
		finally
		{	
			System.out.println(elevator);	
		}
	}
}