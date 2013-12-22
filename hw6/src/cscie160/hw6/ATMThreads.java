package cscie160.hw6;

import java.util.LinkedList;

/**
 * {@link cscie160.hw6.ATMThreads} class.
 * {@link cscie160.hw6.ATMThreads} has a reference to the request queue.
 * {@link cscie160.hw6.ATMThreads} retrieves the ATMRunnable from the list and executes the request contained in it.
 * @author Namai Were Noel.
 * @version Harvard CSCIE160: Java for Distributed Computing - HW6. 
 * */
public class ATMThreads extends Thread
{
	/**Object of type {@link cscie160.hw6.ATMRunnable} */
	private ATMRunnable atmRunnable;
	/**LinkedList of type ATMRunnable.  Acts as a queue for the runnables.*/
	private LinkedList<ATMRunnable> queue;

	public ATMThreads(LinkedList<ATMRunnable> queue) 
	{
		this.queue = queue;
	}

	/**Calls the wait() method of the request queue, thereby waiting to be notified when another thread puts a request on the queue. 
	 * When notified, the thread retrieves the ATMRunnable from the list and executes the request contained in it. 
	 */
	public void run() 
	{
		while(true) 
		{
            synchronized(queue) 
            {
                while (queue.isEmpty()) 
                {
                    try
                    {
                    	queue.wait();
                    }
                    catch (InterruptedException ie)
                    {
    					ie.printStackTrace();
                    }
                }
                
                atmRunnable = (ATMRunnable) queue.removeFirst();
                
                // If we don't catch RuntimeException, 
                // the pool could leak threads
                try 
                {    
                	atmRunnable.run();
                
                    System.out.println("Running request in thread: Thread-" + this.getName());
                }
                catch (RuntimeException e) 
                {
                	e.printStackTrace();
                }
            }            
        }
	}
}