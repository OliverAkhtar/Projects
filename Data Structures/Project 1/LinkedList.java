/*The LinkedList class is an implementation of a doubly linked list as an abstract
 * data type. It can store any type of object. It is also possible to iterate
 * through the a objects of the class by implementation of the Iterable interface. */
public class LinkedList<AnyType> implements Iterable<AnyType>
{	
	private static class ListNode<AnyType>
	{
		public AnyType data;
		public ListNode<AnyType> prev;
		public ListNode<AnyType> next;
		
		public ListNode(AnyType d, ListNode<AnyType> p, ListNode<AnyType> n)
		{
			data = d;
			prev = p;
			next = n;
		}
	}
	
	private ListNode<AnyType> first;
	private ListNode<AnyType> last;
	private int length;
	
	public LinkedList(){
		first = new ListNode<AnyType>(null, null, null);
		last = new ListNode<AnyType>(null, first, null);
		first.next = last;
		length = 0;
	}
	
	public void add(AnyType d)
	   {
		   ListNode<AnyType> toAdd = new ListNode<AnyType>(d, first, first.next);
		   first.next.prev = toAdd;
		   first.next = toAdd;
		   length++;
	   }
	
	private void remove(ListNode<AnyType> p)
	{
		p.next.prev = p.prev;
		p.prev.next = p.next;
		length--;
	}
	
	public java.util.Iterator<AnyType> iterator()
	{
		return new LinkedListIterator(first.next);
	}
	
	private class LinkedListIterator implements java.util.Iterator<AnyType>
	{
			private ListNode<AnyType> current;
			
			public LinkedListIterator(ListNode<AnyType> start)
			{
		        current = start;
		    }
			
		    public boolean hasNext()
		    {
		      return (current!=last);
		    }
		   
		    public AnyType next()
		    {
		       if (current==null)
		          throw new NullPointerException("Linked list empty.");
		       AnyType nextItem = current.data;
		       current = current.next;
		       return nextItem;
		    }
		    
		    public void remove()
		    {
		    	LinkedList.this.remove(current.prev);
		    }
	}	
}