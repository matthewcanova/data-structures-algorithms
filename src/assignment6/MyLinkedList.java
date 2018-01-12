package assignment6;

import java.util.NoSuchElementException;

/**
 * Class containing methods for implementing a Doubly Linked List
 * 
 * @author Matthew Canova
 * @author Trevor Chapman
 * @param <E>
 *            - The type of object the Linked List holds
 */
public class MyLinkedList<E> implements List
{
	private Node head;
	private Node tail;
	private int size;

	public MyLinkedList()
	{
		size = 0;
	}

	/**
	 * Inserts the specified element at the beginning of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addFirst(Object element)
	{
		// check if list is empty
		if (head == null)
		{
			head = new Node(element, null, null);
			tail = head;
			size++;
			return;
		}

		// add the element to the first spot in the list
		head.previous = new Node(element, null, head);
		head = head.previous;
		size++;
	}

	/**
	 * Inserts the specified element at the end of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addLast(Object element)
	{
		// check if list is empty
		if (tail == null)
		{
			tail = new Node(element, null, null);
			head = tail;
			size++;
			return;
		}

		// add the element to the last spot in the list
		tail.next = new Node(element, tail, null);
		tail = tail.next;
		size++;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index > size()) O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, Object element) throws IndexOutOfBoundsException
	{
		if(index == 0)
		{
			addFirst(element);
			return;
		}
		if(index == size-1)
		{
			addLast(element);
			return;
		}
		// find the node currently in the spot we are inserting to
		Node currentNode = findNode(index);

		// update all references to insert the new item
		Node newNode = new Node(element, currentNode.previous, currentNode);
		currentNode.previous.next = newNode;
		currentNode.previous = newNode;
		size++;
	}

	/**
	 * Returns the first element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public Object getFirst() throws NoSuchElementException
	{
		// check for an empty list
		if (size == 0)
		{
			throw new NoSuchElementException();
		}

		return head.data;
	}

	/**
	 * Returns the last element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public Object getLast() throws NoSuchElementException
	{
		// check for an empty list
		if (size == 0)
		{
			throw new NoSuchElementException();
		}

		return tail.data;
	}

	/**
	 * Returns the element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
	 * size()) O(N) for a doubly-linked list.
	 */
	@Override
	public Object get(int index) throws IndexOutOfBoundsException
	{
		return findNode(index).data;
	}

	/**
	 * Removes and returns the first element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public Object removeFirst() throws NoSuchElementException
	{
		// check for empty list
		if (size == 0)
		{
			throw new NoSuchElementException();
		}

		// check for a one item list
		if (head.next == null)
		{
			Object data = head.data;
			clear();
			return data;
		}

		// remove the first item in the list
		Object data = head.data;
		head = head.next;
		head.previous = null;
		size--;
		return data;
	}

	/**
	 * Removes and returns the last element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public Object removeLast() throws NoSuchElementException
	{
		// check for empty list
		if (size == 0)
		{
			throw new NoSuchElementException();
		}

		// check for a one item list
		if (tail.previous == null)
		{
			Object data = tail.data;
			clear();
			return data;
		}

		// remove the last item in the list
		Object data = tail.data;
		tail = tail.previous;
		tail.next = null;
		size--;
		return data;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index >= size()) O(N) for a doubly-linked list.
	 */
	@Override
	public Object remove(int index) throws IndexOutOfBoundsException
	{
		if(index == 0)
			return removeFirst();
		if(index == size - 1)
			return removeLast();
		Node currentNode = findNode(index);

		// remove the element at the index
		currentNode.previous.next = currentNode.next;
		currentNode.next.previous = currentNode.previous;
		size--;
		return currentNode.data;
	}

	/**
	 * Traverses the Linked List to the provided index
	 * 
	 * @param index
	 *            The index to go to
	 * @return The node at the index
	 */
	private Node findNode(int index) throws IndexOutOfBoundsException
	{
		// check for index out of bounds exception
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}

		// traverse the linked list until we find the correct spot
		int currentIndex = 0;
		Node currentNode = head;
		while (currentIndex != index)
		{
			currentNode = currentNode.next;
			currentIndex++;
		}

		return currentNode;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int indexOf(Object element)
	{
		int index = 0;
		Node currentNode = head;

		// traverse the linked list
		while (currentNode != null)
		{
			// if we find the element, return index
			if (currentNode.data.equals(element))
				return index;

			// continue traversing
			currentNode = currentNode.next;
			index++;
		}

		// did not find element
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int lastIndexOf(Object element)
	{
		int index = size - 1;
		Node currentNode = tail;

		// traverse the linked list
		while (currentNode != null)
		{
			// if we find the element, return index
			if (currentNode.data.equals(element))
				return index;

			// continue traversing
			currentNode = currentNode.previous;
			index--;
		}

		// did not find the element
		return -1;
	}

	/**
	 * Returns the number of elements in this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Returns true if this collection contains no elements. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	/**
	 * Removes all of the elements from this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element). O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray()
	{
		Object[] retArray = new Object[size];
		Node current = head;

		// load data into the array
		for (int i = 0; i < size; i++)
		{
			retArray[i] = current.data;
			current = current.next;
		}

		return retArray;
	}

	/**
	 * Inner Node class for use in implementing a linked list
	 * 
	 * @author Matthew Canova
	 * @author Trevor Chapman
	 *
	 * @param <E>
	 *            - The type of object the Node holds
	 */
	private class Node<E>
	{
		E data;
		Node previous;
		Node next;

		private Node(E data, Node previous, Node next)
		{
			this.data = data;
			this.previous = previous;
			this.next = next;
		}
	}

}
