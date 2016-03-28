import java.util.Iterator;
import java.util.LinkedList;


public class jobStats
{
	static LinkedList<jobStats> allStats = new LinkedList<jobStats>();
	
	int JobID;
	int arriveTime;
	int completeTime;
	int processTime = 0;
	int waitTime;
	int turnaTime;
	
	jobStats(PCB job)
	{
		JobID = job.jobID;
		arriveTime = job.arrivalTime;
		completeTime = cpuscheduler.myCPU.cpuClock;
		for(int i = 0; i < job.numBursts; i++)
			processTime += job.cpuBursts[i];
		processTime += (job.numBursts - 1) * 10;
		turnaTime = completeTime - arriveTime;
		waitTime = turnaTime - processTime;
		allStats.add(this);
		displayStats();
	}
	
	void displayStats()
	{
		System.out.println("Job ID: " + JobID + "\n" +
						   "Arrival Time: " + arriveTime + "\n" +
						   "Completetion Time: " + completeTime + "\n" +
						   "Processing Time: " + processTime + "\n" +
						   "Turnaround Time: " + turnaTime + "\n" +
						   "Waiting Time: " + waitTime + "\n");
	}
	
	static void avgStats()
	{
		int numJobs = allStats.size();
		int totalPTime = 0, totalWTime = 0, totalTTime = 0;
		Iterator<jobStats> li = allStats.iterator();
		while(li.hasNext())
		{
			jobStats a = li.next();
			totalPTime += a.processTime;
			totalWTime += a.waitTime;
			totalTTime += a.turnaTime;
		}
		int avgPTime = totalPTime/numJobs;
		int avgWTime = totalWTime/numJobs;
		int avgTTime = totalTTime/numJobs;
		System.out.println("CPU Clock: " + cpuscheduler.myCPU.cpuClock + "\n" +
						 "Average Processing Time: " + avgPTime + "\n" +
						 "Average Waiting Time: " + avgWTime + "\n" +
						 "Average Turnaround Time: " + avgTTime + "\n");
	}
	
}
