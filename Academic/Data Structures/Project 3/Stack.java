/*Provided Stack interface from assignment description to be used for program.*/

public interface Stack<AnyType>
{
	int size();
	boolean isEmpty();
	void push(AnyType e);
	AnyType top();
	AnyType pop();
}