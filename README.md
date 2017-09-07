# Java for Distributed Computing

This course is intended for software engineers who want to learn the core Java programming language and apply it to one form of distributed programming based on the Java Distributed Object Model. The course will consist of two parts, first a study of Java as a general purpose programming language, followed by a study of Java's Remote Method Invocation (RMI).

### Table of Contents

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

## Part I:. Java, a general-purpose object oriented programming language.

Around 1996, Java gained quick notoriety as the language that enabled HTML documents to reference platform-independent programs that would be downloaded on demand, across the same connection that served up the original document and that would be run on a virtual machine on the remote client system. Such programs were dubbed "applets". This was a stunning advance that made the web much more than a facility for remote document display. Yet this early success left people with the impression that Java was a mere adjunct to the web, when, in fact, it is a great advance over C++ as a general purpose object oriented programming language. [The term "general purpose" must be qualified because there remain several types of problems where C++ may still enjoy an edge over Java. What these types are is subject to much discussion and contention.]

Java is a much easier language to learn than C++. Java has shed the enormous burden imposed by C++ on the developer in the form of memory management. In C++ every new, perforce, hath its own delete, and the devil takes those who neglect this. Java has no operator delete. While Java does not have a metaobject protocol in the strict sense, it does have a class Class which enables a program to learn about a class when it is first encountered at run time. It allows you to learn in real time such things as the names of all instance methods and instance variables of the class. By contrast C++ has nothing to compare with this.   The designers of Java took the opportunity to correct the failings of C++ and add some features that were sorely lacking in C++.   If a whole semester would be sufficient to lay the foundation for being a C++ programmer, then half a semester should be sufficient for Java, and the second half of a semester-long course could move on to apply the language where it shows special promise. In particular, Java is an ideal language for building distributed programs.

Lest there be any doubt we will not cover the Java GUI library, nor will it cover web application technologies like Java Server Pages (
