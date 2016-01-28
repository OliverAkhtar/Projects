/*Provided LinkedStack class from assignment description to be used for program.*/

import java.util.LinkedList;

public class LinkedStack<AnyType> implements Stack<AnyType>
{
	private LinkedList<AnyType> stack;
	
	public LinkedStack() 
	{ 
		stack = new LinkedList<>(); 
	}
	
	public int size()
	{
		return stack.size();
	}
	
	public boolean isEmpty()
	{
		return stack.isEmpty();
	}
	
	public void push(AnyType e)
	{
		stack.add(0, e);
	}
	
	public AnyType top()
	{
		return stack.get(0);
	}
	
	public AnyType pop()
	{
		return stack.remove(0);
	}
}