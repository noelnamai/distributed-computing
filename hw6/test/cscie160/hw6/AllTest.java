package cscie160.hw6;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**{@link cscie160.hw6.AllTest} Class.
 * JUnit test suite for {@link cscie160.hw6.ATMImplementation}, {@link cscie160.hw6.AccountTest} and {@link cscie160.hw6.ATMProxyTest}.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW5.*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ AccountTest.class, ATMImplementationTest.class, ATMProxyTest.class, })
public class AllTest 
{

}