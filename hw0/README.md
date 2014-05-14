Homework 0: Jump Start

Grading: Self-graded, do not submit, not included in final grade.

Last Modified: 07/12/2010 03:26:45

Objective: To establish your computing environment and get started with Java.

Resources:

Java tutorial
Java Tutorial: First Cup of Java
Classes
In Java almost nothing happens outside of class definitions. There are no stand alone functions, no global variables defined outside the scope of a class. Thus, the simplest Hello World program consists of a class, with a method named main in which appears a statement that uses standard output to write the message "Hello, World!". The main must be declared with a certain signature. Here is the entire program:

```
//File HelloWorld.java
public class HelloWorld
{
	public static void main(String argv[])
  	{
  		System.out.println("Hello, Brave New World of Java!");
  	}
}
```

Here, the out data member of the class System is an instance (individual object) of the class PrintStream, defined in a Java library, which is always present wherever Java is found. It is the equivalent of the C++ object cout. The function call println is one of the methods of this class, which is part of the Java package java.io. [More on packages later.] println prints the character string which is its argument and appends a new line, guaranteeing that each successive call will appear on its own line of output. Finally, the signature of the main function is crucial to making this class a stand-alone Java application. It must have the three qualifiers, public static void and must have an argument whose type is an array of String[], an array of String objects. String is a class in the Java package known as java.lang, always present in any Java environment.

To run this program we must first compile it with javac, the Java compiler supplied with the Java language, or a compiler present in your IDE (Integrated Development Environment). The former is called from a DOS prompt:

   c:\javadist> javac HelloWorld.java 
This produces a file, HelloWorld.class. Running the program consists of calling the Java virtual machine (JVM) with our class, HelloWorld, as the argument. We do this from a DOS prompt:

   c:\javadist> java HelloWorld 
When we start the JVM, supplying a class as an argument, the JVM loads that class and looks for a main method with our special signature. This is a convention codified by the Java Language Standard.

Exercise # 1. Use a plain text editor, like notepad or emacs, to write the minimal HelloWorld program, compile it and run it, using only javac and java supplied with the Java Developers Kit (JDK). If you are using an IDE like eclipse or Idea, repeat the exercise, making a project in the IDE and building and running the program.

Trouble shooting. You compiled your file and you call Java but the Java Virtual Machine bails out with a message about failing to find your class. This is a common error and derives from the way in which the JVM finds classes. When first encountered this is a frustrating problem. javac found my source file, why can't java find my class? you say. It is simply that the JVM locates classes according to an environment variable called CLASSPATH. The value of this variable is a series of directories in your file system. If you have this variable set, and if HelloWorld.class is in one of these directories, the JVM will readily find it, and if it is not, then the JVM will be clueless, even if it happens to be in the directory where you are running the java command. One remedy is to set the classpath, adding "dot", the current directory; another, which works in this case but not in general, is to unset classpath altogether.
The following exercise calls for the use of packages, which are covered toward the end of UnitI. [See Packages in that web page.] Doing the next exercise means jumping ahead to a topic covered in the second lecture. You may find this part tough going, and if so, you could start Homework #1 and start without using packages. On the other hand, if you can complete this exercise you will have a very good leg up on the semester's study!

Exercise #2: Java classes may be grouped together in sets called packages. Packages provide scope as well as allowing you to organize your classes. In the core java class set there are several packages which contain classes that you will use reguarly including java.lang, java.util, and java.io. Put your HelloWorld class in the package cscie160.hw0 using the following package statement:

   package cscie160.hw0;
Now rebuild and rerun your class. Notice that you will need to move the source file into folders corresponding to the package structure to compile and run it, otherwise you will get errors. And notice when you run the class you specify it by its full class name:

   c:\javadist> java cscie160.hw0.HelloWorld 
Finally, Java classes can be collected or "archived" together in a Java Archive or JAR file. JAR files are in fact ZIP compressed archives. You can create them using the jar utility included with the JDK or you may use any ZIP utility such as WinZIP. The real power of JAR files is collecting an entire set of classes together for "deployment". In the homeworks to come you will package up the many classes of your solutions in JAR files to submit them. As an excercise, try creating a JAR file with your HelloWorld class in it. Then try running your solution using the JAR file. This will require you to modify your classpath to include the JAR file rather than the root directory of your class structure. You may change the classpath environment variable or simply specify the classpath to java using the -classpath command line parameter.
