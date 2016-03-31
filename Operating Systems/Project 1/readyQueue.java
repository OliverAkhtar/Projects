/*
This class implements a ready queue which simulates main memory for the CPU scheduler. For SJF scheduling, the customLinkedList allows for insertion 
into the list based on ascending current CPU burst time ordering. Otherwise, Java's implementation of a linked list suffices.
 */
import java.util.LinkedList;

public class readyQueue
{
	LinkedList<PCB> linkedQueue;
	customLinkedList SJFQueue;
	
	readyQueue()
	{
		if(cpuscheduler.algorithm.equals("SJF"))
			SJFQueue = new customLinkedList();
		else
			linkedQueue = new LinkedList<PCB>();
	}
	
	boolean isFull()
	{
		if(cpuscheduler.algorithm.equals("SJF"))
			return (SJFQueue.size() == 10);
		else
			return (linkedQueue.size() == 10);
	}
	
	boolean isEmpty()
	{
		if(cpuscheduler.algorithm.equals("SJF"))
			return (SJFQueue.size() == 0);
		else
			return (linkedQueue.size() == 0);
	}
	
	void enqueue(PCB next)
	{
		if(cpuscheduler.algorithm.equals("SJF"))
		{
			next.state = "Ready";
			SJFQueue.add(next);
		}
		else
		{
			next.state = "Ready";
			linkedQueue.add(next);
		}
	}
	
	PCB dequeue()
	{
		if(cpuscheduler.algorithm.equals("SJF"))
			return SJFQueue.remove();
		else
			return linkedQueue.remove();
	}
	
	int size()
	{
		if(cpuscheduler.algorithm.equals("SJF"))
			return SJFQueue.length;
		else
			return linkedQueue.size();
	}
}