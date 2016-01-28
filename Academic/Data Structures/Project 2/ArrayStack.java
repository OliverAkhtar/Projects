/*Provided ArrayStack class from assignment description with added implementation
 *of methods.*/

public class ArrayStack<AnyType> implements Stack<AnyType>
{
	public static final int DEFAULT_CAPACITY = 1024;
	AnyType[] stack;
	int topOfStack;
	
	public ArrayStack() { this(DEFAULT_CAPACITY); }
	public ArrayStack(int capacity)
	{
		topOfStack = -1;
		stack = (AnyType[]) new Object[capacity];
	}
	
	public int size()
	{
		return (topOfStack + 1);
	}
	
	public boolean isEmpty()
	{
		return (topOfStack == -1);
	}
	
	public void push(AnyType e) throws IllegalStateException
	{
		stack[topOfStack + 1] = e;
		topOfStack++;
	}
	
	public AnyType top() throws IllegalStateException
	{
		return stack[topOfStack];
	}
	
	public AnyType pop() throws IllegalStateException
	{
		topOfStack--;
		return stack[topOfStack + 1];
	}
	
}
