
public class CPU
{
	int cpuClock;
	PCB currentProcess;
	int quantum;
	
	CPU()
	{
		cpuClock = 0;
		currentProcess = null;
		quantum = 0;
	}
	
	void setQuantum(int Quantum)
	{
		quantum = Quantum;
	}
	
	void setProcess(PCB process)
	{
		currentProcess = process;
		executeBurst();
	}
	
	void executeBurst()
	{
		int burst = currentProcess.cpuBursts[currentProcess.currBurst];
		currentProcess.state = "Running";
		while(burst != 0)
			burst--;
		currentProcess.simProgCounter += burst;
		updateClock();
		//get burst time, set next burst, etc..
	}
	
	void updateClock()
	{
		cpuClock += currentProcess.cpuBursts[currentProcess.currBurst];
	}
}
