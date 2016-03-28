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
	
}
