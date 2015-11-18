import java.util.NoSuchElementException;
import java.util.Iterator;

public class LinkedListIterator<E> implements Iterator<E>
{
	private LinkedList<E> link;
	private int index;
	
	/**
	* Constructor for VectorIterator
	* @param Vector<E> that will be iterated over
	*/
	public LinkedListIterator(LinkedList<E> v)
	{
		link = v;
		index = 0;
	}
	
	/**
	* @return true if there is another value in the vector and false if there are no more values in the vector to return
	*/
	public boolean hasNext()
	{
		if(index >= link.size())
		{
			return false;
		}
		return true;
	}
	
	/**
	* @return The next value in the vector as type E
	*/
	public E next()
	{
		if(index >= link.size())
		{
			throw new NoSuchElementException("You are looking to iterate into spot: " + index + " and the size of the vector is " + link.size());
		}
		index++;
		return link.get(index-1);
	}
	
	/**
	* Removes the last returned value in the vector
	*/
	public void remove()
	{
		if(index-1 < 0 || index-1 >= link.size())
		{
			throw new NoSuchElementException("You are looking to remove spot: " + (index-1) + " and the size of the vector is " + link.size());
		}
		link.remove(index-1);
	}
}
