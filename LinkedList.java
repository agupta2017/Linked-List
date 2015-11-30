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
		//I checked if I need to throw an exception first
		if(other == null)
		{
			throw new NullPointerException("You put in a null variable into your copy constructor");
		}
		//Then I checked if we had the special case of an empty LinkedList
		if(other.size() == 0)
		{
			head = null;
			tail = null;
			size = 0;
		}
		//Otherwise I go through the for loop and use setNext to make my linkedList
		//I use truCopy which is in ListNode to make a deep copy of the ListNode
		else
		{
			this.head = other.head.trueCopy();
			ListNode<E> current = this.head;
			ListNode<E> curr;
			for(curr = other.head; curr.getNext() != null; curr = curr.getNext())
			{
				current.setNext(curr.getNext().trueCopy());
				current = current.getNext();
			}
			if(curr.getNext() == null)
			{
				this.tail = current;
			}
			size = other.size;
		}
	}
	
	/** 
	* Places the object in the next available slot and increases the size by one
	* @param E that represents the thing you want to place into the linked list
	*/
	// I picked void for the sake of consistency with Vector
	public void add(E item)
	{
		//This is the ListNode that I am wrapping around the item
		ListNode<E> l = new ListNode<E>(item);
		//I check for the special case of an empty LinkedList
		if(size == 0)
		{
			head = l;
			tail = l;
		}
		//Otherwise I add the ListNode to the end and reposition my tail pointer
		else
		{
			tail.setNext(l);
			tail = tail.getNext();
		}
		size++;
		//System.out.println(this);
	}
	
	/** 
	* Places the object in the inputed index and moves all the objects right one spot
	* @param int that represents the index
	* @param E that represents the thing you want to place into the linked list
	*/
	public void add(int index, E item)
	{
		//I check to see if I have to throw an exception
		if(index > size || index< 0)
		{
			throw new IndexOutOfBoundsException("You tried to add into spot: " + index + " when the size is: " + size);
		}
		//I then check if I need to do one of the constant time operations of adding to the end
		else if(index == size)
		{
			add(item);
		}
		//Or adding to the beginning
		else if(index == 0)
		{
			ListNode<E> l = new ListNode<E>(item);
			l.setNext(head);
			head = l;
			size++;
		}
		//Otherwise I traverse the array using counter to access the correct spot and add my ListNode in there
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
		// if the index is not out of bounds then I traverse the list using counter to access the right spot
		// and then I simply return that ListNode's item
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
		//Otherwise I throw an exception
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
		//I check to see if I have to throw an error
		if(index >= size || index< 0)
		{
			throw new IndexOutOfBoundsException("You tried to remove from spot: " + index + " when the size is: " + size);
		}
		//I check to see if I am going to have to reposition my head pointer
		else if(index == 0)
		{
			ListNode<E> next = head.getNext();
			head.setNext(null);
			item = head.getItem();
			head = next;
			size--;
		}
		//Or if I have to reposition my tail pointer
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
		//Otherwise we just traverse the array using counter to get to the right spot 
		//and access the item and remove the ListNode from the chain
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
		//If it made it to this point we simply return the item
		return item;
	}
	
	/**
	* Removes the first instance of the inputed E
	* @param int index of the E that will be retrieved
	* @return E that was previously in the spot
	*/
	public boolean remove(E item)
	{
		//I use indexOf to find the index of the thing to be removed
		int index = indexOf(item);
		//And then make sure the object is in the array
		if( index == -1 )
		{
			return false;
		}
		// And if it is, then I use the remove(index) to remove it
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
		//I check for an exception first
		if(index >= size || index< 0)
		{
			throw new IndexOutOfBoundsException("You tried to remove from spot: " + index + " when the size is: " + size);
		}
		//Then I traverse the array using my for loop and use setItem to change the correct ListNode
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
		//This was the orignal for loop
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
		//I just reset my head and tail pointers
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
		//I use indexOf to see if the object is in the List
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
		// I run through the array until I reach the end in my for loop
		// If the item of the ListNode matches the item then I break the for loop
		// My counter is keeping track of the index I am on
		// I have separate checks for cases involving null because if I use .equals() on null
		// A NullPointerException is thrown
		int counter = 0;
		ListNode<E> curr;
		for(curr = head; curr != null ; curr = curr.getNext())
		{
			if (curr.getItem() == null && item == null)
			{
				break;
			}
			else if ( item == null || curr.getItem() == null)
			{
			
			}
			else if (curr.getItem().equals(item))
			{
				break;
			}	
			counter ++;
		}
		//If I got to the end then I return -1
		if(curr == null)
		{
			return -1;
		}
		//Otherwise I return the index I broke at
		return counter;
		
	}
	
	/*
	* @return String representation of the vector
	*/
	public String toString()
	{
		//I traverse the array using my for loop
		String s = "";
		for(ListNode<E> curr = head; curr.getNext() != null ; curr = curr.getNext())
		{
			s+= curr.toString() + ", ";
		}
		s+= tail.toString();
		return s;
	}
	
	/*
	* @return Iterator that goes through the vector sequentially
	*/
	public Iterator<E> iterator()
	{
		return new LinkedListIterator<E>(head);
	}
	
	//These are going to be the methods for Stack and Queue
	/*
	* Method for stack
	* Pushes onto the end of the stack
	* Which will be the start of the LinkedList
	* @param item the item that will be added onto the stack
	*/
	public void push(E item)
	{
		add(0,item);
	}
	
	/*
	* Method for stack
	* Removes from the end of the stack
	* Which will be the start of the LinkedList
	* @return the item that will be removed from the stack
	*/
	public E pop()
	{
		return remove(0); 
	}
	
	/*
	* Method for stack and queue
	* Looks at the end of the stack and the beginning of the queue
	* Which will be the start of the LinkedList
	* @return the item that will be gotten from the stack and queue
	*/
	public E peek()
	{
		return get(0);
	}
	
	/*
	* Method for queue
	* Pushes onto the end of the queue
	* Which will be the end of the LinkedList
	* @param item the item that will be added onto the queue
	*/
	public void offer(E item)
	{
		add(item);
	}
	
	/*
	* Method for queue
	* Removes from the start of the queue
	* Which will be the start of the LinkedList
	* @return the item that will be removed from the stack
	*/
	public E poll()
	{
		return remove(0);
	}
	
	//These are the specific add and removes
	/*
	* Removes the first item in the LinkedList
	* Constant time operation
	* @return E item that was in the first spot of the LinkedList
	*/
	public E removeFirst()
	{
		return remove(0);
	}
	
	/*
	* Removes the last item in the LinkedList
	* Constant time operation
	* @return E item that was in the last spot of the LinkedList
	*/
	public E removeLast()
	{
		return remove(size-1);
	}
	
	/*
	* Adds the item to the first spot in the LinkedList
	* Constant time operation
	* @param E item that will be added in the first spot of the LinkedList
	*/
	public void addFirst(E item)
	{
		add(0, item);
	}
	
	/*
	* Adds the item to the last spot in the LinkedList
	* Constant time operation
	* @param E item that will be added in the last spot of the LinkedList
	*/
	public void addLast(E item)
	{
		add(item);
	}
	
}
