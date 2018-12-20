# Floors, Exceptions

## Objective: 

Add floors to the elevator system and begin using exceptions to handle error conditions.

## Resources:

* Exceptions
* Unit Testing with JUnit href="http://java.sun.com/docs/books/tutorial/java/javaOO/classes.html">Classes
* Java tutorial

---

* Migrate your classes into the package cscie55.hw2.
* Define and implement a class Floor to represent a floor that the Elevator will visit. When the Elevator is initialized it should create an array of the Floors in the building. (Again, you may assume 7 floors in the building)
* Endow each Floor with a data member indicating how many passengers are waiting on the Floor to board the Elevator. For now, this can simply be a numeric count of the number of passengers queued up.
* Elevator.stop must now become much smarter. When it has finished executing its passengers destined for the current floor must be unloaded, and any passengers waiting on the floor must be loaded on the elevator, space permitting. It will do this by passing control to Floor.
* Implement a method Floor.unloadPassengers that unloads the elevator's passengers who were destined for the Floor, then loads its waiting passengers onto the elevator. For now, unloadPassengers will just take an argument of type Elevator. [In homework 3, you will change the signature to take a collection of actual Passenger objects.] To be more specific, when a Floor object has its unloadPassengers method called, it will access the Elevator, "pull" off the passengers, then call the boardPassenger method of the 
* Elevator for each passenger waiting on the floor to board. When the job is done, the state of both Floor and Elevator objects should reflect the appropriate changes.
* Add some sophistication to the Elevator.boardPassenger(int floor). If the Elevator is at capacity when boardPassenger is called, the method should throw an ElevatorFullException. When the exception is thrown Floor.unloadPassengers should handle it by correctly holding any additional passengers until the the Elevator returns.
* Add a registerRequest method to the Elevator and have the Floor register a request when passengers are added to its waiting queue. Make sure your Elevator's move method only stops on those Floors that are destinations of passengers on board the Elevator or at Floors that have one or more passengers who wish to board the Elevator.
* Only the Floor has enough information to determine if all the passengers are successfully loaded onto the Elevator. If not (due to overflow) passengers are still waiting for an elevator to carry them. The Floor should be responsible for re-registering a destination request with the Elevator.
* You can assume that every passengers that is waiting at a floor wants to go down to the first floor when it boards the elevator.

---

Demonstrate the new features of your elevator system by implementing a main that simulates the following:

1. Creates an Elevator with its attendant array of Floor objects.
2. Load the Elevator with some passengers going to different floors.
3. Initialize some (but not all) Floors with some arbitrary number of passengers waiting for the Elevator. Make sure there are enough passengers waiting so that at some point the Elevator will reach capacity and overflow to demonstrate your new exception. This will mean that the Elevator needs to make more than one pass through the buildling to get all the passengers down to the ground floor.
4. Run the Elevator up and down collecting passengers and delivering them to the ground floor.
5. Show the Elevator and its Floors successfully collaborating. In other words as the program runs it should show the Elevator unloading passengers to the Floor and the Floor boarding passengers onto the Elevator. [Here, "showing" means nothing more than simple print statements on standard output.]

## Design Considerations

Consider the final state of an Elevator after it has stopped at a Floor. What happens to its record of destinations? This depends on whether all passengers waiting on the Floor successfully boarded the Elevator. If that is true, then the floor number can be removed from the Elevator's destination record. If that was not the case, then the Elevator must come back for the passengers who were stranded–otherwise they'd be stranded there until the Elevator happens to stop there again to discharge passengers. Who is to know if they all got on board? The Floor is the only agency in a position to know this. Thus, either we make it the job of the Floor to clear the destination in the Elevator, or it must contrive to tell the Elevator "you don't have to return here, your job is done."

There is bug lurking in the woodpile here. If the Elevator can not take all waiting passengers and if, as a result, the floor number is not cleared from the list of destinations, the Elevator may never leave the floor. Instead it may go into an infinite loop asking itself "Am I at a floor? Yes. Is it a destination? Yes. Then stop." Each time it stops, it is unable to load waiting passengers, and, still leaving some behind, it will fail to clear the floor number from its destinations and never move on.
