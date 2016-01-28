public class ListNode<E>
{
	private E item;
	private ListNode<E> next;
	
	public ListNode(E i)
	{
		item = i;
	}
	
	public ListNode(E i, ListNode<E> n)
	{
		item = i;
		next = n;
	}
	
	public E getItem()
	{
		return item;
	}
	
	public ListNode<E> getNext()
	{
		return next;
	}
	
	public void setItem(E i)
	{
		item = i;
	}
	
	public void setNext(ListNode<E> n)
	{
		next = n;
	}
	
	public String toString()
	{
		if(item == null)
		{
			return "null";
		}
		return item.toString();
	}
	
	public ListNode<E> trueCopy()
	{
		return new ListNode<E>(item,next);
	}
	
}
