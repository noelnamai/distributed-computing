package cscie160.project;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**{@link cscie160.project.AllTest} Class.
 * JUnit test suite for {@link cscie160.project.ATMImplementation}, {@link cscie160.hw6.AccountTest} and {@link cscie160.hw6.ATMProxyTest}.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - FINAL PROJECT.*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ AccountImplTest.class, ATMImplTest.class, })
public class AllTest 
{

}