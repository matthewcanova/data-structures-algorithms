package assignment3;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a set of sorted objects of type E.
 * 
 * @author Aaron Bellis, Matthew Canova
 *
 * @param <E>
 *            - the object type held by the sorted set.
 */
public class MySortedSet<E> implements SortedSet<E>
{

	// Items in set are sorted, when added to, things will go in the correct
	// position
	private E[] set;
	private int listSize;
	private Comparator<? super E> comparator;

	// Constructors

	/**
	 * Constructs a sorted set that is ordered using their natural ordering
	 */
	public MySortedSet ()
	{
		set = (E[]) new Object[2];
		listSize = 0;
		comparator = null;
	}

	/**
	 * Constructs a sorted set that is ordered using the provided comparator
	 * 
	 * @param c
	 *            - Comparator object used for sorting the set.
	 */
	public MySortedSet (Comparator<? super E> c)
	{
		this();
		comparator = c;
	}

	// Implemented methods from SortedSet interface

	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	@Override
	public Comparator<? super E> comparator ()
	{
		return comparator;
	}

	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E first () throws NoSuchElementException
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			return (E) set[0];
		}
	}

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E last () throws NoSuchElementException
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			return (E) set[listSize - 1];
		}
	}

	/**
	 * Adds the specified element to this set if it is not already present and
	 * not set to null.
	 * 
	 * @param o
	 *            -- element to be added to this set
	 * @return true if this set did not already contain the specified element
	 */
	@Override
	public boolean add (E o)
	{
		if (!contains(o))
		{
			// get index
			int index = binarySearch(o);

			// make sure array is big enough
			if (listSize >= set.length)
			{
				// need to increase size
				doubleSetSize();
			}

			// now there will be enough elements, need to shift everything to
			// the right
			for (int i = listSize; i > index; i--)
			{
				set[i] = set[i - 1];
			}

			// insert the Object o
			set[index] = o;
			// increase the listSize
			listSize++;

			// was successfully inserted
			return true;
		}
		else
		{
			// nothing was added to list
			return false;
		}
	}

	/**
	 * Adds all of the elements in the specified collection to this set if they
	 * are not already present and not set to null.
	 * 
	 * @param c
	 *            -- collection containing elements to be added to this set
	 * @return true if this set changed as a result of the call
	 */
	@Override
	public boolean addAll (Collection<? extends E> c)
	{
		boolean returnValue = false;
		for (E element : c)
		{
			if (add(element)) // both adds element and returns boolean
			{
				returnValue = true;
			}
		}
		return returnValue;
	}

	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	@Override
	public void clear ()
	{
		set = (E[]) new Object[2];
		listSize = 0;
	}

	/**
	 * @param o
	 *            -- element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	@Override
	public boolean contains (Object o)
	{

		if (isEmpty()) // list is empty, can't contain object
		{
			return false;
		}
		else
		{
			E expected;
			int index = binarySearch((E) o);

			if (index >= listSize) // position that object should be in array is
									// bigger than array, doesn't exist
			{
				return false;
			}
			else
			{
				expected = (set[index]);

				if (comparator == null) // see if it is comparable
				{
					Comparable compare;
					compare = (Comparable) expected;
					return compare.compareTo(o) == 0; // return false if objects
														// aren't equal
				}
				else
				{
					return comparator.compare(expected, (E) o) == 0; // return
																		// false
																		// if
																		// objects
																		// aren't
																		// equal
				}
			}
		}
	}

	/**
	 * @param c
	 *            -- collection to be checked for containment in this set
	 * @return true if this set contains all of the elements of the specified
	 *         collection. Will also return false when parameter c is an empty
	 *         collection, matches behavior for other Collection types like
	 *         ArrayList
	 */
	@Override
	public boolean containsAll (Collection<?> c)
	{
		// special case c is empty return false to match containsAll() behavior
		// on other Collection types, like ArrayList per TA
		if (c.isEmpty())
		{
			return false;
		}

		for (Object element : c)
		{
			// if we get a false on the check return false, not all are
			// contained
			if (!contains(element))
			{
				return false;
			}

		}
		return true;
	}

	/**
	 * @return true if this set contains no elements
	 */
	@Override
	public boolean isEmpty ()
	{
		// returns true if set is empty, false if set has at least 1 element in
		// it.
		return listSize < 1;
	}

	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	@Override
	public Iterator<E> iterator ()
	{
		return new Iterator<E>()
		{
			private int iterator = 0;
			private boolean canRemove = false;

			@Override
			public boolean hasNext ()
			{
				return iterator < listSize;
			}

			@Override
			public E next ()
			{
				if (hasNext())
				{
					canRemove = true;
					// iterator++;
					return set[iterator++]; // return element then increase
											// iterator
				}
				else
				{
					throw new NoSuchElementException();
				}
			}

			@Override
			public void remove ()
			{
				if (canRemove)
				{
					canRemove = false;
					iterator--;
					MySortedSet.this.remove(set[iterator]);
				}
				else
				{
					throw new IllegalStateException();
				}

			}
		};
	}

	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @param o
	 *            -- object to be removed from this set, if present
	 * @return true if this set contained the specified element
	 */
	@Override
	public boolean remove (Object o)
	{
		if (contains(o))
		{
			// get index
			int index = binarySearch((E) o);

			// shift left and overwrite index
			// end at size - 1, don't want to over run array length
			for (int i = index; i < listSize - 1; i++)
			{
				set[i] = set[i + 1];
			}

			// erase last element to use memory efficiently
			set[listSize - 1] = null;
			// decrease the listSize
			listSize--;

			// was successfully inserted
			return true;
		}
		else
		{
			// nothing was removed from list
			return false;
		}

	}

	/**
	 * Removes from this set all of its elements that are contained in the
	 * specified collection.
	 * 
	 * @param c
	 *            -- collection containing elements to be removed from this set
	 * @return true if this set changed as a result of the call
	 */
	@Override
	public boolean removeAll (Collection<?> c)
	{
		boolean returnValue = false;
		for (Object element : c)
		{
			if (remove(element)) // both removes element and returns boolean
			{
				returnValue = true;
			}
		}
		return returnValue;
	}

	/**
	 * @return the number of elements in this set
	 */
	@Override
	public int size ()
	{
		return listSize;
	}

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray ()
	{
		// create new array of only correct size
		E[] returnArray = (E[]) new Object[listSize];
		// copy elements
		for (int i = 0; i < listSize; i++)
		{
			returnArray[i] = set[i];
		}

		// return array
		return returnArray;
	}

	// Helper methods

	/**
	 * Searches this set for an element.
	 * 
	 * @param element
	 *            - the object to be found in the set
	 * @return - Returns the index where the element should be in the set.
	 */
	public int binarySearch (E element)
	{
		// list is empty
		if (isEmpty())
		{
			return 0;
		}

		// check if E is instance of comparable
		if (comparator == null && element instanceof Comparable)
		{
			Comparable searchElement = (Comparable) element;

			// look at middle element
			int lhs = 0;
			int rhs = listSize - 1;
			int evaluate;

			while (lhs != rhs)
			{
				evaluate = (lhs + rhs) / 2;

				if (searchElement.compareTo(set[evaluate]) > 0)
				{
					lhs = evaluate + 1;
				}
				else if (searchElement.compareTo(set[evaluate]) < 0)
				{
					rhs = evaluate;
				}
				// element is not bigger or smaller, so is equal
				else
				{
					return evaluate;
				}
			}

			if (searchElement.compareTo(set[lhs]) > 0)
			{
				return lhs + 1;
			}
			else
			{
				return lhs;
			}
		}
		// E is not instance of Comparable, use given comparator
		else
		{
			// look at middle element
			int lhs = 0;
			int rhs = listSize - 1;
			int evaluate;

			while (lhs != rhs)
			{
				evaluate = (lhs + rhs) / 2;

				if (comparator.compare(element, set[evaluate]) > 0)
				{
					lhs = evaluate + 1;
				}
				else if (comparator.compare(element, set[evaluate]) < 0)
				{
					rhs = evaluate;
				}
				else
				{
					return evaluate;
				}
			}

			if (comparator.compare(element, set[lhs]) > 0)
			{
				return lhs + 1;
			}
			else
			{
				return lhs;
			}
		}

	}


	/**
	 * Doubles the length of set while maintaining all elements in the array.
	 */
	private void doubleSetSize ()
	{
		E[] temp = (E[]) new Object[set.length * 2];
		// copy over elements
		for (int i = 0; i < listSize; i++)
		{
			temp[i] = set[i];
		}

		// point set to temp
		set = temp;
	}
}
