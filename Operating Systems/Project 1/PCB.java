/*
The PCB class keeps track of information associated with each particular process. The state of a process may either be "Ready", "Running", or 
"Blocked" depending on whether the process is in the CPU, ready or blocked queue. simProgCounter refers to the number of CPU clock cycles used
by the process so far. 
ioCompleteTime is the time when a process is expected to complete I/O, which equals the current CPU clock time plus ten time units.
setNextBurst makes sure to recalculate the remaingBurst time when the currBurst index is incremented.
 */
public class PCB
{
	int jobID;
	int arrivalTime;
	String state;
	int simProgCounter;
	int numBursts;
	int cpuBursts[];
	int currBurst; //Index of current burst in cpuBursts array.
	int ioCompleteTime;
	int remainingBurst;
	boolean burstIncomplete;
	
	PCB(int id, int arrival, int NumBursts, int[] bursts)
	{
		jobID = id;
		arrivalTime = arrival;
		state = "Ready";
		simProgCounter = 0;
		numBursts = NumBursts;
		cpuBursts = bursts;
		currBurst = 0; 
		ioCompleteTime = 0;
		remainingBurst = cpuBursts[currBurst];
		burstIncomplete = false; //Only under Round Robbin scheduling would this field be used/altered.
	}
	
	void setIO()
	{
		ioCompleteTime = cpuscheduler.myCPU.cpuClock + 10;
	}
	
	boolean processComplete()
	{
		return (currBurst == numBursts);
	}
	
	void setNextBurst()
	{
		currBurst++;
		if(cpuscheduler.algorithm.equals("RR"))
			if(!processComplete())
				remainingBurst = cpuBursts[currBurst];
	}
}
