
public class Car extends Thread
{
	private HauntedHouse hauntedHouse;
	public static long time;
	
	public Car(String id, HauntedHouse house)
	{
		time = System.currentTimeMillis();
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
			hauntedHouse.load(this);
		msg("Leaving haunted house.");
	}
}
