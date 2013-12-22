package cscie160.hw3;

/**
 * @author Namai Were Noel
 * @version Harvard CSCIE 160 - HW3
 * <ul><li>Driver class for HW3.</li></ul>
 */
public class Driver
{
	/**
	 * <ul><li>Creats the Elevator object.</li>
	 * <li>Creats Floor objects.</li>
	 * <li>Creats Passenger objects.</li>
	 * <li>Sets up passengers on random floors.</li>
	 * <li>Calls the move() method if there are passengers onboard or passengers waiting to board the elevator.</li></ul>
	 * @param args Doesnt take any command line arguments.
	 */
	public static void main(String[] args)
	{			
		Elevator myElevator = new Elevator();	
		
		/*Creats using a for-loop Floor objects and puts them in the myFloor[] array.*/
		for (int i = 0; i < myElevator.myFloor.length; i++)
		{
			myElevator.myFloor[i] = new Floor(i);
		}	
		
		/*Creates 25 Passenger objects i.e. Passenger(currentFloor, destinationFloor). */
		Passenger pass1 = new Passenger(1, 5);		
		Passenger pass2 = new Passenger(1, 6);
		Passenger pass3 = new Passenger(1, 6);
		Passenger pass4 = new Passenger(1, 7);
		Passenger pass5 = new Passenger(1, 7);
		Passenger pass6 = new Passenger(1, 7);
		Passenger pass7 = new Passenger(1, 7);
		Passenger pass8 = new Passenger(1, 7);
		Passenger pass9 = new Passenger(1, 7);
		Passenger pass10 = new Passenger(3, 7);
		Passenger pass11 = new Passenger(3, 7);
		Passenger pass12 = new Passenger(3, 7);
		Passenger pass13 = new Passenger(3, 6);
		Passenger pass14 = new Passenger(3, 7);
		Passenger pass15 = new Passenger(3, 6);
		Passenger pass16 = new Passenger(4, 2);
		Passenger pass17 = new Passenger(4, 1);
		Passenger pass18 = new Passenger(6, 7);
		Passenger pass19 = new Passenger(6, 7);
		Passenger pass20 = new Passenger(6, 7);
		Passenger pass21 = new Passenger(6, 1);
		Passenger pass22 = new Passenger(6, 2);
		Passenger pass23 = new Passenger(6, 3);
		Passenger pass24 = new Passenger(6, 4);
		Passenger pass25 = new Passenger(6, 7);

		/*Sets up passengers on random floors.</li></ul>*/
		myElevator.myFloor[1].setFloorPassengers(pass1);
		myElevator.myFloor[1].setFloorPassengers(pass2);
		myElevator.myFloor[1].setFloorPassengers(pass3);
		myElevator.myFloor[1].setFloorPassengers(pass4);
		myElevator.myFloor[1].setFloorPassengers(pass5);
		myElevator.myFloor[1].setFloorPassengers(pass6);
		myElevator.myFloor[1].setFloorPassengers(pass7);
		myElevator.myFloor[1].setFloorPassengers(pass8);
		myElevator.myFloor[1].setFloorPassengers(pass9);
		myElevator.myFloor[3].setFloorPassengers(pass10);
		myElevator.myFloor[3].setFloorPassengers(pass11);
		myElevator.myFloor[3].setFloorPassengers(pass12);
		myElevator.myFloor[3].setFloorPassengers(pass13);
		myElevator.myFloor[3].setFloorPassengers(pass14);
		myElevator.myFloor[3].setFloorPassengers(pass15);
		myElevator.myFloor[4].setFloorPassengers(pass16);
		myElevator.myFloor[4].setFloorPassengers(pass17);
		myElevator.myFloor[6].setFloorPassengers(pass18);
		myElevator.myFloor[6].setFloorPassengers(pass19);
		myElevator.myFloor[6].setFloorPassengers(pass20);
		myElevator.myFloor[6].setFloorPassengers(pass21);
		myElevator.myFloor[6].setFloorPassengers(pass22);
		myElevator.myFloor[6].setFloorPassengers(pass23);
		myElevator.myFloor[6].setFloorPassengers(pass24);
		myElevator.myFloor[6].setFloorPassengers(pass25);
		
		/*Calls the move() method if there are passengers on board or there exists pickup requests.*/
		while ((myElevator.passengersBoardedList.size() != 0) || (Elevator.pickUpRequestsList.size() != 0))
		{
			myElevator.move();
		}
	}
}