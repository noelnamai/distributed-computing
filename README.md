#Java for Distributed Computing

This course is intended for software engineers who want to learn the core Java programming language and apply it to one form of distributed programming based on the Java Distributed Object Model. The course will consist of two parts, first a study of Java as a general purpose programming language, followed by a study of Java's Remote Method Invocation (RMI).

###Table of Contents

* Summary of course content
* Course Structure
* Prerequisites
* Requirements
* Course difficulty
* This Course Is Not...
* Text Books
* Software
* Getting A Headstart
* Teaching Staff

##Part I:. Java, a general-purpose object oriented programming language.

Around 1996, Java gained quick notoriety as the language that enabled HTML documents to reference platform-independent programs that would be downloaded on demand, across the same connection that served up the original document and that would be run on a virtual machine on the remote client system. Such programs were dubbed "applets". This was a stunning advance that made the web much more than a facility for remote document display. Yet this early success left people with the impression that Java was a mere adjunct to the web, when, in fact, it is a great advance over C++ as a general purpose object oriented programming language. [The term "general purpose" must be qualified because there remain several types of problems where C++ may still enjoy an edge over Java. What these types are is subject to much discussion and contention.]

Java is a much easier language to learn than C++. Java has shed the enormous burden imposed by C++ on the developer in the form of memory management. In C++ every new, perforce, hath its own delete, and the devil takes those who neglect this. Java has no operator delete. While Java does not have a metaobject protocol in the strict sense, it does have a class Class which enables a program to learn about a class when it is first encountered at run time. It allows you to learn in real time such things as the names of all instance methods and instance variables of the class. By contrast C++ has nothing to compare with this.   The designers of Java took the opportunity to correct the failings of C++ and add some features that were sorely lacking in C++.   If a whole semester would be sufficient to lay the foundation for being a C++ programmer, then half a semester should be sufficient for Java, and the second half of a semester-long course could move on to apply the language where it shows special promise. In particular, Java is an ideal language for building distributed programs.

Lest there be any doubt we will not cover the Java GUI library, nor will it cover web application technologies like Java Server Pages (JSP), Java Servlets, or SOAP (Simple Object Access Protocol).

##Part II: Distributed Computing and Three-Tiered Architecture

A distributed application consists of collaborating objects residing on separate nodes of a network. The application, considered as a whole, is the sum total of the running processes that participate in providing the services of the application, where the processes are distributed across multiple machines connected by a network. Java provides a facility, called RMI (Remote Method Invocation), for accessing remote objects, i.e. objects living in other Java virtual machines, typically on other (actual) machines. In addition to RMI, there is a standard architecture for distributed object collaboration called CORBA (Common Object Request Broker Architecture), together with a standard communication protocol for inter object communcation, IIOP (Internet Inter-Object Protocol), that enables language-independent remote-object-access. 

[RMI and CORBA are not the only implementations of distributed computing. Indeed, there are quite a few, including one widely used protocol known as "DCOM". The Distributed Component Object Model (DCOM) is a protocol that enables software components to communicate directly over a network in a reliable, secure, and efficient manner. Previously called "Network OLE," DCOM is designed for use across multiple network transports, including Internet protocols such as HTTP. DCOM is based on the Open Software Foundation's DCE-RPC specification and will work with both Java applets and ActiveX® components through its use of the Component Object Model (COM). For example, a developer could use Java to build a Web browser applet that calculates the value of a portfolio of securities, using DCOM to communicate stock values to the applet in real time over the Internet. See the Microsoft website on DCOM for further information. ]

Distributing computing is only just now coming into its own. Once we have a way to make access to a remote object so natural and transparent that invoking a method on a remote object is indistinguishable from invoking the same method on a local object, then we have the key to expanding the paradigm which has dominated computing over the last 10 years, the client/server architecture. With distributed computing we can provide a tier or tiers between the client and the server. By using the middle tier to make intelligent decisions as to which server or servers are appropriate, and by placing most of the "business logic" in the middle tier, the client side of the application can be freed of the burden of responsibility for both the display and application algorithms. The result is a truly thin client. From this standpoint the value of Java is in its power to enable thin-client, 3-tier architecture systems.

##Part III: Alternate New Technologies

The notion of a distributed object model is a low-level concept. Today, most distributed applications are built as webapps, i.e. applications that rely on a web browser as the universal client and HTTP as the request/response protocol. Such applications may run without any distributed object model. The last few weeks of our sessions will be devoted to examining some technologies that are in common use. There will be no homework for the new technologies, whatever they may be.

###Course Structure and Requirements.    

The instruction for the course consists of a weekly lecture, plus a weekly 1-hour section meeting. Section meetings are meant to be a kind of workshop where you meet with fellow students and a Teaching Fellow who is a qualified programmer. There you can ask much more detailed questions than the lecture allows. Your Teaching Fellow may present programming problems and ask you questions. We will make an effort to schedule a meeting on the same night as the lecture, before the lecture meets, to help people traveling long distances to Cambridge to avoid two trips a week. The on-campus section meeting will be video recorded and available for streaming on the day after it meets.

###Prerequisites.     

There are no formal prerequisites for this course, but there are some requirements of common sense. The course is intended for students who already call themselves "programmers". It is definitely not an introductory course. Prior knowledge of C++ is a decided advantage for two reasons: (1) Java follows closely the C++ syntax in many places, and, (2) C++ is an object oriented programming language. The ingredient that will give the greatest advantage is the most elusive to describe, and that is something that comes with programming experience, namely, intuition that allows you to ask the right questions when your software misbehaves. Designing and debugging distributed applications is among the most challenging software activities. For example, consider this case. Your error message says that no class definition can be found for one of your classes, yet you can plainly see the class file right where it ought to be. Until you tumble to the notion that the error may originate in another process altogether and that you are seeing its message sent over the network from that remote process to your local program, you may stand no chance to fix it.

###Requirements

* 7 homeworks. Three will model a real world system of elevators, floors and passengers; four will be devoted to modeling an ATM (standing for "automatic teller machine", not the network protocol called "asynchronous transfer mode"). The elevator problems will introduce you to Java as a general purpose programming language; the ATM problems will use your Java knowledge for building a distributed system, in which the bank and the ATM are remote from one another.
* No examinations. No hour exams. No final. The skills and knowledge we are aiming for do not give themselves to examination.
* No particular hardware or software platform is required. You must have a computing environment that includes Java, i.e. you must be able to run Java code in your environment. You don't even need a network, since your processes can all be on one machine, connected by network protocol to your "localhost." Every student registered in the course is entitled to receive an account on a Harvard machine, but you must provide your own access through an Internet Service Provider of your choice. [If you are reading this on the web, you already have an ISP.]

###How Tough Is This Course Going To Be?     

This is a question frequently asked by people who inquire about my courses. You are entitled to as good an answer as I can provide, but I can't be precise. It will be tough. It is a programming course, after all. You will write a lot of code, in a new language and some of it will use a technique that is quite unfamiliar to you. But it will not be among the toughest courses, the ones that are legendary. You will not write a compiler, or an operating system. A lot will depend on your programming experience. The level of effort to do very well is inversely proportional to the level of those imponderable intuitions you possess. If you've written a lot of code in an object oriented language like C++ or Python, and your debugging skills are sharp you might spend as little as 5-10 hours for each homework. And the less your preparation the more effort you will need to do very well. If you are in doubt, ask yourself if you are in a position to cope with a course that turns out to be a killer. Look at your committments and ask yourself can I give up my weekends for four months in order to deal with a crisis, if that's how it develops. And remember the rules for withdrawing from a course and also the rule for getting an extension for receiving a final grade after the semester ends, i.e. once you opt to take an extension you will receive a regular letter grade and if substantial parts of your semester's work are missing you will get a failing grade.

###Things This Course Is NOT

This course is

* NOT a course in Web programming [See Building Dynamic Websites and Web Development Using XML]
* NOT a course in Object Oriented Design
* NOT just a Java course
* NOT just a course in distributed programming
* NOT an introductory course

###$Books
There is no official course text book, only recommended books. You might even consider doing without a textbook and relying instead on the tutorials available from Oracle's website. They are excellent and comprehensive.

* Core Java Vol. I Fundamentals, 8/E    Horstmann, Cornell. Prentice Hall, 2008. ISBN-10: 0132354764
* Core Java Vol. II Advanced Features, 8/E    Horstmann, Cornell. Prentice Hall, 2008. ISBN-10: 0132354799[Includes the distributed computing parts of our course.]
* Oracle tutorials: Tutorial index, also The Really Big Index These links are so useful that I have them bookmarked in my browser.
* The Java doc pages for Java. In the Java world there is a new form of programmer's reference manual which is made up of "Java doc" html pages for all the classes that comprise a given system. Java itself has such a web of pages describing all the classes that make up the standard Java libraries: JDK1.7 API's Bookmark this page, if you haven't already.
Where to buy these books? In today's world ordering a book is no more difficult than gassing up your car. There are software bookstores like Softpro, and there are web-based book sellers like amazon.com. Take your pick. The Harvard Coop should have the Core Java book in stock.

###Software

There is no specific requirement for software in this course. One needs a Java computing environment, of course, which means a version of the JDK (Java Developers Kit). The very latest version released by Oracle is J2SE 7.0, referred to as Java SE 7. You can download this version of Java here: Java SE Downloads [Most commercial applications in production today that were built using Java were built on earlier versions of the JDK.] There will be no Java-7-specific features used in this course, so Java 6 (and even Java 5) will suffice for our purposes. If you are using a Mac (OS X), you probably have Java 6 already on your system. [Java 7 on Mac Mavericks OS has problems.]

Commercial software projects typically involve teams of software engineers organized around software subsystems. The sheer volume of code becomes a challenge to manage and navigating between the files, especially across subsystems, becomes close to impossible without the help of an IDE (Integrated Development Environment). IDE's enable you to skip from file to file with just a mouse click. An IDE catches syntax errors as you type them and offers name completion with a keystroke. There are several IDE's wide use. Two products bear mention, one free, one for purchase:

* A commericial IDE that has won the approval of advanced Java developers is IntelliJ IDEA from JetBrains. JetBrains has granted students taking this course a free, year-long license. [License info here: Academic License for Idea IDE.]
* A free IDE that has become a standard is Eclipse. It had a long period of development and several releases as a commercial product of IBM, then has became an open source application. Get it here: http://www.eclipse.org
* Also worth mentioning is Oracle's NetBeans, which is bundled with the latest JDK.

When you start learning Java your systems will be tiny and you can get by with just a text editor. My own personal favorite is a beautiful, beloved, venerable piece of software called emacs. Many older generation programmers use emacs rather than an IDE. Two advantages of emacs are its free cost and the fact that it can be found (or imported) everywhere. [You can learn about getting emacs on your computer here: emacs for Windows ]

There are two reasons to write your first few homeworks using a plain text editor. (1) With a simple editor you and your code are always face to face, with no third party IDE between you and it. When you compile and build your programs from a command line, you may develop a better understanding of how the software works, without the magic of an IDE. And, (2) however straightforward and self explanatory an IDE is, you will need time to it get on your system, install it and learn how to use it.

###How To Get A Headstart

There are two things one can do to be prepared: (1) establish your computing environment (purchase and install an IDE and test it out, or brush up on your emacs skills, if you are a confirmed hacker and are ready to write, compile and debug your code in emacs), and, (2) do the "jump start" homework (Homework #0).

###Who Is Teaching This Course?

The lecturer is me, Charles Sawyer. In a sense, if you take this course, you are hiring me as your instructor, so I think it is fitting that you should have access to my resume. [Note: This resume as written with the sole purpose of getting work in software.] And since you may be listening to me one evening a week until the merchants will be flogging their wares for the gifting season it may be of interest to learn some personal details about me, and if so, visit Personal Details and 2120 South Michigan Avenue. [These two websites never even mention software or computers but they present other aspects of my intellectual and cultural life where I have invested myself with every bit as much passion and vitality as I have given to software over the years.] My interest in music has lead me to launch a course in Harvard Extension in the history of blues music. The home page for the course is at www.harvardblues.com.

The Teaching Fellows in this course all come from leading companies in the software industry. They are some of the most talented people I know. If I were hiring a software team to write commercial products based on Java for distributed computing, these are precisely the people I would seek to hire.

##© Charles Sawyer, 2014	

```
echo '[q]sa[ln0=aln256%Pln256/snlbx]sb3135071790101768542287578439snlbxq' | dc
```
