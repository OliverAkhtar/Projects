/*This program reads infix arithmetic expressions, one from each line, then converts
 *and prints the equivalent postfix expression.*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project2
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
			System.out.println("Postfix: " + converter.convertToPostfix(expression)
							   + "\n");
		}
		inputFile.close();
	}
}