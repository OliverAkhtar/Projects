import java.util.LinkedList;

public class readyQueue
{
	static LinkedList<PCB> linkedQueue;
	
	static boolean isFull(){
		return (linkedQueue.size() == 10);
	}
	
	PCB dequeue()
	{
		return linkedQueue.remove();
	}
}
