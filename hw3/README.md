#Homework 3: Active Passengers, Collections

Objective: Add passengers to the elevator system and leverage Java collections.

##Resources:

* Java Collections Overview
* Collections Tutorial
* Using Collections

---

* Migrate your classes into the package cscie55.hw3.
* Define and implement a class Passenger to represent passengers in our simulation. A Passenger should know its current floor and have a destination floor in mind when waiting to board the elevator. Create the appropriate accessors and mutators for these data members.

##Accessors and Mutators

It is customary in object oriented programming to be very selective in exposing the underlying data model of a class. Instance variables are customarily made private. Those parts of the data model that are only for internal use are not exposed to the wider world in any way. Those instance variables that are of legitimate concern to other classes are accompanied by get methods to return the values of these variables. If objects of other classes might reasonably be entitled to change a state variable, then a set method is provided to support the setting of this variable. These methods are usually referred to as accesors and mutators or getters and setters.

There is a naming convention that is extremely common in Java, particularly in a form of programming called JavaBeans which is widely used in building GUI's. According to this convention accessors are named getDataMember() and mutators are named setDataMember(). For example if a class has a data member name that is publicly known and publicly modifiable it might include the following defintions:

```
private String name = null;
public String getName();
public void setName(String name);
```

You should adopt this convention in choosing names for your accessors and mutators.

* Update Floor to have three collections of Passengers, one for those merely resident (just hanging out on the floor, not queued for leaving), one for those queued for up service, and one those queued for down service.

##Collections

In the abstract a collection is a class that holds objects. Objects may be inserted into the collection, removed from the collection, and retreived from the collection by reference by some indexing or sequentially. An array is a type of collection. Java also provides some collection framework, consisting of interfaces and classes. Among the collections provided in Java 6 are    HashSet, ArrayList, and HashMap, and Hashtable among others. The Java documentation for every class is readily available if you have installed the JDK with documentation, or on the web Java 6 API Packages. From there you can navigate to any particular class such as java.util.ArrayList.

Each collection is different and determining which one is best to solve a particular problem is a common design consideration. Consider how the collection will be used. Will it be scanned sequentially (consider a ArrayList), indexed directly (consider a Vector), or keyed (consider a HashMap)? Consider which operations you want to optimize: inserting, indexing, searching. Each collection has strengths and weaknesses. You should make careful consideration in choosing the right collection for the task. Here is a good reference on the differences of these classes: Java Collections Framework

* Extend the definition of class Elevator to carry actual Passengers and to support its collaboration with the Floor class for loading and unloading those Passengers. A key question is how to change the way an Elevator represents the load it is carrying. In prior homeworks there was an array of int to represent how many riders were bound for each floor. Will you keep the array and change the type of its cells, or will you choose a brand new representation?
* Update Elevator.toString() to return a String that includes information about all the Passengers currently on board.
* Make all necessary revisions to the methods of classes Elevator and Floor to properly support the management of Passengers. Here is just one of the changes you will need to make:
* Enable a Floor object to accept a collection of Passengers when an Elevator stops there. This consists of overhauling Floor.unloadPassengers to take the Passengers out of the collection, one by one, and add them to its collection of residents.
* Make sure that your Elevator distinguishs between up	requests and down requests, stopping only when appropriate.
* Endow your Passenger class with an arrive() method that is called when the Passenger reaches the destination floor. Make sure to update the Passenger's state upon arrival.

###Demonstration

Show the Elevator, its Floors, and the Passenger collections successfully collaborating. In other words as the program runs it should show the Elevator unloading Passengers to the Floor and the Floor boarding passengers onto the Elevator. [Here, "showing" means nothing more than simple print statements on standard output.]

In the simulation, initialize each Floor with some number of Passengers waiting to board the Elevator . Give each Passenger some random destination floor. Notice that when you create the Passengers you must put them into the appropriate queues (or collections) in the Floor floor. As before, make certain to create enough Passengers to demonstrate your overflow handling.
