package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * A Hash Table implementation that uses separate chaining to resolve
 * collisions. Tracks a load factor, which represents the average size of each
 * LinkedList. Will rehash once the load factor exceeds the MAXLOADFACTOR.
 * 
 * @author Boqian Yao
 * @author Matthew Canova
 * 
 */

public class ChainingHashTable implements Set<String> {

	private LinkedList<String>[] storage;
	private HashFunctor functor;
	private int size;
	private int primeCapacity;
	double loadFactor;
	static final double MAXLOADFACTOR = 50.0;
	private long collisionCount;

	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {

		// make sure the capacity is a prime number.
		// if it is not a prime number we pick the next prime number.
		primeCapacity = capacity;
		while (!isPrime(primeCapacity)) {
			primeCapacity++;
		}
		// set up the table.
		storage = (LinkedList<String>[]) new LinkedList[primeCapacity];
		this.functor = functor;

	}

	@Override
	public boolean add(String item) {
		if (item == null) {
			throw new NullPointerException();
		}
		// check the loadFactor.
		if (loadFactor > MAXLOADFACTOR) {
			reHash();
		}

		int hashCode = functor.hash(item) % primeCapacity;

		// if there is a linked list at that index.
		if (storage[hashCode] != null) {
			// loop through the list and check for the item.
			for (String s : storage[hashCode]) {
				collisionCount += storage[hashCode].size();
				// item already exists, return false.
				if (s.equals(item)) {
					return false;
				}
			}

			// item does not exist in list, so add it and return true
			storage[hashCode].add(item);
			size++;
			updateLoadFactor();
			return true;
		}
		// list does not exist yet, create, add item, and return true.
		storage[hashCode] = new LinkedList<String>();
		storage[hashCode].add(item);
		size++;
		updateLoadFactor();

		return true;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		if (items == null) {
			throw new NullPointerException();
		}

		// assume set is not changed
		boolean result = false;

		// attempt to add all items from the collection
		// if any succeed, then change return value to true
		for (String s : items) {
			if (add(s))
				result = true;
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		// update all variables.
		size = 0;
		loadFactor = 0.0;
		collisionCount = 0;

		// create a new empty table.
		storage = (LinkedList<String>[]) new LinkedList[primeCapacity];

	}

	@Override
	public boolean contains(String item) {
		if (item == null) {
			throw new NullPointerException();
		}

		int hashCode = functor.hash(item) % primeCapacity;

		// if a list exists at the index already
		// check its contents for the item and if found
		// return true.
		if (storage[hashCode] != null)

			for (String s : storage[hashCode]) {

				if (s.equals(item))
					return true;
			}

		// if list does not exist at index or item not
		// found in list, return false.
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		if (items == null) {
			throw new NullPointerException();
		}

		if (items.isEmpty())
			return false;

		// loop through the collection, check for each item in the collection.
		// if any are not found, return false.
		for (String s : items) {
			if (!contains(s))
				return false;

		}
		return true;
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public int size() {

		return size;
	}

	/**
	 * A method for determining if an integer is a prime number.
	 * 
	 * @param the
	 *            number to check for primeness
	 * @return true if prime, false if not
	 */
	private boolean isPrime(int number) {
		// if value is even, return false
		if (number % 2 == 0)
			return false;

		// if value is divisible by any previous values
		// return false
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * A helper method that updates the load factor. Will be called whenever
	 * size or capacity changes.
	 */
	private void updateLoadFactor() {
		loadFactor = (double) size / primeCapacity;
	}

	/**
	 * A helper method that rehashes this table when the load factor is bigger
	 * than MAXLOADFACTOR. Updates to a new prime capacity that is more than
	 * double the previous capacity and then reinserts all elements.
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void reHash() {

		// double the capacity
		int newCapacity = 2 * primeCapacity;

		// if new capacity is not a prime number, increase it until we get a
		// prime number.
		while (!isPrime(newCapacity))
			newCapacity++;

		// store the new capacity
		primeCapacity = newCapacity;

		// save the old values.
		LinkedList<String>[] oldValues = storage;

		// set up the new table.
		storage = (LinkedList<String>[]) new LinkedList[newCapacity];
		size = 0;

		// loop through the table and transfer the old values to the new table.
		for (LinkedList<String> l : oldValues) {
			if (l != null)
				for (String s : l)
					add(s);
		}

		updateLoadFactor();
	}
	
	/**
	 * Return the number of collisions that have occurred since 
	 * creation, rehashing, or clearing. 
	 * 
	 * @return long representing the number of collisions
	 */
	public long getCollision() {
		return collisionCount;
	}

}
