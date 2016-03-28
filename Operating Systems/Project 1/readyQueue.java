import java.util.LinkedList;

public class readyQueue
{
	LinkedList<PCB> linkedQueue = new LinkedList<PCB>();
	
	boolean isFull()
	{
		return (linkedQueue.size() == 10);
	}
	
	boolean isEmpty()
	{
		return (linkedQueue.size() == 0);
	}
	
	void enqueue(PCB next)
	{
		next.state = "Ready";
		linkedQueue.add(next);
	}
	
	PCB dequeue()
	{
		return linkedQueue.remove();
	}
}
