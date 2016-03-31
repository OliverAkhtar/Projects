/*
This class outlines a queue data structure that keeps PCB objects which are executing I/O activity. When a PCB is inserted into a BlockedQueue,
the constructor calls the setIO method of the PCB which sets the time of I/O completion. 

IOComplete checks whether or not the process at the front of the blocked queue has finished I/O activity by checking if the time of I/O completion
for that process is less than the current value of the CPU clock.
*/
import java.util.LinkedList;

public class BlockedQueue
{
	LinkedList<PCB> linkedQueue = new LinkedList<PCB>();
	
	void enqueue(PCB next)
	{
		next.state = "Blocked";
		next.setIO();
		linkedQueue.add(next);
	}
	
	PCB dequeue()
	{
		return linkedQueue.remove();
	}
	
	boolean isEmpty()
	{
		return (linkedQueue.isEmpty());
	}
	
	boolean IOComplete()
	{
		PCB ioPCB = linkedQueue.peek();
		if(ioPCB.ioCompleteTime < cpuscheduler.myCPU.cpuClock)
			return true;
		else return false;
	}
	
	int size()
	{
		return linkedQueue.size();
	}
	
}
