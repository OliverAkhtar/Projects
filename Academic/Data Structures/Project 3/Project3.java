/*This program reads infix arithmetic expressions, one from each line, prints the 
 * equivalent postfix expression, asks user for integer or float values for
 * variables in expression as keyboard input, then evaluates the expression and
 * prints the total value.*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project3
{
	public static void main(String[] args) throws FileNotFoundException
	{
		File expressions = new File("src/expressions.txt");
		Scanner inputFile = new Scanner(expressions);
		InfixToPostfix converter = new InfixToPostfix();
		/*InFixToPostFix class contains methods that returns postfix expressions
		 *from inputed infix expressions, converter object is used to access them.*/
		while(inputFile.hasNextLine())
		{
			String expression = inputFile.nextLine();
			System.out.println("Infix: " + expression);
			System.out.print("Postfix: "); 
			converter.convertToPostfix(expression);
			System.out.println("\n");
		}
		inputFile.close();
	}
}