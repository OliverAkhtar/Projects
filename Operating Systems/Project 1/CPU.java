/*
This class represents a CPU that executes one process at a time, either for a CPU burst or time quantum. cpuClock is used to keep track of elapsed 
time since the beginning of program execution.

The executeBurst function will execute either a CPU burst or time quantum depending on the algorithm argument passed to main function. Execution is 
simulated by a while loop which decrements for the amount of burst time or time quantum. 
For Round Robbin simulation, if the remainingBurst time for the current process is greater than the time quantum, the time quantum is subtracted from 
remainingBurst, and it is noted that the burst is incomplete. Otherwise, execution is simulated for only that CPU burst time, and it is noted that the
remainingBurst is complete.
For FCFS and SJF simulation, execution is simulated for only the current CPU burst's time. Their is no need to track remainingBurst since the entire
CPU burst must be executed.
In any case, the cpuClock is updated via the updateClock method which is passed an argument which equals either the time quantum, remainingBurst, or
the entire CPU burst depending on the scheduling algorithm and process.
*/
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
		int burst;
		if(cpuscheduler.algorithm.equals("RR"))
		{
			if(currentProcess.remainingBurst > quantum)
			{
				burst = quantum;
				currentProcess.simProgCounter += burst;
				currentProcess.state = "Running";
				while(burst != 0)
					burst--;
				currentProcess.remainingBurst -= quantum;
				currentProcess.burstIncomplete = true;
				updateClock(quantum);
			}
			else
			{
				burst = currentProcess.remainingBurst;
				currentProcess.simProgCounter += burst;
				currentProcess.state = "Running";
				while(burst != 0)
					burst--;
				currentProcess.burstIncomplete = false;
				updateClock(currentProcess.remainingBurst);
			}
		}
		else
		{
			burst = currentProcess.cpuBursts[currentProcess.currBurst];
			currentProcess.simProgCounter += burst;
			currentProcess.state = "Running";
			while(burst != 0)
				burst--;
			updateClock(currentProcess.cpuBursts[currentProcess.currBurst]);
		}
	}
	
	void updateClock(int timeSpent)
	{
		cpuClock += timeSpent;
	}
}
