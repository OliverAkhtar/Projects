/*
 This program simulates a CPU scheduler by implementing three scheduling algorithms: First-Come First-Serve, Shortest Job First, and Round Robbin.
 An evaluation of the algorithm simulated is given by outputting process statistics including:
 	Completion Time, Processing Time, Waiting Time, and Turnaround Time for each process as it is completed.
 	Average Processing Time, Average Waiting Time, and Average Turnaround Time.
  
 Execution must be invoked by passing arguments to the main function in the form: algorithm [quantum] filename.txt, where algorithm may equal either 
 FCFS, SJF, or RR, referring to the three algorithms mentioned respectively. Quantum may only be specified if algorithm argument is RR(Round Robbin).
 The last argument must be the name of a text file containing a list of jobs(processes) where each job is defined on one line as follows:
 	JobID ArrivalTime NumberOfCpuBursts BurstsSeparatedBySpace
 	For example: 18 10 4 45 14 20 25 would indicate a process with ID 18, arrival time of 10, 4 CPU bursts of time length 45, 14, 20, 25.
 
 Besides CPU bursts, each job will request I/O for 10 time units between successive CPU bursts. For the above example with 4 CPU burst, 3 I/O 
 requests will be made, totaling 30 time units spent for I/O activity.
 
 Processes will be maintained in two queues, a ready queue which simulates processes in memory which are ready to be allocated the CPU, and a
 blocked queue which simulates processes in I/O activity. 
 
 Descriptions of scheduling algorithms:
 	First-Come First-Serve: The process that requests the CPU first is allocated the CPU first, non-preemptively. Once allocated the CPU, the
 	  						process will keep the CPU until a request for I/O operation, or completion of all CPU bursts. After a completion of a I/O
 	  						operation, the process is inserted to the end of the ready queue.
 	Shortest Job First:     The process with the next smallest CPU burst is allocated the CPU. If the next CPU burst of 2 or more processes is the
 	     					same, FCFS is used to break the tie. After a process requests and completes I/O, it is inserted into the appropriate in 
 	     					the ready queue based on its next CPU burst.
 	Round Robbin:			Similar to FCFS, but with preemption. Each process is executed in a FCFS order except the CPU time allocated to the 
 							process is the time defined by a time quantum or time slice passed as an argument to the main function. If a process has
 							a CPU burst time less than 1 time quantum, the process will release CPU after it's own CPU burst time is completed. If a
 							process has a CPU burst time greater than 1 time quantum, the process is preempted after 1 time quantum, and put at the
 							end of the ready queue.
 */

/*
 The following class variables(static fields) are used to simulate resources, keep track of user input, and provide access to both from other 
 classes:
 	File jobs - Stores the input file(text) of jobs from main function argument.
 	Scanner filereader - The scanner object used to read information from the File object jobs.
 	readyQueue myReadyQueue - Simulates main memory where processes are loaded, implementation depends on algorithm from main function argument.
 	BlockedQueue myBlockedQueue - A data structure used to store processes conducting I/O activity.
 	CPU myCPU - Simulates a CPU that executes a process for a given time, and keeps track CPU clock cycles.
 	int quantum - Stores the user inputed number from main function argument to define the time quantum or time slice for Round Robbin algorithm.
 	String algorithm - Stores the user inputed scheduling algorithm from main function argument to be executed by the program.
 	
 Process scheduling is implemented via a function for a short term and long term scheduler. The short term scheduler, assigns the process at the head
 of the ready queue to the CPU. The long term scheduler loads new processes into the ready queue(main memory).
 
 Static functions main, ShortTerm, and LongTerm, implement the following tasks:
 	main:		First store the scheduling algorithm to be used throughout program execution, which user inputs via main function arguments. Based on
 				the algorithm inputed, store the second main function argument which may be the time quantum, or name of text file. For Round Robbin,
 				set the time quantum for processes within the CPU object myCPU. Open the text file, create the ready queue and blocked queue. Fill
 				the ready queue with a process from the text file until it is full via the LongTerm function. Call the ShortTerm function to assign a
 				process to the CPU until the ready queue is empty. Finally output the average statistics which evaluate scheduling algorithm used.
 	ShortTerm:	First assign the process at the head of the ready queue to the CPU via the setProcess function of a CPU object. setProcess will also
 				invoke other CPU methods that simulate execution and keep track of CPU clock cycles, those methods are described in the CPU class.
 				For Round Robbin scheduling, if current CPU burst was not completed, the process is preemptively inserted into the end of the ready
 				queue. If not, or if scheduling is not Round Robbin, the next CPU burst is assigned for that process. At that point, if the process
 				is complete, a jobStats object is created to store average time evaluation statistics for that process, the constructor of 
 				jobStats invokes a method that output time statistics specifically for that process, and the LongTerm function is called to load a 
 				new process into the ready queue. If the process is not complete it sent sent to the blocked queue for I/O activity. Then, processes
 				in the blocked queue which have completed I/O(after 10 time units), are inserted into the ready queue. Finally, statistics of jobs
 				completed, amount of jobs in ready queue and blocked queue are output every 200 time units.
 	LongTerm:	First the next line is read from the text file, the string is 'tokenized' to separate the information, and used to create a new
 				Process Control Block(PCB) for that process, and that PCB is inserted into the ready queue.
 */
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
	static String algorithm;
	
	public static void main(String args[]) throws FileNotFoundException
	{
		algorithm = args[0];
		myCPU = new CPU();
		if(algorithm.equals("RR"))
		{
			quantum = Integer.parseInt(args[1]);
			myCPU.setQuantum(quantum);
			jobs = new File(args[2]);
		}
		else 
			jobs = new File(args[1]);
		filereader = new Scanner(jobs);
		myReadyQueue = new readyQueue();
		myBlockedQueue = new BlockedQueue();
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
		
		if(myCPU.currentProcess.burstIncomplete)
			myReadyQueue.enqueue(myCPU.currentProcess);
		else
		{
			myCPU.currentProcess.setNextBurst(); 
			if(myCPU.currentProcess.processComplete())
			{
				jobStats completedJob = new jobStats(myCPU.currentProcess);
				if(filereader.hasNextLine())
					longTerm();
			}
			else
				myBlockedQueue.enqueue(myCPU.currentProcess);
		}
		
		while(!myBlockedQueue.isEmpty() && myBlockedQueue.IOComplete())
				myReadyQueue.enqueue(myBlockedQueue.dequeue());
		
		if(myCPU.cpuClock % 200 == 0) //every 200 times units, output statistics
		{
			System.out.println("# of jobs in ReadyQueue: " + myReadyQueue.size() + "\n" +
							   "# of jobs in BlockedQueue: " + myBlockedQueue.size() + "\n" + 
							   "# of jobs completed: " + jobStats.allStats.size() + "\n");
			
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