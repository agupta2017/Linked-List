import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Stack<E>, Queue<E>, Iterable<E>
{
	
	private ListNode<E> head;
	private ListNode<E> tail;
	private int size;
	
	/** Default Constructor
	* It has nothing in it at this point
	*/
	public LinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	public LinkedList(ListNode<E> h)
	{
		head = h;
		tail = h;
		size = 1;
	}
	
	/** Copy Constructor
	* @param LinkedList<E> another LinkedList that will be true copied
	*/
	public LinkedList(LinkedList<E> other)
	{
		if(other == null)
		{
			throw new NullPointerException("You put in a null variable into your copy constructor");
		}
		head = other.head.trueCopy();
		tail = other.tail.trueCopy();
		ListNode<E> current = head;
		for(ListNode<E> curr = other.head; curr != null; curr = curr.getNext())
		{
			current.setNext(curr.getNext().trueCopy());
			current = current.getNext();
		}
		size = other.size;
	}
	
	/** 
	* Places the object in the next available slot and increases the size by one
	* @param E that represents the thing you want to place into the linked list
	*/
	// I picked void for the sake of consistency with Vector
	public void add(E item)
	{
		ListNode<E> l = new ListNode<E>(item);
		if(size == 0)
		{
			head = l;
			tail = l;
		}
		else
		{
			tail.setNext(l);
			tail = l;
		}
		size++;
	}
	
	/** 
	* Places the object in the inputed index and moves all the objects right one spot
	* @param int that represents the index
	* @param E that represents the thing you want to place into the linked list
	*/
	public void add(int index, E item)
	{
		if(index > size || index< 0)
		{
			throw new IndexOutOfBoundsException("You tried to add into spot: " + index + " when the size is: " + size);
		}
		else if(index == size)
		{
			add(item);
		}
		else if(index == 0)
		{
			ListNode<E> l = new ListNode<E>(item);
			l.setNext(head);
			head = l;
			size++;
		}
		else
		{
			ListNode<E> l = new ListNode<E>(item);
			int counter = 0;
			ListNode<E> curr;
			for(curr = head; counter < index-1; curr = curr.getNext())
			{
				counter ++;
			}
			l.setNext(curr.getNext());
			curr.setNext(l);
			size++;
		}
		
	}
	
	/**
	* Gets the E corresponding to the inputed index
	* @param int index of the E that will be retrieved
	* @return E that is in the spot
	*/
	public E get(int index)
	{
		// if the index is out of size then throw an error
		if(index<size && index>=0)
		{
			int counter = 0;
			ListNode<E> curr;
			for(curr = head; counter <index ; curr = curr.getNext())
			{
				counter ++;
			}
			return curr.getItem();
		}
		throw new IndexOutOfBoundsException("You tried to access  spot: " + index + " when the size is: " + size);
	}
	
	/**
	* Removes the E corresponding to the inputed index
	* @param int index of the E that will be retrieved and removed
	* @return E that was previously in the spot
	*/
	public E remove(int index)
	{
		E item;
		if(index >= size || index< 0)
		{
			throw new IndexOutOfBoundsException("You tried to remove from spot: " + index + " when the size is: " + size);
		}
		else if(index == 0)
		{
			ListNode<E> next = head.getNext();
			head.setNext(null);
			item = head.getItem();
			head = next;
			size--;
		}
		else if(index == size-1)
		{
			int counter = 0;
			ListNode<E> prev;
			for(prev = head; counter < index -1 ; prev = prev.getNext())
			{
				counter ++;
			}
			ListNode<E> current = prev.getNext();
			item = current.getItem();
			prev.setNext(null);
			tail = prev;
			size--;
		}
		else
		{
			int counter = 0;
			ListNode<E> prev;
			for(prev = head; counter < index -1 ; prev = prev.getNext())
			{
				counter ++;
			}
			ListNode<E> next = prev.getNext().getNext();
			ListNode<E> previous = prev;
			ListNode<E> current = prev.getNext();
			current.setNext(null);
			item = current.getItem();
			previous.setNext(next);
			size--;
		}
		return item;
	}
	
	/**
	* Removes the first instance of the inputed E
	* @param int index of the E that will be retrieved
	* @return E that was previously in the spot
	*/
	public boolean remove(E item)
	{
		int index = indexOf(item);
		if( index == -1 )
		{
			return false;
		}
		remove(index);
		return true;
	}
	
	/**
	* Replaces the E at the index with the new E
	* @param int index of the E that will be replaces
	* @param E obj that is replacing the previous E
	* @return E that was previously in the spot
	*/
	public E set(int index, E item)
	{
		if(index >= size || index< 0)
		{
			throw new IndexOutOfBoundsException("You tried to remove from spot: " + index + " when the size is: " + size);
		}
		int counter = 0;
		ListNode<E> curr;
		for(curr = head; counter <index ; curr = curr.getNext())
		{
			counter ++;
		}
		E oldItem = curr.getItem();
		curr.setItem(item);
		return oldItem;
	}
	
	/*
	* @return int representation of the size which equals the amount of objects in the array
	*/
	public int size()
	{
		/**
		int counter = 0;
		for(ListNode<E> curr = head; curr != null; curr = curr.getNext()
		{
			counter ++;
		}
		return counter;
		*/
		//return size(head);
		
		return size;
	}
	
	/*
	* Clears all the objects in the array
	*/
	public void clear()
	{
		size = 0;
		head = null;
		tail = null;
	}
	
	/*
	* @return true if the size is 0 otherwise returns false
	*/
	public boolean isEmpty()
	{
		if (size == 0)
		{
			return true;
		}
		return false;
	}
	
	/*
	* @param E item that is looked for in the list
	* @return boolean true if the object is in the list otherwise returns false
	*/
	public boolean contains(E item)
	{
		int index = indexOf(item);
		if(index == -1)
		{
			return false;
		}
		return true;
	}
	
	/*
	*@param E item that will be searched for in the linked list
	*@return int index of the inputed E. Returns -1 if the inputed item is not in the list
	*/
	public int indexOf(E item)
	{
		int counter = 0;
		ListNode<E> curr;
		for(curr = head; curr != null && ((curr.getItem() == null && item == null) || ! curr.getItem().equals(item)) ; curr = curr.getNext())
		{
			counter ++;
		}
		
		if(curr == null)
		{
			return -1;
		}
		return counter;
		
	}
	
	/*
	* @return String representation of the vector
	*/
	public String toString()
	{
		String s = "";
		ListNode<E> curr;
		for(curr = head; curr != null ; curr = curr.getNext())
		{
			s+= curr.toString() + " ";
		}
		return s;
	}
	
	/*
	* @return Iterator that goes through the vector sequentially
	*/
	public Iterator<E> iterator()
	{
		return new LinkedListIterator<E>(this);
	}
	
	//These are going to be the methods for Stack and Queue
	public void push(E item)
	{
		add(0,item);
	}
	
	public E pop()
	{
		return remove(0); 
	}
	
	public E peek()
	{
		return get(0);
	}
	
	public void offer(E item)
	{
		add(item);
	}
	
	public E poll()
	{
		return remove(0);
	}
}
