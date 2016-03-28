
public class PCB
{
	int jobID;
	int arrivalTime;
	String state;
	int simProgCounter;
	int numBursts;
	int cpuBursts[];
	int currBurst;
	int ioCompleteTime;
	
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
	}
}
