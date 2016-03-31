/*
This class outlines a linked list which can be used store PCB elements in the order of ascending current CPU burst time. It is used as the linked list
for the myReadyQueue object if the scheduling algorithm is Shortest Job First.

The add method inserts the PCB object into an appropriate position depending on it's current CPU burst time, and of other PCBS already in the list.
The remove method is used to return and also remove a PCB from the list. An important feature of the remove method should be noted, although the
ordering of the list allows for easy removal of the PCB with shortest current CPU burst time, the remove method makes sure that a process whose
arrival time is greater than the current value of CPU clock should NOT yet be removed.
 */
public class customLinkedList
{
	private static class ListNode
	{
		public PCB process;
		public ListNode next;
		public ListNode prev;
		
		public ListNode(PCB input)
		{
			process = input;
			next = null;
			prev = null;
		}
	}
	
	ListNode head;
	ListNode tail;
	int length;
	
	customLinkedList()
	{
		head = new ListNode(null);
		tail = new ListNode(null);
		tail.prev = head;
		head.next = tail;
		length = 0;
	}
	
	int size() {return length;}
	
	void add(PCB newProcess)
	{
		ListNode spot = head;
		while(spot.next.process != null && 
			  spot.next.process.cpuBursts[spot.next.process.currBurst] <=
			  newProcess.cpuBursts[newProcess.currBurst])
				spot = spot.next;
		ListNode newNode = new ListNode(newProcess);
		newNode.next = spot.next;
		newNode.prev = spot;
		newNode.next.prev = newNode;
		spot.next = newNode;
		length++;
	}
	
	PCB remove()
	{
		ListNode toDelete = head.next;
		while(toDelete.process.arrivalTime  > cpuscheduler.myCPU.cpuClock)
			toDelete = toDelete.next;
		toDelete.next.prev = toDelete.prev;
		toDelete.prev.next = toDelete.next;
		length--;
		
		return toDelete.process;
	}
	
	int length()
	{
		return length;
	}
	
}
