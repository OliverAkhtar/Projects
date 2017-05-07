//Synchronization project, simulating a haunted house ride with passengers and cars.

import java.util.Random;
import java.util.Vector;

public class HauntedHouse
{
	private Vector<Passenger> waitingPass = new Vector<Passenger>();
	private Vector<Car> waitingCar = new Vector<Car>();
	private Object loadingCar = new Object();
	private Vector<Object> touringCar = new Vector<Object>();
	private Random rand = new Random();
	
	public void boarding(Passenger p)
	{
		synchronized(p)
		{
			p.msg("Getting on line");
			waitingPass.addElement(p); //Get on line with other passengers.
			if(!waitingCar.isEmpty()) //If a car has been waiting for passengers..
			{
				synchronized(waitingCar.elementAt(0))
				{
					waitingCar.elementAt(0).notify(); //Let it know you are ready to board.
				}
			}
			
			p.msg("Waiting on line");
			while(true)
				try
					{p.wait(); break;} //Wait for next car to let you be loaded onto it.
				catch (InterruptedException e)
					{continue;}
			
			p.msg("Going on tour.");
			while(true)
				synchronized(touringCar.lastElement()) // touringCar vector contains objects that each represent a single tour, the last element
				{                                      // is the next tour this passenger will ride and wait for to finish.
					try
						{touringCar.lastElement().wait(); break;}
					catch (InterruptedException e)
						{continue;}
				}
			
			p.msg("Left tour, wandering.");
			int wander = rand.nextInt((9 - 7) + 1) + 7;
			try{
				Thread.sleep(wander * 1000); // Wander after tour.
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public void load(Car car)
	{
		car.msg("Getting on line.");
		waitingCar.addElement(car); //Get on line with other cars to load passengers.
		
		car.msg("Waiting for ahead cars to finish loading.");
		while(car != waitingCar.elementAt(0)) //Wait until you are car at the front of the line.
			synchronized(loadingCar) //loadingCar is the object waiting cars will wait on.
			{
				try
					{loadingCar.wait();}
				catch (InterruptedException e)
					{continue;}
			}
		
		car.msg("Waiting for passengers to come on line.");
		while(waitingPass.isEmpty()) // Wait for passengers to come on line.
			synchronized(waitingCar.elementAt(0))
			{
				try {
					waitingCar.elementAt(0).wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
		Object tour = new Object(); //Create object which represents a tour, representing a single car, and multiple passengers.
		touringCar.addElement(tour); //Add to a vector that Passenger threads will access and wait on to finish before they can wander.
		int carWeight = 0;
		car.msg("Loading passengers.");
		while(carWeight < 1000) //Load passengers
		{
			if(!waitingPass.isEmpty())
			{
				synchronized(waitingPass.elementAt(0))
				{
					waitingPass.elementAt(0).notify(); //Signal next passenger at front of line
					carWeight += waitingPass.elementAt(0).weight; //Add his weight
					waitingPass.removeElementAt(0); //Remove passenger from line
				}
			}
		}
		
		waitingCar.removeElementAt(0); //Finished loading passengers
		synchronized(loadingCar)
			{loadingCar.notifyAll();} //Allow next car to load
		
		car.msg("Starting tour.");
		int t = rand.nextInt((5 - 3) + 1) + 3;
		try{
			Thread.sleep(t * 1000); //Tour
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
		synchronized(tour)
			{tour.notifyAll();} //Notify passengers that tour is complete so they can wander.
		car.msg("Tour finished.");
	}
	
	public static void main(String[] args)
	{
		HauntedHouse h = new HauntedHouse();
		
		for(int i = 1; i<=15; i++)
			new Passenger("Pass" + i, h).start();
		for(int i = 1; i<=3; i++)
			new Car("Car" + i, h).start();
	}
}
