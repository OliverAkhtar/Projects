
public class CPU
{
	static int cpuClock;
	static PCB currentProcess;
	
	void setProcess(PCB process)
	{
		currentProcess = process;
		executeBurst();
	}
	
	void executeBurst()
	{
		//get burst time, set next burst, etc..
		
	}
	
	void updateClock()
	{
		cpuClock += currentProcess.currBurst;
	}
	
	
}
