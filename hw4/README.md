#Homework 4: Automated Teller Machine

##Objective: 

Develop the back end of a distributed Automated Teller Machine (ATM) system using basic Java networking. Then write JUnit test classes for the principal ATM classes, and a build.xml that will drive the tool ant to build the application, create its jar file and run the JUnit tests.

##Resources:

* Interfaces
* Networking
* Sockets
* I/O
* An overview of ant, the portable build utility.

###Downloads:

* Download ant
* Download JUnit

---

You will be developing a distributed Automated Teller Machine (ATM). The ATM will be hosted in a different process than the client application. The client will connect to the server using Java networking and communicate with the server using a protocol you will define. You will also use Java interfaces to add some sophistication to your remote invocations.

###Functional Requirements

The ATM will simulate a real world automated teller machine.

The ATM must support the following operations:

* deposit: add some dollar amount to account balance

* withdrawal: deduct some dollar amount from account balance

* balance inquiry: get current account balance

(For now) The ATM need only support one account and the balance for that one account.

The ATM will run in its own process and will handle remote requests from a client over a socket connection (clients get an java.net.Socket) running in some other process.

###Design

In the ATM system, the client and ATM will be running in different processes. Since the client cannot reference memory in the server process, it cannot get an actual reference to the real ATM object running on the server. Instead the client will use a proxy that presents all the behavior that characterizes an ATM. In fact, the client doesn't even need to know if the actual ATM is local or remote. This is achieved using a Java interface.

Create an ATM interface:

```Java
public interface ATM 
```

This interface will be implemented on both client and server sides of this distributed application.

On the server side will be an ATMImplementation class that you will created, implementing this interface:

```Java
public class ATMImplementation implements ATM 
```

This is the actual ATM that has a reference to an instance of a class Account and manipulates its balance. An instance of ATMImplementation will run on the server.

Unfortunately, the client won't be able to directly reference this object. So create a client proxy, ATMProxy, that also implements the ATM interface:

```Java
public class ATMProxy implements ATM 
```

The implementation of the interface methods in ATMProxy won't actually manipulate any Account data. Instead, the proxy will connect to the server running the real ATM and for each call it will send a message over the network requesting that the server perform the work.

The server process will start up, create an ATMImplementation instance, and then open a socket and wait for incoming requests. When a request arrives the server will dispatch to the appropriate ATMImplementation method and reply if necessary.

###Architecture

The following diagram illustrates how the client operates on an ATM which is actually an ATMProxy that communicates over the network to the server and dispatches the call to the ATMImplementation.

##Assignment

[Portions of the requirements that appear in this blue font are supplied in a file you can download from this link: hw4.jar]
All classes should be in the package:

   cscie55.hw4
ATM

Create the following ATM interface:

   public interface ATM {
      public void deposit(float amount) throws ATMException;
      public void withdraw(float amount) throws ATMException;
      public Float getBalance() throws ATMException;
   }
ATMImplementation

Develop a class ATMImplementation that implements the ATM interface. For now the ATMImplementation can have just one Account. All transactions on the ATM act on the balance of that Account.

  public class ATMImplementation implements ATM
Account

The class Account should be the bare minimum needed to hold the state information that models a bank account. In fact, it need only be a wrapper around an appropriate primitive type that represents the balance in the account. The constructor for ATMImplementation should create an Account object using the default constructor and store a reference to it.

ATMProxy

Now, the client wants to make calls on the ATM, perhaps withdrawing some money or checking the balance, but, the client can't actually get a reference to the ATMImplementation. Instead it will operate on an ATMProxy that implements the ATM interface. As far as the client is concerned, the instance is an ATM. It doesn't care that the ATMProxy actually connects to a remote server process and delegates the ATM methods to the remote ATMImplementation.

Develop an ATMProxy class that implements the ATM interface.

   public class ATMProxy implements ATM 
The proxy doesn't actually hold any account data as part of its state. It simply dispatches each method of the ATM interface to the remote ATMImplementation. The proxy will need to make a socket connection to the server process described below. Once a socket connection is made, the proxy gets the input and output streams from the socket. To send a message to the server, the proxy writes outgoing requests to the output stream. You may want to wrap the basic outbound stream with a convenience wrapper stream like java.io.PrintWriter. Similarly, the proxy reads incoming responses from the input stream which may also be wrapped in another stream like java.io.BufferedReader.

To communicate with the server you will need to define a protocol, like the one used by the SimpleClient and the SimpleServer. The protocol enables the proxy to tell the server what it wants the server to do. And while the server is processing that request the proxy must wait for that response. Consider a simple String based protocol that includes the method to be executed and some String representation of any parameters for that method. Then have the proxy write the String messages directly onto the output stream obtained from the socket.

Server

Develop a class Server that will host the ATMImplementation object. Server.main() should accept a port number as a command line parameter. This port is the port on which the Server will listen for client requests.

For example:

   C:\> java cscie55.hw4.Server 7777
would tell the server to create a ServerSocket on port 7777.

Server.main should instantiate an ATMImplementation and begin listening for requests. To prepare for requests, the Server must create a ServerSocket and then begin accepting incoming connections on that ServerSocket. When a request is received from the client, the Server should read in incoming message from the socket input stream, interpret the request based on the protocol defined above and then dispatch the request to the appropriate method of its ATMImplementation object. If a response is required, the Server must then form the appropriate response message and send it back to the caller over the socket output stream.

Client

To drive you system, create the following Client class and include this exact class with your submission:

   package cscie55.hw4;
   public class Client {
      public static void main(String[] args) {
         try {
            // parse command line arguments
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            ATM atm = new ATMProxy(host, port);
            // get initial account balance
            System.out.println("Balance: "+atm.getBalance());
            // make $1000 deposit and get new balance    
            System.out.println(" Depositing: 1000");
            atm.deposit(1000);
            System.out.println("Balance: "+atm.getBalance());
            // make $250 withdrawal and get new balance
            System.out.println(" Withdrawing: 250");
            atm.withdraw(250);
            System.out.println("Balance: "+atm.getBalance());
            // make $750 withdrawal and get new balance
            System.out.println(" Withdrawing: 750");
            atm.withdraw(750);
            System.out.println("Balance: "+atm.getBalance());
         } catch (ATMException ae) {
            System.out.println("An exception occurred while communicating with the ATM");
            ae.printStackTrace();
         }
      }
   }
When invoking this client, specify the host and port of the server process as command line arguments. For example:

   C:\> java cscie55.hw4.Client localhost 7777
Demonstration

To demonstrate your system you will need to start two distinct processes.

First startup the server specifying a port number to listen on.

   C:\> java cscie55.hw4.Server 7777
Once the server is up and running, start up the client specifying the host and port of the server process.

   C:\>java cscie55.hw4.Client localhost 7777
You should see the following output from the Client:

   Balance: 0.0
    Depositing: 1000
   Balance: 1000.0
    Withdrawing: 250
   Balance: 750.0
    Withdrawing: 750
   Balance: 0.0
Note: when you use the front end supplied in the jar file, your output will always show a balance of 0.0. What else would you expect from an ATM which has no Accounts?

JUnit and ant Requirements: Now comes the hard part. In addition to the ATMImplementation and Account classes that compise the back end of this application, you are required to write JUnit test classes for each of these classes:

Account
ATMImplementation
ATMProxy
Next, write a build.xml suitable to drive the tools ant and support the following features:
Compile all the Java source code in the application
Put the resulting class files in a different directory
Remove all compiled class files [suggest an ant target <clean>]
Build a jar file from the classes (don't include the source code)
An ant target that combines these jobs to clean out all .class files, compile all the sources, and build the jar file.
[Extra credit: Run all the JUnit test classes you have created to accompany the application. Require additional jarfile ant-junit.jar For this to work you will need to set an environment variable named ANT_HOME to the full pathname of you top level ant directory. Plus you need to put that jar file, ant-junit.jar in the lib directory of the ant install.]
