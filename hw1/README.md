# Elevator Simulation

## Objective: 

Interpret a software specification and develop your first Java class.

## Resources:

* Java tutorial
* Classes
* Packages
* Writing Doc Comments

### Prologue 

There is a fundamental difficulty in designing exercises for learning to program in a new language. Learning the syntax and proper usage of a new language and learning to design programs using the language are two distinct goals and sometimes they conflict. When you don't know a hammer from a saw it's hard to build anything, even something as simple as a bread box, no less a dog house. So, you must learn to use a hammer and saw before you can build something. Yet everyone knows that exercises in hammering and sawing are very boring. Besides, just knowing how to use a hammer and saw doesn't mean you can build anything. What you want to do eventually is to build big structures, houses, office buildings, and palaces. So you set out to build something very modest, like the dog house, and you try to learn about the tools as you build. You look for a balance between learning carpentry and learning architecture. Sometimes you have to slight one to get the other. In this course when we are forced to choose we will aim for good carpentry, but we will still try to include important ideas of architecture. It's all a question of emphasis.

The homework exercises for this course focus on modelling two real world systems, an elevator system for a building and an ATM (standing for "automated teller machine", not "asynchronous transfer mode"). In both sets of exercises you will write Java classes to represent real world objects like Floor, Elevator and Account. You will make them cooperate to simulate the real system, making the simulation more expressive until it behaves more and more like the real world system. The exercises are progressive - what you do in one homework will be revised and extended in the next one.

### Where to start

In building commercial software the first step is usually to set down the requirements in a document called a "Requirements Specification," a.k.a. "the spec." Often the actual engineers who will build the system are nowhere around during this part of the process. Typically a spec describes the real world situation which the system will model and control. It may never mention computers, but if it does, usually it describes only what the end user may see when interacting with the completed system. When the software engineers enter the picture they start with the spec. They analyze it, and design the software components which will model and control the real world system to the satisfaction of the customer as set forth in the spec.

So this is where we will start. If you want a challenge before you even begin these exercises, put this write-up aside and write a requirements spec for a multi-elevator system in a building of arbitrary height. You will be surprised how difficult it is to arrive at a final spec that is both comprehensive and consistent.

[Here we have encountered one fundamental truth of the trouble with software: the customer may have written a system spec that does not capture his/her intent. Thus, the system builders may deliver a product that meets the spec exactly, only to be told by the customer that it's all wrong. This is why prototyping is so important: if the customer sees a working prototype before the full-fledged system is built, such catastrophes may be avoided. Indeed, there is an entire school of software design based on the premise that the biggest software errors derive from flawed specs. Moreover, the reasoning goes, to catch big errors at spec time one must have a proper formalism for specifying the requirements, a formalism that comprises a requirements specification language, as formal and specific as any computer language. One such formal approach is called "Z" (pronounced "zed"). If this sounds interesting, search the Web for Zed.]

## Requirements Specification

You are to write a simulation of an elevator system for a multi-story building. A full featured simulation would be probabilistic and event-driven, perhaps even employing fuzzy logic and adaptive algorithms. We will not carry the simulation so far as that. The details of which features to capture in your homeworks are given in the individual exercises. The way that the exercises are stated may entail violating some of these requirements, so don't take this spec as mandating the behavior of classes in your homeworks.

### The Real World System 

A building, a certain number of stories tall, is equipped with an elevator system comprised of a certain number of elevators. Passengers arrive in the building at random times with a certain probability of an arrival per second. During the time a passenger is in the building s/he may request elevator service from the floor where s/he is currently located. The request always specifies a direction, up or down. Passengers on the lowest floor may only request up service; those on the top floor may only request down service; all others may request either service.

On entering an elevator a passenger selects a destination floor. The elevator then closes its doors and moves to that destination floor, possibly stopping on intermediate floors to deliver other passengers who may have selected intermediate floors. When an elevator arrives at a destination floor it stops, opens its doors, and discharges any passengers who have selected that floor. Having stopped, opened its doors and unloaded its passengers, the elevator then admits any passengers who may be waiting for service in the direction the elevator may be currently moving, subject to the restriction that the elevator may not exceed its capacity to carry passengers. Passengers who do not succeed in boarding the elevator because it is full must make a fresh request to obtain service at the floor on which they are waiting.

While in the building every passenger is always on some floor or in an elevator. While on a floor s/he has a given probability per second to decide to leave the building, and another probability to decide to move to another floor. When a passenger decides to move to another floor s/he joins the up or down queue on that floor according to the relative locations of that floor and the destination floor. If that passenger joins an empty queue, a request for service (UP or DOWN) is generated. [On the floor itself this takes the form of pushing the appropriate button, turning the UP or DOWN light on. Elsewhere in the system the fact of the new service request will (eventually) result in the dispatch of an appropriate elevator to journey to that floor.] When a passenger decides to leave the building s/he joins the down queue on that floor, destined for the first floor, and exits the system upon arrival at floor # 1. Thus, passengers on a floor are either in a queue waiting for an elevator or merely resident.

At the beginning of the day the system is initialized with no passengers in the building and all elevators stopped on the ground floor, doors open. Operation of the system for one day ends at a given ending time, at which time all remaining passengers leave the building.

## The Purpose Of The Simulation.

Such a simulation might be built for very different purposes. For example, it might be commissioned by an elevator company as a preliminary step toward developing the system that will actually control real elevators. On the other hand, the simulation might be part of a computer game that shows a graphical display of an entire building on a computer screen. Or, perhaps a firm of building architects commissioned the simulation in order to gather information on how the values of certain parameters will effect the efficiency of service in a building they are designing. These different objectives may impose very different standards. Take the issue of whether the elevator doors are open or closed. This matters very much for the first two customers, in the first case for safety reasons and in the second for realistic display. But for the third, the building architects studying traffic patterns, the doors really play little or no role. Building architects care about things like the utilization of the elevators, what percentage of time they are idle, how long on average a passenger must wait for an elevator, how often passengers waiting for service are unable to board the elevator because it is full. They want to know how many elevators are needed for their building, what is the best policy for selecting which elevator should be sent to a floor to provide service. For this series of exercises we shall assume that we are developing the simulation for the third hypothetical customer, the building architects. So, you should design your simulation in a way that will be conducive to gathering such information. However, you can relax, because you need not actually support such statistics gathering in your simulation. But don't entirely lose sight of the ideal of supporting statistics gathering. At least take care that your design does not make put obstacles in the way of statistics gathering.

[Note: What is described above is known as a discrete event simulation. Such a simulation assumes that time evolves in quantum jumps, or clock ticks. The simulation need not run in real time. A "second" in the requirements spec is not an elapsed second in the running simulation, rather it is a clock tick, . Every time-sensitive entity in the system keeps its state constant between clock ticks and when a clock cycle has passed the world stops and each entity adjusts its state according to the new time. The act of updating state is always assumed to take zero time, even if doing so might entail a complex series of actions. The elevator exercises in this course will only lay the groundwork for a simulation. Instead of implementing the full simulation we will switch to a different problem model entirely, an automatic teller machine.]

# The Exercise

* Create an Elevator class in the package cscie55.hw1. The class should have a no argument constructor that sets up the elevator's state.
* Define several constant values in the Elevator class. Include the capacity and number of floors in the building.
* Augment the Elevator with data members indicating the current floor and current direction of travel. Also add data members for both its destination requests and the number of passengers destined for each floor. [It's tempting to think that an Elevator can determine if a given floor is among its destinations just by checking to see if it has any passengers destined for the floor. But this will not stand up under the later requirement that an Elevator could be destined for a particular floor just to fetch passengers, while having none on board who are going there.]
* Add a method move() to the elevator with the characteristic that every time it is called it increments or decrements the value of the current floor, according to whether the elevator is moving up or down, changing directions as appropriate.
* Override the toString() method of java.lang.Object (the default base class of every Java class) in your Elevator class which returns a String object summarizing all the pertinent values in the elevator, including the number of passengers on board and the current floor. [The call to System.out.println() will accept any object as an argument and will look for a toString method for a means to create a String it can write to standard out. Thus, if Elevator is equipped with toString, then System.out.println(myElevator) will get a String from myElevator.toString() and send it to standard out.]
* When the Elevator reaches a floor that is among its destinations it should stop, otherwise it should keep moving. Write the stop() method in such a way that it prints a message to standard out announcing the event of its stopping and have it send your Elevator to standard out after all processing for the stop is complete, i.e. after discharging passengers, to show its post-stop-processing state.
* Stopping should entail appropriate changes in state. For instance, if an Elevator had two passengers destined for a particular floor, stopping there should clear the number of passengers destined there and the number of passengers on board should decrease by two and eliminate the floor as a destination of the Elevator. [The class Floor comes in the next homework so you needn't worry about what happens to the passengers after the Elevator stops.]
* Endow your Elevator with a method, boardPassenger(int floor), for boarding a passenger destined for a particular floor. Calling this method should increase the number of passengers destined there by one, and should increase the number of passengers on board by one. [Note: this does not require defining a class Passenger.] For now, allow your elevator to fill beyond its capacity, accepting more passengers than it can hold. In homework 2, we will use an exception to handle this error case and limit the occupancy in the elevator.
* Add self-documenting javadoc comments to your class. Run the javadoc utility on your class and submit the resulting HTML file, Elevator.html, with your work. If you are submitting hard copy, submit a printout from your browser displaying the html documentation for the class. [This requirement will apply to every homework during the course.]

## Demonstration

Demonstrate the features of your class by designing main to make calls to boardPassenger to add two passengers destined for the 2nd floor and one destined for the 3rd floor. main should print out your Elevator object to show its state before starting its journey. Then use a for loop to call move sufficiently many times to send your Elevator up to the top floor and down to the first floor a few times. Your output should look nearly like this:

```
Currently 3 passengers onboard
Current Floor: 1
   
Stopping on floor 2
Currently 1 passengers onboard
Current Floor: 2
   
Stopping on floor 3
Currently 0 passengers onboard
Current Floor: 3
```

## Assumptions

* Use 10 as the default elevator capacity and assume 7 floors in the building.
* All behavior is deterministic, hard-wired, i.e. the program runs the exactly the same way every time. [A true simulation would be probabilistic and such programs run differently every time they run, even when the input is the same, because somewhere in the program some object chooses its execution path based on the roll of some dice.]
* There is one gigantic simplification that will come into play in later homework exercises. This pertains to the sweep algorithm for the elevator, i.e. the algorithm that determines how the movements of the elevator are programmed. If the simulation were commissioned by an actual elevator company, or by a firm of building architects, one of the chief objectives would be to find the optimal sweep algorithm, one that would minimize the average time a passenger waits for service under a great variety of usage patterns. Here we will adopt the simplest sweep algorithm, one which will guarantee that no passenger will ever be stranded. The algorithm is simple: the elevator is always moving, sweeping up to the top floor, turning around and going back down to the first floor, reversing direction and heading up to the top again, and so on.
