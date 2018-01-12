package assignment7;

import assignment6.MyLinkedList;
import java.util.NoSuchElementException;

/**
 * Represents a generic stack (first in, last out).
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 * 
 * @param <E>
 *            -- the type of elements contained in the stack
 */
public class MyStack<E>
{

	private MyLinkedList<E> stack;

	public MyStack()
	{
		stack = new MyLinkedList<E>();
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	public void clear()
	{
		stack.clear();
	}

	/**
	 * Returns true if the stack contains no elements.
	 */
	public boolean isEmpty()
	{
		return stack.isEmpty();
	}

	/**
	 * Returns the item at the top of the stack without removing it from the
	 * stack. Throws NoSuchElementException if the stack is empty.
	 */
	public E peek() throws NoSuchElementException
	{
		return (E) stack.getFirst();
	}

	/**
	 * Returns and removes the item at the top of the stack. Throws
	 * NoSuchElementException if the stack is empty.
	 */
	public E pop() throws NoSuchElementException
	{
		return (E) stack.removeFirst();
	}

	/**
	 * Pushes the input item onto the top of the stack.
	 */
	public void push(E item)
	{
		stack.addFirst(item);
	}

	/**
	 * Returns the number of items in the stack.
	 */
	public int size()
	{
		return stack.size();
	}
}