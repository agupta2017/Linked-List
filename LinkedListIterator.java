import java.util.NoSuchElementException;
import java.util.Iterator;

public class LinkedListIterator<E> implements Iterator<E>
{
	private ListNode<E> curr;
	
	/**
	* Constructor for VectorIterator
	* @param Vector<E> that will be iterated over
	*/
	public LinkedListIterator(ListNode<E> head)
	{
		curr = head;
	}
	
	/**
	* @return true if there is another value in the vector and false if there are no more values in the vector to return
	*/
	public boolean hasNext()
	{
		if(curr != null)
		{
			return true;
		}
		return false;
	}
	
	/**
	* @return The next value in the vector as type E
	*/
	public E next()
	{
		if(curr == null)
		{
			throw new NoSuchElementException();
		}
		E hold = curr.getItem();
		curr = curr.getNext();
		return hold;
	}
	
	/**
	* Removes the last returned value in the vector
	*/
	public void remove()
	{
		
	}
}
