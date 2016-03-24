//IN-PROGRESS Project
//This program will simulate and evaluate CPU scheduling for three scheduling algorithms: First-Come First-Serve, Shortest Job First, and Round Robbin. 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class cpuscheduler
{
	static readyQueue myReadyQueue;
	static BlockedQueue myBlockedQueue;
	static CPU myCPU;
	
	public static void main(String args[]) throws FileNotFoundException
	{
		File jobs = new File(args[2]);
		Scanner filereader = new Scanner(jobs);
		myReadyQueue = new readyQueue();
		myBlockedQueue = new BlockedQueue();
		myCPU = new CPU();
		while(!myReadyQueue.isFull())
		{
			//allocate PCB, insert in readyQ
		}
		shortTerm(); 
	}
	
	static void shortTerm()
	{
		myCPU.setProcess(myReadyQueue.dequeue());
		myCPU.updateClock();
		//function to check if process done
		//if so, function to output stats, call longterm()
		//else, place in blockedQ
		if(myCPU.cpuClock % 200 == 0)
		{
			//output stats
		}
		//send to IO
		
		//check PCB IO completion time is < CPU clock time
		//if so place in readyQ
		//else, repeat
	}
}
