//IN-PROGRESS Project
//This program will simulate and evaluate CPU scheduling for three scheduling algorithms: First-Come First-Serve, Shortest Job First, and Round Robbin. 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class cpuscheduler
{
	static File jobs;
	static Scanner filereader;
	static readyQueue myReadyQueue;
	static BlockedQueue myBlockedQueue;
	static CPU myCPU;
	static int quantum;
	
	public static void main(String args[]) throws FileNotFoundException
	{
		myReadyQueue = new readyQueue();
		myBlockedQueue = new BlockedQueue();
		myCPU = new CPU();
		String algorithm = args[0];
		if(algorithm.equals("RR"))
		{
			quantum = Integer.parseInt(args[1]);
			myCPU.setQuantum(quantum);
			jobs = new File(args[2]);
		}
		else 
			jobs = new File(args[1]);
		filereader = new Scanner(jobs);
		while(!myReadyQueue.isFull() && filereader.hasNextLine())
			longTerm();
		while(!myReadyQueue.isEmpty())
			shortTerm();
		jobStats.avgStats();
	}
	
	static void shortTerm()
	{
		//setProcess() assigns process at head of ready queue to the CPU
		myCPU.setProcess(myReadyQueue.dequeue());
		/*setProcess() then calls executeBurst() which runs for the current burst
		 *time for that process, then executeBurst() calls on updateClock() to 
		 *update the CPU clock.*/
		myCPU.currentProcess.setNextBurst(); //set the next burst for that process.
		if(myCPU.currentProcess.processComplete())
		{
			jobStats completedJob = new jobStats(myCPU.currentProcess);
			if(filereader.hasNextLine())
				longTerm();
		}
		else
			myBlockedQueue.enqueue(myCPU.currentProcess);
			
		//function to check if process done
		//if so, function to output stats, call longterm()
		//else, place in blockedQ
		// set IO function
		
		//check PCB IO completion time is < CPU clock time
		//if so place in readyQ
		while(!myBlockedQueue.isEmpty() && myBlockedQueue.IOComplete())
			{
				myReadyQueue.enqueue(myBlockedQueue.dequeue());
			}
		
		if(myCPU.cpuClock % 200 == 0)
		{
			//output stats
		}
	}
	
	static void longTerm()
	{
		String job = filereader.nextLine();
		StringTokenizer st = new StringTokenizer(job);
		int jobId = Integer.parseInt(st.nextToken());
		int arrival = Integer.parseInt(st.nextToken());
		int numBursts = Integer.parseInt(st.nextToken());
		int[] bursts = new int[numBursts];
		for(int i = 0; i < numBursts; i++)
			bursts[i] = Integer.parseInt(st.nextToken());
		PCB newJob = new PCB(jobId, arrival, numBursts, bursts);
		myReadyQueue.enqueue(newJob);
	}
}