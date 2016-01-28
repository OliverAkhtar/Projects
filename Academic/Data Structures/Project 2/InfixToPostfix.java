/*This class class contains methods that create postfix expressions from inputed 
 *infix expressions.*/

import java.util.StringTokenizer;

public class InfixToPostfix {
	
	/*This method is used to determine if a character should be used in a stack
	 *or stack operation that facilitates the conversion algorithm.*/
	private boolean notOperandOrSpace(char c)
	{
		return (c == '+' || c == '-' || c == '*' || c == '/' || c == '('
				|| c == ')');
	}
	
	/*Space character is not part of the postfix expression, this method identifies
	 *a space.*/
	private boolean isSpace(char c)
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
	
	//Takes infix expression as parameter and returns equivalent postfix expression.
	public String convertToPostfix(String infix)
	{
		ArrayStack<String> operatorStack = new ArrayStack<String>();
		StringTokenizer tokenSet = new StringTokenizer(infix, "+-*/() ", true);
		StringBuffer postfix = new StringBuffer(); //To concatenate operands and
											       //operators as needed.
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
					postfix.append(operatorStack.pop());
				}
				/*Pop and print operators at top of stack when a right parenthesis 
				 *is read until the operator at the top is a left parenthesis, which
				 *should also be popped.*/
				if(c==')')
				{
					String operator = operatorStack.pop();
					while(operator.charAt(0)!='(')
					{
						postfix.append(operator);
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
				postfix.append(token);
		}
		
		//After all tokens are read, pop top of the stack and print the popped 
		//operator until stack is empty.
		while(!operatorStack.isEmpty())
			postfix.append(operatorStack.pop());
		
		return postfix.toString(); //Return postix expression.
	}
}
