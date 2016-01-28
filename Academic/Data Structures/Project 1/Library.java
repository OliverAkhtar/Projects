import java.util.InputMismatchException; //to throw exception for invalid user input
import java.util.Iterator; //to declare Iterator objects
import java.util.Scanner; //for user input

/*The Library class contains a LinkedList which can hold CDRecord elements. It is
 * used by the librarian to store CD records. The class also contains methods which
 * can be used to add, delete, find, or list all CD records.*/

public class Library
{
	private static LinkedList<CDRecord> mylist = new LinkedList<CDRecord>();
	
	public static void printWelcome()
	{
		System.out.print("****************************************\n" + 
						 "Welcome to the Queens Library\n" +
						 "CD Database System!\n" +
						 "****************************************\n");
	}
	
	public static void printMenu()
	{
		System.out.print("\nChoose from the following:\n" +
						 "\tADD: Add a new Record to the list.\n" +
						 "\tDELETE : Delete a Record from the list.\n" +
						 "\tFIND : Find an existing Record in the list.\n" +
						 "\tLIST : Print all current Records to the screen.\n" +
						 "\tQUIT : Quit the CD Database System.\n" +
						 "Type your choice:");
	}
	
	/*The add() method requests information for a new CD to be added. It also
	 * checks to see if a CD already exists by the inputed library tracking
	 * number. It also error checks if an existed library tracking number checks if 
	 * the inputed Album Title matches the Album Title in the linked list.*/
	public static void add()
	{
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter Album Title:");
		String title = keyboard.nextLine();
		System.out.print("Enter Artist Name:");
		String artist = keyboard.nextLine();
		System.out.print("Enter Number of Tracks:");
		try //Invalid user input can occur after this point.
		{
			int noOfTracks = keyboard.nextInt();
			if(noOfTracks<=0)
				throw new IllegalArgumentException("Number of tracks must be " +
												   "greater than zero.");
			System.out.print("Enter Library Tracking Number:");
			long libTrackNum = keyboard.nextLong();
			if(libTrackNum<=0)
				throw new IllegalArgumentException("Library tracking number must "+ 
												   "be greater than zero.");
			System.out.print("Enter Number of Copies:");
			int noOfCopies = keyboard.nextInt();
			if(noOfCopies<=0)
				throw new IllegalArgumentException("Number of copies must be " +
												   "greater than zero.");
			
			//Check if a CD already exists with the same library tracking number.
			CDRecord CDFound = findByTrackNum(libTrackNum);
			if(CDFound==null) //No existing CD is found -> create and add to list.
			{
				CDRecord newCD = new CDRecord(title, artist, noOfTracks,libTrackNum,
											  noOfCopies);
				mylist.add(newCD);
			}
			else //CD with entered library tracking number found.
			{
				if((CDFound.getTitle()).equalsIgnoreCase(title))//if titles match
							CDFound.addCopy(); //increment # of copies
				else //Matching tracking, but mismatched titles = error
					System.out.println("Error: Addition not performed. The album " +
								       "title of the CD you are adding does not " +
								       "match the album title of the existing " +
								       "CD in the CD Library.");
			}
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println(iae.getMessage());
		}
		catch(InputMismatchException ime)
		{
			System.out.println("You must enter integer digits for number values!");
		}
	}

	/*The delete() method takes a library tracking number as input and uses it to
	 * find a CD record in the linked list to delete. If the CD exists, it is
	 * deleted if their is one copy. When their is more than one copy the user
	 * is asked if one copy or all copies should be deleted. If the CD does not
	 * exist, the user is informed of the error.*/
	public static void delete()
	{
		System.out.print("For the CD Record you want to delete,\n" +
						   "Enter the Library Tracking Number:");
		Scanner keyboard = new Scanner(System.in);
		
		try //Invalid user input can occur after this point.
		{
			long trackNum = keyboard.nextLong();
			if(trackNum<=0)
				throw new IllegalArgumentException("Library tracking number must be"
											       + " greater than zero.");
			
			//Find CD to be deleted by entered library tracking number.
			CDRecord CDtoDelete = findByTrackNum(trackNum);
			if(CDtoDelete==null) //True if CD not found.
				System.out.println("Library tracking number not found.");
			else //Else iterate through linked list, and find the node with the CD.
			{
				Iterator<CDRecord> lli = mylist.iterator();
				while(lli.hasNext())
				{
					CDRecord R = lli.next();
					if(R.getTracking()==trackNum)
					{
						if(R.getNumOfCopies()==1) //Only copy, so delete CD.
						{
							lli.remove();
							return;
						}
						else //Ask user to delete all or one copy.
						{
							System.out.print("Enter 'one' to delete one copy \n" +
											 "or 'all' to delete all copies:");
							Scanner keyboard2 = new Scanner(System.in);
							String choice = keyboard2.nextLine();
							if(choice.equalsIgnoreCase("one"))
								R.removeCopy();
							else if(choice.equalsIgnoreCase("all"))
								lli.remove();
							else
								System.out.println("Didn't enter 'one' or 'all'.");
							return;
						}
						
					}
				}
			}
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println(iae.getMessage());
			return;
		}
		catch(InputMismatchException ime)
		{
			System.out.println("You must enter integer digits for number values!");
		}	
	}
	
	/*The find() method asks the user if they want to find a CD record by Album
	 * title or library tracking number. If the CD record it found it prints all
	 * the information associated with that CD record. If a CD record is not found
	 * a message is printed informing the user.*/
	public static void find()
	{
		System.out.print("Do you want to find a CD Record by\n" +
						 "Album Title or Library Tracking Number:");
		Scanner keyboard = new Scanner(System.in);
		String choice = keyboard.nextLine();
		if(choice.equalsIgnoreCase("Album Title"))
		{
			System.out.print("Enter Album Title:");
			String titleToFind = keyboard.nextLine(); 
			Iterator<CDRecord> lli = mylist.iterator();
			while(lli.hasNext()) //Find by title.
			{
				CDRecord R = lli.next();
				if(R.getTitle().equals(titleToFind))
				{
					System.out.println("\n" + R);
					return;
				}
			}
			System.out.println("Error: CD not found in the library. A CD with the\n"
							  +" album title you provided does not exist in the \n"
							  +"CD library");
		}
		else if(choice.equalsIgnoreCase("Library Tracking Number"))
		{
			System.out.print("Enter Library Tracking Number:");
			
			try //Invalid user input can occur after this point.
			{
				long libTrackNum = keyboard.nextLong();
				if(libTrackNum<=0)
					throw new IllegalArgumentException("Library tracking number "
												     +"must be greater than zero.");
				CDRecord CDtoFind = findByTrackNum(libTrackNum);
				if(CDtoFind==null)
					System.out.println("Error: CD not found in the library. A CD\n"
								     +"with the library tracking number you \n"
								     +"provided does not exist in the CD library.");
				else
					System.out.println("\n" + CDtoFind);
			}
			catch(IllegalArgumentException iae)
			{
				System.out.println(iae.getMessage());
				return;
			}
			catch(InputMismatchException ime)
			{
				System.out.println("You must enter integer digits for number "
						          +"values!");
			}	
		}
		else
			System.out.println("Please choose 'Album Title' or 'Libary Tracking " +
							   "Number'.");
	}
	
	/*findByTrackNum() searches LinkedList of CD records to find one that matches
	 * a CD record that has a library tracking number that matches the argument.
	 * If found, it returns the CD record, if not it returns null.*/
	private static CDRecord findByTrackNum(long trackNum)
	{
		Iterator<CDRecord> lli = mylist.iterator();
		while(lli.hasNext())
		{
			CDRecord R = lli.next();
			if(R.getTracking()==trackNum)
				return R;
		}
		return null;
	}

	/*list() traverses the entire linked list and prints the information of each
	 * CD record. */
	public static void list()
	{
		Iterator<CDRecord> lli = mylist.iterator();
		int  CDnumber = 1;
		while(lli.hasNext())
		{
			CDRecord R = lli.next();
			System.out.println("CDRecord " + Integer.toString(CDnumber) + "\n" +
					           R + "\n");
			CDnumber++;
		}
	}
}