
public class Passenger extends Thread
{	
	private HauntedHouse hauntedHouse;
	int weight;
	public static long time;
	
	public Passenger(String id, HauntedHouse house)
	{
		time = System.currentTimeMillis();
		weight = 200;
		setName(id);
		hauntedHouse = house;
	}
	
	public void msg(String m)
	{
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}
	
	public void run()
	{
		for(int i = 0; i < 3; i++)
			hauntedHouse.boarding(this);
		msg("Leaving haunted house.");
	}
}
