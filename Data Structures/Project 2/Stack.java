/*Provided Stack interface from assignment description.*/

public interface Stack<AnyType>
{
	int size();
	boolean isEmpty();
	void push(AnyType e);
	AnyType top();
	AnyType pop();
}