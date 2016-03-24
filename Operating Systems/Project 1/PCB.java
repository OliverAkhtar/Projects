
public class PCB
{
	int jobID;
	String state;
	int simProgCounter;
	int numBursts;
	int cpuBursts[];
	int currBurst;
	int ioCompleteTime;
	
	PCB()
	{
		
	}
	
	void setIO()
	{
		ioCompleteTime = cpuscheduler.myCPU.cpuClock + 10;
	}
}
