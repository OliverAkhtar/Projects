/*This program uses a Linked List to store CD records for a library.*/
import java.util.Scanner; //for user input

//driver class

public class Project1
{	
	public static void main(String[] args)
	{
		Library.printWelcome();
		Scanner keyboard = new Scanner(System.in); //for user's menu choice
		while(true) //keep program running until user selects quit
		{
			Library.printMenu();
			String input = keyboard.nextLine().toUpperCase();
			switch (input)
			{
				case "ADD":
					Library.add();
					break;
				case "DELETE":
					Library.delete();
					break;
				case "FIND":
					Library.find();
					break;
				case "LIST":
					Library.list();
					break;
				case "QUIT":
					keyboard.close();
					System.exit(0);
				default:
					System.out.println("Please enter either ADD, DELETE, FIND, LIST" 
									 + " or QUIT.");
			}
		}
	}
}
