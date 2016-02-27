/*This class class contains methods that create postfix expressions from inputed 
 *infix expressions, create an expression tree, print the postfix expression from
 *the expression tree, as well as evaluate the expression with given user input
 *from the keyboard.*/

import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InfixToPostfix 
{
	/*This method is used to determine if a character should be used in a stack
	 *or stack operation that facilitates the conversion algorithm.*/
	private static boolean notOperandOrSpace(char c)
	{
		return (c == '+' || c == '-' || c == '*' || c == '/' || c == '('
				|| c == ')');
	}
	
	/*Space character is not part of the postfix expression, this method identifies
	 *a space.*/
	private static boolean isSpace(char c)
	{
		return (c == ' ');
	}
	
	/*This method determines if the operator at the top of the stack is of higher
	 *or equal precedence to the operator just read.*/
	private boolean lowerPrecedence(char top, char token)
	{
		switch(top)
		{
		case '+':
		case '-':
			return !(token=='+' || token=='-');
		case '*':
		case '/':
			return false;
		case '(':
			return true;
		default:
				return false;
		}
	}
	
	/*Takes infix expression as parameter, creates postfix expression queue, then
	  sends it to the createExpressionTree method.*/
	public void convertToPostfix(String infix)
	{
		LinkedStack<String> operatorStack = new LinkedStack<String>();
		StringTokenizer tokenSet = new StringTokenizer(infix, "+-*/() ", true);
		LinkedQueue<String> postfix = new LinkedQueue<String>();
		char c;
		while(tokenSet.hasMoreTokens()) //Process character every part of expression
		{
			String token = tokenSet.nextToken();
			c = token.charAt(0);
			
			//Character that is not operand or space must be processed using stack.
			if((token.length()==1) && (notOperandOrSpace(c)))
			{
				/*While stack is not empty operator at top of stack is of higher or 
				 *equal precedence than operator just read, pop and print operator
				 *at top of stack.*/
				while(!operatorStack.isEmpty() && 
					  !lowerPrecedence(operatorStack.top().charAt(0), c))	
				{
					if(c == '(') break; //Left parenthesis is always pushed.
					postfix.enqueue(operatorStack.pop());
				}
				/*Pop and print operators at top of stack when a right parenthesis 
				 *is read until the operator at the top is a left parenthesis, which
				 *should also be popped.*/
				if(c==')')
				{
					String operator = operatorStack.pop();
					while(operator.charAt(0)!='(')
					{
						postfix.enqueue(operator);
						operator = operatorStack.pop();
					}
				}
				/*When stack is empty or operator at top of stack is of lower
				 *precedence than operator just read, push operator just read on
				 *top of stack.*/
				else
				{
					operatorStack.push(token);
				}
			}
			else if((token.length()==1) && (isSpace(c)))
				; //Spaces aren't of concern in conversion.
			else //Not operator or space, so must be operand to print.
				postfix.enqueue(token);
		}
		
		//After all tokens are read, pop top of the stack and print the popped 
		//operator until stack is empty.
		while(!operatorStack.isEmpty())
			postfix.enqueue(operatorStack.pop());
		
		createExpressionTree(postfix);
	}
	
	/*This method takes the queue which contains the expression in postfix form
	 * and creates an expression tree, then sends the tree to the methods that
	 * print the postfix expression from the tree, and evaluate the expression.*/
	private static void createExpressionTree(LinkedQueue<String> postfixQueue)
	{
		LinkedStack<BinaryTree<String>> expressionTreeStack = new LinkedStack<>();
		char c;
		while(!(postfixQueue.isEmpty()))
		{
			String expressionElement = postfixQueue.dequeue();
			c = expressionElement.charAt(0);
			if(!(notOperandOrSpace(c))) //element is an operand
			{
				BinaryTree<String> T = new BinaryTree<String>(expressionElement);
				expressionTreeStack.push(T);
			}
			else //element is operator
			{
				BinaryTree<String> T = new BinaryTree<String>(expressionElement);
				BinaryTree<String> rightChild = expressionTreeStack.pop();
				BinaryTree<String> leftChild = expressionTreeStack.pop();
				T.attach(leftChild, rightChild);
				expressionTreeStack.push(T);
			}
		}
		BinaryTree<String> postfixTree = expressionTreeStack.pop();
		printPostfixTree(postfixTree);
		evaluatePostfixTree(postfixTree);
	}
	
	/*This method prints the postfix expression from the given postfix expression 
	 *tree parameter.*/
	private static void printPostfixTree(BinaryTree<String> postfixTree)
	{
		Iterator<String> treeIterator = postfixTree.iterator();
		while(treeIterator.hasNext())
		{
			String expressionElement = treeIterator.next();
			System.out.print(expressionElement);
		}
		System.out.print("\n");
	}
	
	/*This method uses the postfix expression within the expression tree parameter
	 *and evaluates it, using user input of variables if needed.*/
	private static void evaluatePostfixTree(BinaryTree<String> postfixTree)
	{
		Iterator<String> treeIterator = postfixTree.iterator();
		LinkedStack<Float> evaluationStack = new LinkedStack<Float>();
		char c;
		while(treeIterator.hasNext())
		{
			String expressionElement = treeIterator.next();
			c = expressionElement.charAt(0);
			if(!(notOperandOrSpace(c))) //element is operand
			{
				if(Character.isDigit(c)) //operand is digit
				{
					Float number = Float.valueOf(expressionElement);
					evaluationStack.push(number);
				}
				else //operand is variable
				{
					System.out.print("Enter float or integer for variable " + 
									 expressionElement + ":");
					Scanner keyboard = new Scanner(System.in);
					Float variableValue = Float.valueOf(keyboard.nextFloat());
					evaluationStack.push(variableValue);
				}
			}
			else //element is operator
			{
				float rightOperand = evaluationStack.pop().floatValue();
				float leftOperand = evaluationStack.pop().floatValue();
				switch(expressionElement)//performs appropriate arithmetic operation
				{
				case "+":
					float sum = leftOperand + rightOperand;
					Float s = Float.valueOf(sum);
					evaluationStack.push(s);
					break;
				case "-":
					float difference = leftOperand - rightOperand;
					Float d = Float.valueOf(difference);
					evaluationStack.push(d);
					break;
				case "*":
					float product = leftOperand * rightOperand;
					Float p = Float.valueOf(product);
					evaluationStack.push(p);
					break;
				case "/":
					float quotient = leftOperand / rightOperand;
					Float q = Float.valueOf(quotient);
					evaluationStack.push(q);
					break;
				}
			}
		}

		float result = evaluationStack.pop().floatValue();
		System.out.print("Result:" + result);
	}
}
